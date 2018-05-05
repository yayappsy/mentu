/**
 * 
 */
package com.weimhc.modules.property.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.weimhc.framework.persistence.BaseNameEntity;
import com.weimhc.modules.base.entity.ShowType;

/**
 * 商品扩展属性Entity
 * 
 * @author zsm
 * @version 2016-02-01
 */
public class Property extends BaseNameEntity<Property> {

	private static final long serialVersionUID = 1L;
	/**
	 * 属性模板
	 * 
	 */
	private PropertyTemplate propertyTemplate;

	/**
	 * 扩展属性值集合
	 * 
	 */
	private String propertyValues;
	/**
	 * 展示类型
	 * 
	 */
	private ShowType showType;
	/**
	 * 是否搜索属性
	 * 
	 */
	private Boolean isSearch;
	/**
	 * 是否必选
	 * 
	 */
	private Boolean isRequired;

	/**
	 * 选择值，只有有限的展示类型才会有
	 */
	private List<PropertyValue> propertyValueList = Lists.newArrayList();

	public Property() {
		super();
	}

	public Property(String id) {
		super(id);
	}

	public Property(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

	/**
	 * 获取属性模板
	 * 
	 * @return 属性模板
	 */
	public PropertyTemplate getPropertyTemplate() {
		return propertyTemplate;
	}

	/**
	 * 设置属性模板id
	 * 
	 * @param propertyTemplate
	 *            商品类型
	 */
	public void setPropertyTemplate(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

	/**
	 * 获取扩展属性值集合
	 * 
	 * @return 扩展属性值集合
	 */
	@Length(min = 0, max = 255)
	public String getPropertyValues() {
		return propertyValues;
	}

	/**
	 * 设置扩展属性值集合
	 * 
	 * @param propertyValues
	 *            扩展属性值集合
	 */
	public void setPropertyValues(String propertyValues) {
		this.propertyValues = propertyValues;
	}

	/**
	 * 获取展示类型
	 * 
	 * @return 展示类型
	 */
	public ShowType getShowType() {
		return showType;
	}

	/**
	 * 设置展示类型
	 * 
	 * @param showType
	 *            展示类型
	 */
	public void setShowType(ShowType showType) {
		this.showType = showType;
	}

	/**
	 * 获取是否搜索属性
	 * 
	 * @return 是否搜索属性
	 */
	public Boolean getIsSearch() {
		return isSearch;
	}

	/**
	 * 设置是否搜索属性
	 * 
	 * @param isSearch
	 *            是否搜索属性
	 */
	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * 获取是否必选
	 * 
	 * @return 是否必选
	 */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * 设置是否必选
	 * 
	 * @param isRequired
	 *            是否必选
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * 返回 属性选项值列表
	 * 
	 * @return the propertyValueList
	 */
	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}

	/**
	 * 设置 属性选项值列表
	 * 
	 * @param propertyValueList
	 *            the propertyValueList to set
	 */
	public void setPropertyValueList(List<PropertyValue> propertyValueList) {
		this.propertyValueList = propertyValueList;
	}

}