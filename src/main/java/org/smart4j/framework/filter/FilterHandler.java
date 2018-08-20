package org.smart4j.framework.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ReflectionUtil;

public class FilterHandler {
	private static List<MyFilter> filterList;
static {
	filterList=new ArrayList<MyFilter>();
	Set<Class<?>> filterClassSet = ClassHelper.getClassSetBySuper(MyFilter.class);
	for(Class<?> cls:filterClassSet) {
		MyFilter newInstance = (MyFilter) ReflectionUtil.newInstance(cls);
		filterList.add(newInstance);
	}
}
public static List<MyFilter> getFilterList() {
	return filterList;
}
public static void setFilterList(List<MyFilter> filterList) {
	FilterHandler.filterList = filterList;
}

}
