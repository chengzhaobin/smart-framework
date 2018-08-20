package org.smart4j.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * 
 * @ClassName CollectionUtil  
 * @Description 集合工具类 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class CollectionUtil {
public static boolean isEmpty(Collection<?> collection) {
	return CollectionUtils.isEmpty(collection);
}
public static boolean isNotEmpty(Collection<?> collection) {
	return !isEmpty(collection);
}
public static boolean isEmpty(Map<?,?> map) {
	return MapUtils.isEmpty(map);
}
public static boolean isNotEmpty(Map<?,?> map) {
	return !isEmpty(map);
}
}
