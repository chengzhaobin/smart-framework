package org.smart4j.framework.util;

import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @ClassName ArrayUtil  
 * @Description 数组工具类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class ArrayUtil {
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}
}
