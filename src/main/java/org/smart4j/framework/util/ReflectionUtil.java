package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName ReflectionUtil  
 * @Description 反射工具类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class ReflectionUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(ReflectionUtil.class);
	/**
	 * 
	 * @Title: newInstance 
	 * @Description: 创建实例
	 * @param cls
	 * @return 参数说明
	 * @return Object    返回类型
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance=null;
		try {
			instance=cls.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instance failure",e);
			throw new RuntimeException(e);
		} 
		return instance;
	}
	/**
	 * 
	 * @Title: invokeMethod 
	 * @Description: 调用方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return 参数说明
	 * @return Object    返回类型
	 */
	public static Object invokeMethod(Object obj,Method method,Object... args) {
		Object result=null;
		try {
			method.setAccessible(true);
			result=method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 
	 * @Title: setField 
	 * @Description: 设置成员变量的值
	 * @param obj
	 * @param field
	 * @param value 参数说明
	 * @return void    返回类型
	 */
	public static void setField(Object obj,Field field,Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field failure",e);
			throw new RuntimeException(e);
		} 
	}
}
