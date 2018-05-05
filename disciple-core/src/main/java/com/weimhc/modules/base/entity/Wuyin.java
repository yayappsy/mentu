/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 五音
 * 
 * @author zsm
 * @version 2017-03-26
 */
public class Wuyin extends BaseNameEntity<Wuyin> {

	private static final long serialVersionUID = 1L;
	/**
	 * 代码
	 */
	private String code;

	public Wuyin() {
		super();
	}

	public Wuyin(String id) {
		super(id);
	}

	/**
	 * 五音的代码
	 * 
	 * @return code 五音的代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 五音的代码
	 * 
	 * @param code
	 *            五音的代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

}