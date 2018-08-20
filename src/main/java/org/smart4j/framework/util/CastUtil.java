package org.smart4j.framework.util;

/**
 * 
 * @ClassName CastUtil  
 * @Description 转型操作工具类 
 * @author chengzhb 
 * @date 2018年8月7日  
 *   
 */
public final class CastUtil {
	/**
	 * 
	 * @Title: castString 
	 * @Description:转为String类型
	 * @param obj
	 * @return 参数说明
	 * @return String    返回类型
	 */
public static String castString(Object obj) {
	return CastUtil.castString(obj,"");
}
/**
 * 
 * @Title: castString 
 * @Description: 转为String类型（提供默认值）
 * @param obj
 * @param defaultValue
 * @return 参数说明
 * @return String    返回类型
 */
public static String castString(Object obj,String defaultValue) {
	return obj != null ?String.valueOf(obj) :defaultValue;
}
public static Double castDouble(Object obj) {
	return CastUtil.castDouble(obj,0);
}
public static Double castDouble(Object obj, double defaultValue) {
	double doubleValue=defaultValue;
	if(obj!=null) {
		String strValue=castString(obj);
		if(StringUtil.isNotEmpty(strValue)) {
			try{
				doubleValue=Double.parseDouble(strValue);
			}catch(NumberFormatException e) {
				doubleValue=defaultValue;
			}
		}
	}
	return doubleValue;
}
public static Long castLong(Object obj) {
	return CastUtil.castLong(obj,0);
}
private static Long castLong(Object obj, long defaultValue) {
	long longValue=defaultValue;
	if(obj!=null) {
		String strValue=castString(obj);
		if(StringUtil.isNotEmpty(strValue)) {
			try{
				longValue=Long.parseLong(strValue);
			}catch(NumberFormatException e) {
				longValue=defaultValue;
			}
		}
	}
	return longValue;
}

public static int castInt(Object obj) {
	return CastUtil.castInt(obj,0);
}
private static int castInt(Object obj, int defaultValue) {
	int intValue=defaultValue;
	if(obj!=null) {
		String strValue=castString(obj);
		if(StringUtil.isNotEmpty(strValue)) {
			try{
				intValue=Integer.parseInt(strValue);
			}catch(NumberFormatException e) {
				intValue=defaultValue;
			}
		}
	}
	return intValue;
}

public static boolean castBoolean(Object obj) {
	return CastUtil.castBoolean(obj,false);
}
private static boolean castBoolean(Object obj, boolean defaultValue) {
	boolean booleanValue=defaultValue;
	if(obj!=null) {
		booleanValue=Boolean.parseBoolean(castString(obj));
	}
	
	return booleanValue;
}
}
