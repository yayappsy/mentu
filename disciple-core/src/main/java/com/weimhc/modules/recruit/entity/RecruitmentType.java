/**
 * 
 */
package com.weimhc.modules.recruit.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 职位类型Entity
 * @author lc
 * @version 2017-04-06
 */
public class RecruitmentType extends SortableEntity<RecruitmentType>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 职位类型名称
	 * 
	 */		
	private String name;	
	
	public RecruitmentType() {
		super();
	}

	public RecruitmentType(String id){
		super(id);
	}

	/**
	 * 获取职位类型名称
	 * 
	 * @return 职位类型名称
	 */	
	@Length(min=1, max=255)
	public String getName() {
		return name;
	}
	/**
	 * 设置职位类型名称
	 * 
	 * @param name
	 *            职位类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}