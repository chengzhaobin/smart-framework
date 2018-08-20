package org.smart4j.framework.web;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.util.ReflectionUtil;

public class RequestMappingHandler implements RequestMapping{
	/**
	 * 
	 * @Title: doAction 
	 * @Description: 调用方法 
	 * @param handler
	 * @param param
	 * @return 参数说明
	 * @return Object    返回类型
	 */
	public  Object doAction(Handler handler,Param param) {
		//获取Controller 类及其bean
		Class<?> controllerClass=handler.getControllerClass();
		
		Object controllerBean=BeanHelper.getBean(controllerClass);
		
		Method actionMethod=handler.getActionMethod();
		Class<?>[] parameterTypes = actionMethod.getParameterTypes();
		if(parameterTypes.length==0) {
			return ReflectionUtil.invokeMethod(controllerBean, actionMethod);
		}
		
		Object[] obj=new Object[parameterTypes.length];
		for(int i=0;i<parameterTypes.length;i++) {
			try {
				obj[i]=parameterTypes[i].newInstance();
			//	baseParamTypeHandler(parameterTypes, param);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//获取参数类型的所有属性
			Field[] fields = parameterTypes[i].getDeclaredFields();
			for(Field field:fields) {
				for(Map.Entry<String, Object> entry:param.getParamMap().entrySet()) {
					if(entry.getKey().equals(field.getName())) {
						field.setAccessible(true);
						try {
							field.set(obj[i], entry.getValue());
						} catch (Exception e) {
							e.printStackTrace();
						} 
						break;
					}
				}
				
			}
		}
		return ReflectionUtil.invokeMethod(controllerBean, actionMethod,obj);
	}
	/**
	 * 
	 * @Title: baseParamTypeHandler 
	 * @Description: 基本数据类型处理
	 * @return void    返回类型
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public void baseParamTypeHandler(Class<?>[] parameterTypes,Param param) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		for(Class<?> cls:parameterTypes) {
			for(Map.Entry<String, Object> entry:param.getParamMap().entrySet()) {
				if(cls.getName().equals("java.lang.String")) {
					if(entry.getKey().equals(cls.getField("value[]").getName())) {
						Object obj=cls.newInstance();
						cls.getField("value[]").set(obj,entry.getValue());
					}
				}
			}
		}
	}
}
