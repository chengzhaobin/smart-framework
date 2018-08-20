package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;

/**
 * 
 * @ClassName BeanHelper  
 * @Description bean助手类
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class BeanHelper {
	/**
	 * 定义 bean 映射(用于存放bean类与bean实例的映射关系)
	 */
private static Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();

static {
	Set<Class<?>> beanClassSet=ClassHelper.getBeanClassSet();
	for(Class<?> cls:beanClassSet) {
		BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls));
	}
}
/**
 * 
 * @Title: getBeanMap 
 * @Description: 获取beanMap
 * @return 参数说明
 * @return Map<Class<?>,Object>    返回类型
 */
public static Map<Class<?>,Object> getBeanMap(){
	return BEAN_MAP;
}
/**
 * 
 * @Title: getBean 
 * @Description: 获取bean实例 
 * @param cls
 * @return 参数说明
 * @return Object    返回类型
 */
@SuppressWarnings("unchecked")
public static <T> T  getBean(Class<T> cls) {
	if(!BEAN_MAP.containsKey(cls)) {
		throw new RuntimeException("can not get bean by class: "+cls);
	}
	return (T) BEAN_MAP.get(cls);
}
public static void setBean(Class<?> cls,Object obj) {
	BEAN_MAP.put(cls, obj);
}
}
