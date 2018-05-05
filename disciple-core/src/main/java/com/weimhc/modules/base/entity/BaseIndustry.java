/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 行业Entity
 * 
 * @author lc
 * @version 2016-06-03
 */
public class BaseIndustry extends BaseNameEntity<BaseIndustry> {

	private static final long serialVersionUID = 1L;

	/**
	 * 行业方向
	 * 
	 */
	private String direction;

	public BaseIndustry() {
		super();
	}

	public BaseIndustry(String id) {
		super(id);
	}

	/**
	 * 获取行业方向
	 * 
	 * @return 行业方向
	 */
	@Length(min = 0, max = 255)
	public String getDirection() {
		return direction;
	}

	/**
	 * 设置行业方向
	 * 
	 * @param direction
	 *            行业方向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

}