/**
 * 
 */
package com.weimhc.framework.persistence;

import org.hibernate.validator.constraints.Length;

/**
 * 基础数据
 * 
 * 固定拥有名字、描述是数据
 * 
 * @author zsm
 * @version 2017-03-06
 */
public abstract class BaseNameEntity<T> extends SortableEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 * 
	 */
	private String name;

	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 是否显示
	 */
	protected Boolean isShow;

	public BaseNameEntity() {
		super();
	}

	public BaseNameEntity(String id) {
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
	 * 返回是否推荐
	 * 
	 * @return the isShow
	 */
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否推荐
	 * 
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/* (non-Javadoc)
	 * @see com.weimhc.framework.persistence.SortableEntity#setDefaultValue()
	 */
	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		if (this.getIsShow() == null) {
			this.setIsShow(Boolean.TRUE);
		}
	}

}