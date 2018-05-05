/**
 * 
 */
package com.weimhc.modules.base.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 身体部位分类 Entity
 * 
 * @author zsm
 * @version 2017-01-22
 */
public class BodyCategory extends BaseNameEntity<BodyCategory> {

	private static final long serialVersionUID = 1L;

	/**
	 * 身体部位
	 */
	private List<BodyPart> bodyPartys = Lists.newArrayList();

	public BodyCategory() {
		super();
	}

	public BodyCategory(String id) {
		super(id);
	}

	/**
	 * 返回 身体部位
	 * 
	 * @return the bodyPartys
	 */
	public List<BodyPart> getBodyPartys() {
		return bodyPartys;
	}

	/**
	 * 设置 身体部位
	 * 
	 * @param bodyPartys
	 *            the bodyPartys to set
	 */
	public void setBodyPartys(List<BodyPart> bodyPartys) {
		this.bodyPartys = bodyPartys;
	}

}