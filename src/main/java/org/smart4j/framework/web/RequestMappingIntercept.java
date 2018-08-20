package org.smart4j.framework.web;

import java.util.List;

import org.smart4j.framework.annotation.Intercept;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.filter.FilterChain;
import org.smart4j.framework.filter.FilterHandler;
import org.smart4j.framework.filter.MyFilter;
import org.smart4j.framework.interceptor.InterceptHandler;
import org.smart4j.framework.interceptor.MyInterceptor;

public class RequestMappingIntercept extends RequestMapperDecorate{
	private List<MyInterceptor> interceptor;
	private List<MyFilter> filterList;
	private FilterChain filterChain;
	public RequestMappingIntercept(RequestMapping requestMapping) {
		super(requestMapping);
		interceptor=InterceptHandler.getInterceptorList();
		filterList=FilterHandler.getFilterList();
		filterChain=new FilterChain();
		filterChain.addFilter(filterList);
	}
	public Object doAction(Handler handler, Param param) {
		filterChain.doFilterChain();
		for(MyInterceptor in:interceptor) {
			if(in.getClass().isAnnotationPresent(Intercept.class)) {
				Intercept annotation = in.getClass().getAnnotation(Intercept.class);
				String url=annotation.url();
				String requestPath=param.getString("requestPath");
				if(url.equals(requestPath)) {
					in.preHandler(param);
				}
			}
		}
		Object obj=super.doAction(handler, param);
		return obj;
	}
	
}
