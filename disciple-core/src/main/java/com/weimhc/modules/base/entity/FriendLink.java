/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 友情链接Entity
 * 
 * @author lc
 * @version 2016-08-18
 */
public class FriendLink extends SortableEntity<FriendLink> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 链接
	 * 
	 */
	private String link;

	public FriendLink() {
		super();
	}

	public FriendLink(String id) {
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
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
	 * 获取链接
	 * 
	 * @return 链接
	 */
	@Length(min = 1, max = 255)
	public String getUrl() {
		return link;
	}

	/**
	 * 设置链接
	 * 
	 * @param link
	 *            链接
	 */
	public void setUrl(String link) {
		this.link = link;
	}

}