/**
 * 
 */
package com.weimhc.modules.product.entity;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.property.entity.Property;
import com.weimhc.modules.property.entity.PropertyTemplate;

/**
 * 商品属性Entity
 * @author zsm
 * @version 2016-02-04
 */
public class ProductProperty extends DataEntity<ProductProperty> {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品
	 */
	private Product product;
	/**
	 * 扩展属性
	 */
	private Property property;
	/**
	 * 属性名称
	 */
	private String propertyName;
	/**
	 * 属性模板
	 */
	private PropertyTemplate propertyTemplate;
	/**
	 * 属性值id集合
	 */
	private String propertyValueIds;

	/**
	 * 属性值内容集合
	 */
	private String propertyValueNames;

	public ProductProperty() {
		super();
	}

	public ProductProperty(String id) {
		super(id);
	}

	public ProductProperty(Product product) {
		this.product = product;
	}

	/**
	 * 获取商品
	 * 
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 设置商品
	 * 
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 获取属性
	 * 
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * 设置属性
	 * 
	 * @param property
	 *            the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}

	/**
	 * 获取扩展属性
	 * 
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置扩展属性
	 * 
	 * @param propertyName
	 *            the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 返回 模板属性
	 * 
	 * @return the propertyTemplate
	 */
	public PropertyTemplate getPropertyTemplate() {
		return propertyTemplate;
	}

	/**
	 * 设置 模板属性
	 * 
	 * @param propertyTemplate
	 *            the propertyTemplate to set
	 */
	public void setPropertyTemplate(PropertyTemplate propertyTemplate) {
		this.propertyTemplate = propertyTemplate;
	}

	/**
	 * 获取扩展属性值ids
	 * 
	 * @return the propertyValueIds
	 */
	public String getPropertyValueIds() {
		return propertyValueIds;
	}

	/**
	 * 设置扩展属性值ids
	 * 
	 * @param propertyValueIds
	 *            the propertyValueIds to set
	 */
	public void setPropertyValueIds(String propertyValueIds) {
		this.propertyValueIds = propertyValueIds;
	}

	/**
	 * 获取扩展属性值列表
	 * 
	 * @return the propertyValueNames
	 */
	public String getPropertyValueNames() {
		return propertyValueNames;
	}

	/**
	 * 设置扩展属性值列表
	 * 
	 * @param propertyValueNames
	 *            the propertyValueNames to set
	 */
	public void setPropertyValueNames(String propertyValueNames) {
		this.propertyValueNames = propertyValueNames;
	}

}