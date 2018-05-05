/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 课程时间配置Entity
 * 
 * @author lc
 * @version 2017-06-12
 */
public class CourseTime extends SortableEntity<CourseTime> {

	private static final long serialVersionUID = 1L;
	/**
	 * 时间简称
	 * 
	 */
	private String name;
	/**
	 * 开始时间
	 * 
	 */
	private String beginTime;
	/**
	 * 结束时间
	 * 
	 */
	private String endTime;

	public CourseTime() {
		super();
	}

	public CourseTime(String id) {
		super(id);
	}

	/**
	 * 获取时间简称
	 * 
	 * @return 时间简称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置时间简称
	 * 
	 * @param name
	 *            时间简称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param beginTime
	 *            开始时间
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endTime
	 *            结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}