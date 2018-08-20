package org.smart4j.framework.web;

import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
/**
 * 
 * @ClassName RequestMapperDecorate  
 * @Description 抽象装饰器 
 * @author chengzhb 
 * @date 2018年8月10日  
 *   
 */
public class RequestMapperDecorate implements RequestMapping{
private RequestMapping requestMapping;
	public Object doAction(Handler handler, Param param) {
		return requestMapping.doAction(handler, param);
	}
	public RequestMapperDecorate(RequestMapping requestMapping) {
		this.requestMapping = requestMapping;
	}

}
