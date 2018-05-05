/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 工作年限Entity
 * @author lc
 * @version 2017-04-06
 */
public class WorkingYears extends SortableEntity<WorkingYears>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 工作年限名称
	 * 
	 */		
	private String name;	
	
	public WorkingYears() {
		super();
	}

	public WorkingYears(String id){
		super(id);
	}

	/**
	 * 获取工作年限名称
	 * 
	 * @return 工作年限名称
	 */	
	@Length(min=1, max=255)
	public String getName() {
		return name;
	}
	/**
	 * 设置工作年限名称
	 * 
	 * @param name
	 *            工作年限名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}