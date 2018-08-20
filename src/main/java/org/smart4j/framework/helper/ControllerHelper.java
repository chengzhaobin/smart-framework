package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

/**
 * 
 * @ClassName ControllerHelper  
 * @Description 控制器助手类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class ControllerHelper {
private static final Map<Request,Handler> ACTION_MAP=
	new HashMap<Request, Handler>();
	static {
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)) {
			for(Class<?> controllerClass:controllerClassSet) {
				//获取Controller类中定义的方法
				Method[] methods=controllerClass.getDeclaredMethods();
					if(ArrayUtil.isNotEmpty(methods)) {
						for(Method method:methods) {
							if(method.isAnnotationPresent(Action.class)) {
								Action action = method.getAnnotation(Action.class);
								String mappering=action.value();
								//验证URL规则
								//if(mappering.matches("\\W+:/\\W*")) {
									String[] array=mappering.split(":");
									if(ArrayUtil.isNotEmpty(array) && array.length==2) {
										//获取请求方法与请求路径
										String requestMethod=array[0];
										String requestPath=array[1];
										Request request=new Request(requestMethod, requestPath);
										Handler handler=new Handler(controllerClass, method);
										//初始化 Action Map
										ACTION_MAP.put(request, handler);
									}
									
							//	}
							}
						}
					}
			}
		}
	}
	/**
	 * 
	 * @Title: getHandler 
	 * @Description:获取handler
	 * @param requestMethod
	 * @param requestPath
	 * @return 参数说明
	 * @return Handler    返回类型
	 */
	public static Handler getHandler(String requestMethod,String requestPath) {
		Request request=new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
