package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName CodecUtil  
 * @Description 编码解码操作工具类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class CodecUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(CodecUtil.class);
	/**
	 * 
	 * @Title: encodeURL 
	 * @Description: 将URL编码
	 * @param source
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public static String encodeURL(String source) {
		String target;
		try {
			target=URLEncoder.encode(source,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	/**
	 * 
	 * @Title: encodeURL 
	 * @Description: 将URL编码
	 * @param source
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public static String decodeURL(String source) {
		String target;
		try {
			target=URLDecoder.decode(source,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
}
