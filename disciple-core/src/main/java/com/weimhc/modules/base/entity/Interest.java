/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 兴趣 Entity
 * 
 * @author zsm
 * @version 2017-01-22
 */
public class Interest extends BaseNameEntity<Interest> {

	private static final long serialVersionUID = 1L;

	/**
	 * 兴趣分类
	 */
	private InterestCategory interestCategory;

	public Interest() {
		super();
	}

	public Interest(String id) {
		super(id);
	}

	/**
	 * @param interestCategory
	 */
	public Interest(InterestCategory interestCategory) {
		this.interestCategory = interestCategory;
	}

	/**
	 * 返回 兴趣分类
	 * 
	 * @return the interestCategory
	 */
	public InterestCategory getInterestCategory() {
		return interestCategory;
	}

	/**
	 * 设置 兴趣分类
	 * 
	 * @param interestCategory
	 *            the interestCategory to set
	 */
	public void setInterestCategory(InterestCategory interestCategory) {
		this.interestCategory = interestCategory;
	}

}