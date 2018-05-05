package com.weimhc.framework.im.easemob.dto.resp;

import com.weimhc.framework.im.dto.IMUser;

/**
 * 环信用户返回信息
 * 
 * @author laozh
 *
 */
public class IMUserDto extends IMUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809986592563409371L;
	/**
	 * 环信自有id
	 */
	private String uuid;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 是否可用
	 */
	private Boolean activated;
	/**
	 * 创建时间
	 */
	private long created;
	/**
	 * 更新时间
	 */
	private long modified;

	public IMUserDto() {

	}

	public IMUserDto(String username) {
		this.username = username;
	}

	public IMUserDto(String username, String password, String nickname) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	/**
	 * 返回 用户名
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 密码
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回 密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 用户名
	 * 
	 * @param username
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回 昵称
	 * 
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置 昵称
	 * 
	 * @param username
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取 环信自有id
	 * 
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置 环信自有id
	 * 
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 获取 类型
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 类型
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取 是否可用
	 * 
	 * @return the activated
	 */
	public Boolean getActivated() {
		return activated;
	}

	/**
	 * 设置 是否可用
	 * 
	 * @param activated
	 *            the activated to set
	 */
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return the created
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param created
	 *            the created to set
	 */
	public void setCreated(long created) {
		this.created = created;
	}

	/**
	 * 获取 更新时间
	 * 
	 * @return the modified
	 */
	public long getModified() {
		return modified;
	}

	/**
	 * 设置 更新时间
	 * 
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(long modified) {
		this.modified = modified;
	}

}
