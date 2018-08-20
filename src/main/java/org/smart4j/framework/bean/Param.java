package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

/**
 * 
 * @ClassName Param  
 * @Description 请求参数对象 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class Param {
private Map<String,Object> paramMap;
public Param(Map<String, Object> paramMap) {
	this.paramMap = paramMap;
}

public Param() {
	super();
}

public Map<String, Object> getParamMap() {
	return paramMap;
}
public long getLong(String name) {
	return CastUtil.castLong(paramMap.get(name));
}
public String getString(String name) {
	return CastUtil.castString(paramMap.get(name));
}
}
