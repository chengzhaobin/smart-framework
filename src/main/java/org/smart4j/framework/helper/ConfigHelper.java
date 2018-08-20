package org.smart4j.framework.helper;

import java.util.Properties;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtils;

/**
 * 
 * @ClassName ConfigHelper  
 * @Description 属性文件 助手类 
 * @author chengzhb 
 * @date 2018年8月7日  
 *   
 */
public class ConfigHelper {
private static final Properties CONFIG_PROPS=PropsUtils.loadProps
		(ConfigConstant.CONFIG_FILE);
/**
 * 
 * @Title: getJdbcDriver 
 * @Description: 获取jdbc驱动 
 * @return 参数说明
 * @return String    返回类型
 */
public static String getJdbcDriver() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
}
/**
 * 
 * @Title: getJdbcUrl 
 * @Description: 获取jdbc url
 * @return 参数说明
 * @return String    返回类型
 */
public static String getJdbcUrl() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
}
/**
 * 
 * @Title: getJdbcUsername 
 * @Description: 获取jdbc 用户名
 * @return 参数说明
 * @return String    返回类型
 */
public static String getJdbcUsername() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
}
/**
 * 
 * @Title: getJdbcPassword 
 * @Description: 获取jdbc 密码
 * @return 参数说明
 * @return String    返回类型
 */
public static String getJdbcPassword() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
}
/**
 * 
 * @Title: getJdbcPassword 
 * @Description: 获取应用基础报名
 * @return 参数说明
 * @return String    返回类型
 */
public static String getAppBasePackage() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
}
/**
 * 
 * @Title: getJdbcPassword 
 * @Description: 获取应用 jsp 路径
 * @return 参数说明
 * @return String    返回类型
 */
public static String getAppJspPath() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
}
/**
 * 
 * @Title: getJdbcPassword 
 * @Description: 获取应用 静态资源路径
 * @return 参数说明
 * @return String    返回类型
 */
public static String getAppAssetPath() {
	return PropsUtils.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
}
}
