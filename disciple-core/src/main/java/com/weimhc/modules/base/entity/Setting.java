/**
 * 
 */
package com.weimhc.modules.base.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 网站设置Entity
 * 
 * @author zsm
 * @version 2016-01-29
 */
public class Setting extends DataEntity<Setting> {

	private static final long serialVersionUID = 1L;
	private String name; // 名称
	private String label; // 标签
	private String type; // 类型
	private String description; // 描述
	private String value; // 值
	private Integer sort; // 排序
	private Boolean isEditable;// 可以编辑
	private Boolean isRequired;// 是否必填
	private String javaType; // JAVA类型
	private ShowType showType;

	private List<Setting> settings;

	public Setting() {
		super();
	}

	public Setting(String id) {
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 50)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取标签
	 * 
	 * @return 标签
	 */
	@Length(min = 0, max = 255)
	public String getLabel() {
		return label;
	}

	/**
	 * 设置标签
	 * 
	 * @param label
	 *            标签
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Length(min = 1, max = 50)
	public String getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 100)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取值
	 * 
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置值
	 * 
	 * @param value
	 *            值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the settings
	 */
	@JsonIgnore
	public List<Setting> getSettings() {
		return settings;
	}

	/**
	 * 用于批量更改设置
	 * 
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取是否可编辑
	 * 
	 * @return the isEditable
	 */
	public Boolean getIsEditable() {
		return isEditable;
	}

	/**
	 * 设置是否可编辑
	 * 
	 * @param isEditable
	 *            the isEditable to set
	 */
	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * 获取是否必填
	 * 
	 * @return the isRequired
	 */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * 设置是否必填
	 * 
	 * @param isRequired
	 *            the isRequired to set
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * 获取JAVA类型
	 * 
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}

	/**
	 * 设置JAVA类型
	 * 
	 * @param javaType
	 *            the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * 获取字段生成方案
	 * 
	 * @return the showType
	 */
	public ShowType getShowType() {
		return showType;
	}

	/**
	 * 设置字段生成方案
	 * 
	 * @param showType
	 *            the showType to set
	 */
	public void setShowType(ShowType showType) {
		this.showType = showType;
	}

}