package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName ProxyChain  
 * @Description 代理链 
 * @author chengzhb 
 * @date 2018年8月13日  
 *   
 */
public class ProxyChain {
private  Class<?> targetClass;

private  Object targetObject;

private  Method targetMethod;

private  MethodProxy methodProxy;


private  Object[] methodParams;

private List<Proxy> proxyList=new ArrayList<Proxy>();

private int proxyIndex=0;

public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod,
		 Object[] methodParams,MethodProxy methodProxy,List<Proxy> proxyList) {
	super();
	this.targetClass = targetClass;
	this.targetObject = targetObject;
	this.targetMethod = targetMethod;
	this.methodProxy = methodProxy;
	this.methodParams = methodParams;
	this.proxyList = proxyList;
}

public Class<?> getTargetClass() {
	return targetClass;
}

public Method getTargetMethod() {
	return targetMethod;
}

public Object[] getMethodParams() {
	return methodParams;
}

public Object doProxyChain() throws Throwable{
	Object methodResult;
	if(proxyIndex < proxyList.size()) {
		methodResult=proxyList.get(proxyIndex++).doProxy(this);
	}else {
		methodResult=methodProxy.invokeSuper(targetObject, methodParams);
	}
	return methodResult;
}
}
