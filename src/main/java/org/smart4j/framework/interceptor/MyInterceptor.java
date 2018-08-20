package org.smart4j.framework.interceptor;


import org.smart4j.framework.bean.Param;

public interface MyInterceptor {
public void preHandler(Param parm);
public void postHandler();
}
