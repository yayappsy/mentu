package com.weimhc.framework.im.easemob.dto.req;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import io.swagger.client.model.User;

/**
 * 环信用户注册，增加昵称
 * 
 * @author laozh
 *
 */
public class IMUserRQ extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809986592563409371L;
	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 新密码
	 */
	private String newPassword;

	public IMUserRQ() {

	}

	public IMUserRQ(String username) {
		this(username, null);
	}

	public IMUserRQ(String username, String password) {
		this(username, password, null);
	}

	public IMUserRQ(String username, String password, String nickname) {
		username(username);
		password(password);
		this.nickname = nickname;
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
	 * 返回 新密码
	 * 
	 * @return the newPassword
	 */
	@SerializedName("newpassword")
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 设置 新密码
	 * 
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
