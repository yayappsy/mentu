/**
 * 
 */
package com.weimhc.modules.property.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 扩展属性选项Entity
 * 
 * @author zsm
 * @version 2017-03-29
 */
public class PropertyValue extends SortableEntity<PropertyValue> {

	private static final long serialVersionUID = 1L;
	/**
	 * 扩展属性值
	 * 
	 */
	private String name;
	/**
	 * 扩展属性
	 * 
	 */
	private Property property;
	/**
	 * 扩展属性名称
	 * 
	 */
	private String propertyName;
	/**
	 * 属性模板
	 * 
	 */
	private PropertyTemplate propertyTemplate;

	public PropertyValue() {
		super();
	}

	public PropertyValue(String id) {
		super(id);
	}

	public PropertyValue(Property property) {
		this.property = property;
	}

	/**
	 * 获取扩展属性值
	 * 
	 * @return 扩展属性值
	 */
	@Length(min = 0, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置扩展属性值
	 * 
	 * @param name
	 *            扩展属性值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取扩展属性
	 * 
	 * @return 扩展属性
	 */
	@NotNull()
	public Property getProperty() {
		return property;
	}

	/**
	 * 设置扩展属性
	 * 
	 * @param property
	 *            扩展属性
	 */
	public void setPropertyId(Property property) {
		this.property = property;
	}

	/**
	 * 获取扩展属性名称
	 * 
	 * @return 扩展属性名称
	 */
	@Length(min = 0, max = 255)
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置扩展属性名称
	 * 
	 * @param propertyName
	 *            扩展属性名称
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 获取属性模板
	 * 
	 * @return 属性模板
	 */
	@NotNull
	public PropertyTemplate getPropertyTemplate() {
		return propertyTemplate;
	}

	/**
	 * 设置属性模板
	 * 
	 * @param propertyTemplate
	 *            属性模板
	 */
	public void setPropertyTemplate(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

}