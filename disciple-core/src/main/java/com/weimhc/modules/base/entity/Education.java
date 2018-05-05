/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 学历Entity
 * 
 * @author lc
 * @version 2016-06-24
 */
public class Education extends BaseNameEntity<Education> {

	private static final long serialVersionUID = 1L;

	public Education() {
		super();
	}

	public Education(String id) {
		super(id);
	}

}