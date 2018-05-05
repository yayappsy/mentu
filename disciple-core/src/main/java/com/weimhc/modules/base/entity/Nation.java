/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 民族Entity
 * 
 * @author lc
 * @version 2017-01-05
 */
public class Nation extends BaseNameEntity<Nation> {

	private static final long serialVersionUID = 1L;

	public Nation() {
		super();
	}

	public Nation(String id) {
		super(id);
	}

}