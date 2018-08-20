package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName ProxyManager  
 * @Description 代理管理器 
 * @author chengzhb 
 * @date 2018年8月13日  
 *   
 */
public class ProxyManager {
@SuppressWarnings("unchecked")
public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList) {
	return (T) Enhancer.create(targetClass,new ProxyHandler(targetClass, proxyList));
	
}
static class ProxyHandler implements MethodInterceptor{
	private Class<?> targetClass;
	private List<Proxy> proxyList;
	public ProxyHandler(Class<?> targetClass,List<Proxy> proxyList) {
		this.targetClass=targetClass;
		this.proxyList=proxyList;
	}
	@Override
	public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy)
			throws Throwable {
		return new ProxyChain(targetClass, targetObject, targetMethod, methodParams,methodProxy, proxyList).doProxyChain();
	}
}
}
