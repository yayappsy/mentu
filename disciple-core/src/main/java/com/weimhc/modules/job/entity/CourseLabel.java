/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 课程标签Entity
 * @author cwl
 * @version 2017-12-31
 */
public class CourseLabel extends SortableEntity<CourseLabel>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 标签名称
	 * 
	 */		
	private String name;	
	
	public CourseLabel() {
		super();
	}

	public CourseLabel(String id){
		super(id);
	}

	/**
	 * 获取标签名称
	 * 
	 * @return 标签名称
	 */	
	@Length(min=1, max=255)
	public String getName() {
		return name;
	}
	/**
	 * 设置标签名称
	 * 
	 * @param name
	 *            标签名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}