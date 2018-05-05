/**
 * 
 */
package com.weimhc.modules.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 活动类型Entity
 * @author zsm
 * @version 2017-04-24
 */
public class ActivityType extends SortableEntity<ActivityType>{
	
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
	
	public ActivityType() {
		super();
	}

	public ActivityType(String id){
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */	
	@Length(min=1, max=255)
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
	@Length(min=0, max=255)
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
	
}