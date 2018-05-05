/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 疾病Entity
 * 
 * @author lc
 * @version 2017-01-06
 */
public class Disease extends BaseNameEntity<Disease> {

	private static final long serialVersionUID = 1L;

	public Disease() {
		super();
	}

	public Disease(String id) {
		super(id);
	}

}