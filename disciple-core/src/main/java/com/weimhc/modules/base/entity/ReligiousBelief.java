/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 宗教信仰Entity
 * 
 * @author lc
 * @version 2017-01-05
 */
public class ReligiousBelief extends BaseNameEntity<ReligiousBelief> {

	private static final long serialVersionUID = 1L;

	public ReligiousBelief() {
		super();
	}

	public ReligiousBelief(String id) {
		super(id);
	}

}