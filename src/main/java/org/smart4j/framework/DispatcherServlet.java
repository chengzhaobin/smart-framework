package org.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StreamUtil;
import org.smart4j.framework.util.StringUtil;
import org.smart4j.framework.web.RequestMapping;
import org.smart4j.framework.web.RequestMappingHandler;
import org.smart4j.framework.web.RequestMappingIntercept;
/**
 * 
 * @ClassName DispatcherServlet  
 * @Description 请求转发器 
 * @author chengzhb 
 * @date 2018年8月8日  
 */
@WebServlet(urlPatterns="/*",loadOnStartup=0)
public class DispatcherServlet extends HttpServlet {
	RequestMapping requestMapping;
	@Override
public void init(ServletConfig servletConfig) throws ServletException {
		//初始化Helper类
		HelperLoader.init();
		
		RequestMapping rm=new RequestMappingHandler();
		requestMapping=new RequestMappingIntercept(rm);
		//获取ServletContext对象(用于注册Servlet)
		ServletContext servletContext=servletConfig.getServletContext();
		//注册处理JSP的Servlet
		ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		//注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
}
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		//获取请求方法与请求路径
		String requestMethod=request.getMethod().toLowerCase();
		String requestPath=request.getPathInfo();
		//获取Action 处理器
		Handler handler=ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler!=null) {
			//创建请求参数对象
			Param param=getParam(request);
			//调用方法
			param.getParamMap().put("requestPath", requestPath);
			
			Object result=requestMapping.doAction(handler, param);
			//处理返回值
			doView(result, request, response);
		}
	}
	/**
	 * 
	 * @Title: getParam 
	 * @Description: 组装参数
	 * @param request
	 * @return
	 * @throws IOException 参数说明
	 * @return Param    返回类型
	 */
	public Param getParam(HttpServletRequest request) throws IOException {
		Map<String,Object> paramMap=new HashedMap<String,Object>();
		Enumeration<String> paramNames=request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName=paramNames.nextElement();
			String paramValue=request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}
		//请求体
		String body =CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
		if(StringUtil.isNotEmpty(body)) {
			String[] params =StringUtil.splitString(body,"&");
			if(ArrayUtil.isNotEmpty(params)) {
				for(String param:params) {
					String[] array=StringUtil.splitString(param,"=");
					if(ArrayUtil.isNotEmpty(array) && array.length==2) {
						paramMap.put(array[0],array[1]);
					}
				}
			}
		}
		return new Param(paramMap);
	}
	public void doView(Object result,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		if(result instanceof View) {
			View view =(View) result;
			String path=view.getPath();
			if(StringUtil.isNotEmpty(path)) {
				if(path.startsWith("/")) {
					response.sendRedirect(request.getContextPath()+path);
				}else {
					Map<String,Object> model=view.getModel();
					for(Map.Entry<String,Object> entry:model.entrySet()) {
						request.setAttribute(entry.getKey(),entry.getValue());
					}
				}
				request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
			}
		}else if(result instanceof Data) {
			//返回JSON数据
			Data data=(Data) result;
			Object model=data.getModel();
			if(model!=null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				String json=JsonUtil.toJson(model);
				writer.write(json);
				writer.flush();
				writer.close();
			}
		}
		
	}
}
