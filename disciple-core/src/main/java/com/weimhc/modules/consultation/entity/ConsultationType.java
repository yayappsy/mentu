/**
 * 
 */
package com.weimhc.modules.consultation.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 咨询类型Entity
 * 
 * @author zsm
 * @version 2017-01-05
 */
public class ConsultationType extends BaseNameEntity<ConsultationType> {

	private static final long serialVersionUID = 1L;

	public ConsultationType() {
		super();
	}

	public ConsultationType(String id) {
		super(id);
	}

}