package org.smart4j.framework.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.smart4j.framework.annotation.Intercept;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ReflectionUtil;


public final class InterceptHandler {
	private static List<MyInterceptor> interceptorList;
static {
	interceptorList=new ArrayList<MyInterceptor>();
	Set<Class<?>> interceptClassSet = ClassHelper.getClassSetBySuper(MyInterceptor.class);
	for(Class<?> cls:interceptClassSet) {
		MyInterceptor newInstance = (MyInterceptor) ReflectionUtil.newInstance(cls);
		interceptorList.add(newInstance);
	}
}
public static List<MyInterceptor> getInterceptorList() {
	return interceptorList;
}
public static void setInterceptorList(List<MyInterceptor> interceptorList) {
	InterceptHandler.interceptorList = interceptorList;
}

}
