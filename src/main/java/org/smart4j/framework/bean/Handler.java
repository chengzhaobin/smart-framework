package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 
 * @ClassName Handler  
 * @Description 封装Action信息 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class Handler {
	/**
	 * Controller 类
	 */
	private Class<?> controllerClass;
	/**
	 * action 方法
	 */
	private Method actionMethod;
	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	public Method getActionMethod() {
		return actionMethod;
	}
}
