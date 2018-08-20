package org.smart4j.framework;

import org.smart4j.framework.helper.AopHelper;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.interceptor.InterceptHandler;
import org.smart4j.framework.util.ClassUtil;

/**
 * 
 * @ClassName HelperLoader  
 * @Description 加载相应的Helper类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class HelperLoader {
public static void init() {
	Class<?>[] classList= {
			ClassHelper.class,
			BeanHelper.class,
			AopHelper.class,
			IocHelper.class,
			ControllerHelper.class
			};
	for(Class<?> cls:classList) {
		ClassUtil.loadClass(cls.getName(),true);
	}
}
}
