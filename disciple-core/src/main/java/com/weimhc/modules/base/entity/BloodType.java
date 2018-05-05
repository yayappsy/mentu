/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 血型Entity
 * 
 * @author lc
 * @version 2016-06-24
 */
public class BloodType extends BaseNameEntity<BloodType> {

	private static final long serialVersionUID = 1L;
	/**
	 * 血型类型(ABO血型，RH血型）
	 * 
	 */
	private String type;

	public BloodType() {
		super();
	}

	public BloodType(String id) {
		super(id);
	}

	/**
	 * 获取血型类型(ABO血型，RH血型）
	 * 
	 * @return 血型类型(ABO血型，RH血型）
	 */
	@Length(min = 1, max = 255)
	public String getType() {
		return type;
	}

	/**
	 * 设置血型类型(ABO血型，RH血型）
	 * 
	 * @param type
	 *            血型类型(ABO血型，RH血型）
	 */
	public void setType(String type) {
		this.type = type;
	}

}