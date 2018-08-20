package org.smart4j.framework.filter;

import java.util.List;

public class FilterChain {
private List<MyFilter> filterList;
private int posit=0;
public void addFilter(List<MyFilter> filter) {
	filterList=filter;
}
public void doFilterChain() {
	if(posit<filterList.size()) {
		filterList.get(posit++).doFilter(this);
	}
}
}
