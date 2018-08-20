package org.smart4j.framework.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName Data  
 * @Description 返回数据对象 
 * @author chengzhb 
 * @date 2018年8月8日  
 *   
 */
public class Data implements Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -9206059721111647845L;
	/**
	 * 模型数据
	 */
private Object model;

	public Data(Object model) {
		this.model = model;
	}

	public Object getModel() {
		return model;
	}
}
