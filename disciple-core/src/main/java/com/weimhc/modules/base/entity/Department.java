/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 工作部门Entity
 * @author lc
 * @version 2017-04-06
 */
public class Department extends SortableEntity<Department>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 工作部门名称
	 * 
	 */		
	private String name;	
	
	public Department() {
		super();
	}

	public Department(String id){
		super(id);
	}

	/**
	 * 获取工作部门名称
	 * 
	 * @return 工作部门名称
	 */	
	@Length(min=1, max=255)
	public String getName() {
		return name;
	}
	/**
	 * 设置工作部门名称
	 * 
	 * @param name
	 *            工作部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}