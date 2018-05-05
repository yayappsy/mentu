/**
 * 
 */
package com.weimhc.modules.remind.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 提醒方式Entity
 * 
 * @author zsm
 * @version 2017-03-23
 */
public class RemindWay extends BaseNameEntity<RemindWay> {

	private static final long serialVersionUID = 1L;
	/**
	 * 字数最大长度
	 * 
	 */
	private Integer maxLength;

	public RemindWay() {
		super();
	}

	public RemindWay(String id) {
		super(id);
	}

	/**
	 * 获取字数最大长度
	 * 
	 * @return 字数最大长度
	 */
	@Length(min = 0, max = 11)
	public Integer getMaxLength() {
		return maxLength;
	}

	/**
	 * 设置字数最大长度
	 * 
	 * @param maxLength
	 *            字数最大长度
	 */
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

}