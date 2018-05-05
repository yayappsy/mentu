/**
 * 
 */
package com.weimhc.modules.suggestion.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 建议主题Entity
 * 
 * @author lc
 * @version 2016-12-01
 */
public class SuggestionSubject extends SortableEntity<SuggestionSubject> {

	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 * 
	 */
	private String subjectTitle;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 是否显示
	 * 
	 */
	private Boolean isShow;

	public SuggestionSubject() {
		super();
	}

	public SuggestionSubject(String id) {
		super(id);
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Length(min = 1, max = 255)
	public String getSubjectTitle() {
		return subjectTitle;
	}

	/**
	 * 设置标题
	 * 
	 * @param subjectTitle
	 *            标题
	 */
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取是否显示
	 * 
	 * @return 是否显示
	 */
	@Length(min = 1, max = 1)
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 * 
	 * @param isShow
	 *            是否显示
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}