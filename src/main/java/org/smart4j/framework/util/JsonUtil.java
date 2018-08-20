package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName JsonUtil  
 * @Description json工具类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class JsonUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
	/**
	 * 
	 * @Title: toJson 
	 * @Description: 将POJO 转json
	 * @param obj
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public static <T> String toJson(T obj) {
		String json;
		try {
			json=OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("convert POJO to JSON failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	/**
	 * 
	 * @Title: toJson 
	 * @Description: 将json 转POJO
	 * @param obj
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public static <T> T fromJson(String json,Class<T> type) {
		T pojo;
		try {
			pojo=OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
