package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Intercept;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

/**
 * 
 * @ClassName ClassHelper  
 * @Description 类操作 助手类 
 * @author chengzhb 
 * @date 2018年8月7日  
 *   
 */
public final class ClassHelper {
/**
 * 定义类集合 (用于存放所加载的类)
 */
private static  Set<Class<?>> CLASS_SET;
static {
	String basePackage=ConfigHelper.getAppBasePackage();
	CLASS_SET=ClassUtil.getClassSet(basePackage);
}
/**
 * 
 * @Title: getClassSet 
 * @Description: 获取应用包名下的所有类
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getClassSet(){
	return CLASS_SET;
}
/**
 * 
 * @Title: getServiceClassSet 
 * @Description: 获取应用包名下所有的service类
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getServiceClassSet(){
	Set<Class<?>> classSet=new HashSet<Class<?>>();
	for(Class<?> cls:CLASS_SET) {
		if(cls.isAnnotationPresent(Service.class)) {
			classSet.add(cls);
		}
	}
	return classSet;
}
/**
 * 
 * @Title: getControllerClassSet 
 * @Description: 获取应用包名下所有的controller类
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getControllerClassSet(){
	Set<Class<?>> classSet=new HashSet<Class<?>>();
	for(Class<?> cls:CLASS_SET) {
		if(cls.isAnnotationPresent(Controller.class)) {
			classSet.add(cls);
		}
	}
	return classSet;
}
/*public static Set<Class<?>> getInterceptClassSet(){
	Set<Class<?>> classSet=new HashSet<Class<?>>();
	for(Class<?> cls:CLASS_SET) {
		for(Class<?> c:cls.getInterfaces()) {
			if("org.smart4j.framework.interceptor.MyInterceptor".equals(c.getName())) {
				classSet.add(cls);
				break;
			}
		}
		
	}
	return classSet;
}*/
/**
 * 
 * @Title: getServiceClassSet 
 * @Description: 获取应用包名下所有的bean类(包括：service,controller等)
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getBeanClassSet(){
	return CLASS_SET;
}
/**
 * 
 * @Title: getClassSetBySuper 
 * @Description: 获取应用包下 某父类(接口) 的所有子类(实现类)
 * @param superClass
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
	Set<Class<?>> classSet=new HashSet<Class<?>>();
	for(Class<?> cls:CLASS_SET) {
		if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)) {
			classSet.add(cls);
		}
	}
	return classSet;
}
/**
 * 
 * @Title: getClassSetByAnnotation 
 * @Description: 获取应用包下 带有某注解的所有类
 * @param annotationClass
 * @return 参数说明
 * @return Set<Class<?>>    返回类型
 */
public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
	Set<Class<?>> classSet=new HashSet<Class<?>>();
	for(Class<?> cls:CLASS_SET) {
		if(cls.isAnnotationPresent(annotationClass)) {
			classSet.add(cls);
		}
	}
	return classSet;
}
}
