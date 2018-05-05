/**
 * 
 */
package com.weimhc.modules.base.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 身体部位Entity
 * 
 * @author zsm
 * @version 2017-01-22
 */
public class BodyPart extends BaseNameEntity<BodyPart> {

	private static final long serialVersionUID = 1L;

	/**
	 * 身体部位分类
	 */
	private BodyCategory bodyCategory;

	/**
	 * 部位细节
	 */
	private List<BodyPartItem> bodyPartyItems = Lists.newArrayList();

	public BodyPart() {
		super();
	}

	public BodyPart(String id) {
		super(id);
	}

	/**
	 * 返回 部位细节
	 * 
	 * @return the bodyPartyItems
	 */
	public List<BodyPartItem> getBodyPartyItems() {
		return bodyPartyItems;
	}

	/**
	 * 设置 部位细节
	 * 
	 * @param bodyPartyItems
	 *            the bodyPartyItems to set
	 */
	public void setBodyPartyItems(List<BodyPartItem> bodyPartyItems) {
		this.bodyPartyItems = bodyPartyItems;
	}

	/**
	 * 返回 身体部位分类
	 * 
	 * @return the bodyCategory
	 */
	public BodyCategory getBodyCategory() {
		return bodyCategory;
	}

	/**
	 * 设置 身体部位分类
	 * 
	 * @param body
	 *            the body to set
	 */
	public void setBodyCategory(BodyCategory bodyCategory) {
		this.bodyCategory = bodyCategory;
	}

}