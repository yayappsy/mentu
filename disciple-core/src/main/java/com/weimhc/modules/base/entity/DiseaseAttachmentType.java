/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 疾病附件类型 Entity
 * 
 * @author lc
 * @version 2017-01-05
 */
public class DiseaseAttachmentType
		extends BaseNameEntity<DiseaseAttachmentType> {

	private static final long serialVersionUID = 1L;

	public DiseaseAttachmentType() {
		super();
	}

	public DiseaseAttachmentType(String id) {
		super(id);
	}

}