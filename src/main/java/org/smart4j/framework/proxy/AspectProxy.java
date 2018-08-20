package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName AspectProxy  
 * @Description 切面代理 
 * @author chengzhb 
 * @date 2018年8月13日  
 *   
 */
public abstract class AspectProxy implements Proxy{
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable{
		Object result=null;
		Class<?> cls=proxyChain.getTargetClass();
		Method method=proxyChain.getTargetMethod();
		Object[] params=proxyChain.getMethodParams();
		if(intecept(cls, method, params)) {
			before(cls, method, params);
			result=proxyChain.doProxyChain();
			after(cls, method, params);
		}else {
			result=proxyChain.doProxyChain();
		}
		return result;
	}
	public  void before(Class<?> cls, Method method, Object[] params) {};
	public  void after(Class<?> cls, Method method, Object[] params) {};
	public boolean intecept(Class<?> cls,Method method,Object[] params) throws Throwable{
		return true;
	}
}
