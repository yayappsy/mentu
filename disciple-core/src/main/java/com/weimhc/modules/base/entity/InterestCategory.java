/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 兴趣分类 Entity
 * 
 * @author zsm
 * @version 2017-01-22
 */
public class InterestCategory extends BaseNameEntity<InterestCategory> {

	private static final long serialVersionUID = 1L;

	public InterestCategory() {
		super();
	}

	public InterestCategory(String id) {
		super(id);
	}

}