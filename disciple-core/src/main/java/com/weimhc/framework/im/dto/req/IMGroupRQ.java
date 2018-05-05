/**
 * 
 */
package com.weimhc.framework.im.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;

/**
 * @author shaozuo
 *
 */
public abstract class IMGroupRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64227702939724533L;
	/**
	 * IM群id
	 */
	private String id;
	/**
	 * 群名称
	 */
	private String name;
	/**
	 * 群描述
	 */
	private String description;
	/**
	 * 群成员上线
	 */
	private int maxUsers;
	/**
	 * 群主id
	 */
	private String managerId;

	/**
	 * 返回 IM群id
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 IM群id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 群描述
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 群描述
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回 群成员上线
	 * 
	 * @return the maxUsers
	 */
	public int getMaxUsers() {
		return maxUsers;
	}

	/**
	 * 设置 群成员上线
	 * 
	 * @param maxUsers
	 *            the maxUsers to set
	 */
	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}

	/**
	 * 返回 群主id
	 * 
	 * @return the managerId
	 */
	public String getManagerId() {
		return managerId;
	}

	/**
	 * 设置 群主id
	 * 
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
