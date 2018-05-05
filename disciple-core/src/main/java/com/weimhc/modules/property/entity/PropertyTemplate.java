/**
 * 
 */
package com.weimhc.modules.property.entity;

import java.util.List;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 属性模板Entity
 * 
 * @author zsm
 * @version 2017-03-29
 */
public class PropertyTemplate extends BaseNameEntity<PropertyTemplate> {

	private static final long serialVersionUID = 1L;
	/**
	 * 属性列表
	 */
	private List<Property> propertyList;

	public PropertyTemplate() {
		super();
	}

	public PropertyTemplate(String id) {
		super(id);
	}

	/**
	 * 返回 属性列表
	 * 
	 * @return
	 */
	public List<Property> getPropertyList() {
		return propertyList;
	}

	/**
	 * 设置 属性列表
	 * 
	 * @param propertyList
	 */
	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

}