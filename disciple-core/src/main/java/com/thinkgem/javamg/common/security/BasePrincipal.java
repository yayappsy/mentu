package com.thinkgem.javamg.common.security;

import java.io.Serializable;

/***
 * 登录凭证
 * 
 * @author laozh
 *
 */
public abstract class BasePrincipal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276581269662961438L;

	private String id; // 编号
	private String username; // 登录名
	private String name; // 姓名
	private boolean mobileLogin; // 是否手机登录
	/**
	 * 是否app登录
	 */
	private boolean appLogin;
	/**
	 * token前缀
	 */
	private String tokenPrefix;

	public BasePrincipal() {

	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobileLogin(boolean mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	/**
	 * @return the appLogin
	 */
	public boolean isAppLogin() {
		return appLogin;
	}

	/**
	 * @param appLogin
	 *            the appLogin to set
	 */
	public void setAppLogin(boolean appLogin) {
		this.appLogin = appLogin;
	}

	@Override
	public String toString() {
		return id;
	}

	/**
	 * 获取 token前缀
	 * 
	 * @return the tokenPrefix
	 */
	public String getTokenPrefix() {
		return tokenPrefix;
	}

	/**
	 * 设置 token前缀
	 * 
	 * @param tokenPrefix
	 *            the tokenPrefix to set
	 */
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

}