package org.smart4j.framework.web;

import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;

public interface RequestMapping {
	public  Object doAction(Handler handler,Param param);
}
