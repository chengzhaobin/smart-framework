package org.smart4j.framework.bean;

import java.util.Map;

/**
 * 
 * @ClassName View  
 * @Description 返回视图对象 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class View {
	/**
	 * 视图路径
	 */
private String path;
private Map<String,Object> model;
public View(String path) {
	super();
	this.path = path;
}
public String getPath() {
	return path;
}
public Map<String, Object> getModel() {
	return model;
}
public View addModel(String key,Object value) {
	model.put(key, value);
	return this;
}
}
