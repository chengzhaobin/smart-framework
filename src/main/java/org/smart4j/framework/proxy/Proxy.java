package org.smart4j.framework.proxy;

public interface Proxy {
Object doProxy(ProxyChain proxyChain)throws Throwable;
}
