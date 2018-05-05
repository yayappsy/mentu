/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 身体部位细节Entity
 * 
 * @author zsm
 * @version 2017-01-22
 */
public class BodyPartItem extends BaseNameEntity<BodyPartItem> {

	private static final long serialVersionUID = 1L;
	/**
	 * 部位
	 * 
	 */
	private BodyPart bodyPart;

	public BodyPartItem() {
		super();
	}

	public BodyPartItem(String id) {
		super(id);
	}

	/**
	 * 获取部位id
	 * 
	 * @return 部位id
	 */
	public BodyPart getBodyPart() {
		return bodyPart;
	}

	/**
	 * 设置部位
	 * 
	 * @param bodyPart
	 *            部位
	 */
	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

}