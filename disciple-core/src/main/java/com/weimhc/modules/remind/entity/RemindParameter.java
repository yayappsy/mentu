/**
 * 
 */
package com.weimhc.modules.remind.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 模板可选参数Entity
 * 
 * @author zsm
 * @version 2017-04-14
 */
public class RemindParameter extends SortableEntity<RemindParameter> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 代码
	 * 
	 */
	private String code;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 参数对应的实体名称
	 * 
	 */
	private EntityName entityName;
	/**
	 * 所对应的字段
	 * 
	 */
	private String propertyName;

	public RemindParameter() {
		super();
	}

	public RemindParameter(String id) {
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 255)
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
	 * 获取代码
	 * 
	 * @return 代码
	 */
	@Length(min = 1, max = 64)
	public String getCode() {
		return code;
	}

	/**
	 * 设置代码
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
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
	 * 获取参数对应的实体名称
	 * 
	 * @return 参数对应的实体名称
	 */
	public EntityName getEntityName() {
		return entityName;
	}

	/**
	 * 设置参数对应的实体名称
	 * 
	 * @param entityName
	 *            参数对应的实体名称
	 */
	public void setEntityName(EntityName entityName) {
		this.entityName = entityName;
	}

	/**
	 * 获取 所对应的字段
	 * 
	 * @return 所对应的字段
	 */
	@Length(min = 0, max = 255)
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置 所对应的字段
	 * 
	 * @param propertyName
	 *            所对应的字段
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}