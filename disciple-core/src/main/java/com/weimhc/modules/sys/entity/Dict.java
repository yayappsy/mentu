/**
 * 
 */
package com.weimhc.modules.sys.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 字典Entity
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class Dict extends DataEntity<Dict> {

	private static final long serialVersionUID = 1L;
	private String value; // 数据值
	private String label; // 标签名
	private String type; // 类型
	private String description;// 描述
	private Integer sort; // 排序
	private String parentId;//父Id

	public Dict() {
		super();
	}

	public Dict(String id) {
		super(id);
	}

	/**
	 * 获取数据值
	 * 
	 * @return 数据值
	 */
	@XmlAttribute
	@Length(min = 1, max = 100)
	public String getValue() {
		return value;
	}

	/**
	 * 设置数据值
	 * 
	 * @param value
	 *            数据值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取标签名
	 * 
	 * @return 标签名
	 */
	@XmlAttribute
	@Length(min = 1, max = 100)
	public String getLabel() {
		return label;
	}

	/**
	 * 设置标签名
	 * 
	 * @param label
	 *            标签名
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Length(min = 1, max = 100)
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
	@XmlAttribute
	@Length(min = 1, max = 100)
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
	 * 获取排序（升序）
	 * 
	 * @return 排序（升序）
	 */
	@NotNull
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序（升序）
	 * 
	 * @param sort
	 *            排序（升序）
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Length(min = 1, max = 100)
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return label;
	}

}