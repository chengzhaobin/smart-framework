package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;


/**
 * 
 * @ClassName IocHelper  
 * @Description 依赖注入助手类
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public final class IocHelper {
static {
	//获取所有的bean类与bean实例之间的映射关系(简称Bean Map)
	Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
	if(CollectionUtil.isNotEmpty(beanMap)) {
		//遍历 bean map
		for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()) {
			Class<?> beanClass=beanEntry.getKey();
			Object beanInstance=beanEntry.getValue();
			Field[] beanFields =beanClass.getDeclaredFields();
			if(ArrayUtil.isNotEmpty(beanFields)) {
				for(Field beanField:beanFields) {
					if(beanField.isAnnotationPresent(Inject.class)) {
						Class<?> beanFieldClass = beanField.getType();
						Object beanFieldInstance=beanMap.get(beanFieldClass);
						if(beanFieldInstance!=null) {
							ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
						}
					}
				}
			}
			
		}
	}
}
}
