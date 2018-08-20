package org.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName ClassUtil  
 * @Description 类 操作工具类 
 * @author chengzhb 
 * @date 2018年8月7日  
 *   
 */
public final class ClassUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(ClassUtil.class);
	/**
	 * 
	 * @Title: getClassLoader 
	 * @Description: 获取类加载器
	 * @return 参数说明
	 * @return ClassLoader    返回类型
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> cls = null;
		try {
			cls=Class.forName(className,isInitialized,getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls=getClassLoader().getResources(packageName.replace(".","/"));
			while(urls.hasMoreElements()) {
				URL url=urls.nextElement();
				if(null!=url) {
					String protocol=url.getProtocol();
					if("file".equals(protocol)) {
						String packagePath=url.getPath().replaceAll("20%","");
						addClass(classSet,packagePath,packageName);
					}else if("jar".equals(protocol)) {
						JarURLConnection jarURLConnection=(JarURLConnection) url.openConnection();
						if(jarURLConnection!=null) {
							JarFile jarFile=jarURLConnection.getJarFile();
							if(jarFile!=null) {
								Enumeration<JarEntry> jarEntries=jarFile.entries();
								while (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
									String jarEntryName=jarEntry.getName();
									if(jarEntryName.endsWith(".class")) {
										String className=jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
										doAddClass(classSet,className);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("get class set failure",e);
			throw new RuntimeException(e);
		}
		return classSet;
	}
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files=new File(packagePath).listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")|| file.isDirectory());
			}
		});
		
		for(File file:files) {
			String fileName=file.getName();
			if(file.isFile()) {
				String className=fileName.substring(0,fileName.lastIndexOf("."));
				if(StringUtils.isNotEmpty(packageName)) {
					className=packageName+"."+className;
				}
				doAddClass(classSet, className);
			}else {
				String subPackagePath=fileName;
				if(StringUtils.isNotEmpty(packagePath)) {
					subPackagePath=packagePath+"/"+subPackagePath;
				}
				String subPackageName=fileName;
				if(StringUtils.isNotEmpty(packageName)) {
					subPackageName=packageName+"."+subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}
private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls=loadClass(className,false);
		classSet.add(cls);
	}
}
