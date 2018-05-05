/**
 * 
 */
package com.weimhc.modules.user.entity;

/**
 * 登录类型
 * 
 * @author laozh
 *
 */
public enum IdentityType {
	/**
	 * 使用用户名登录
	 */
	username(false),
	/**
	 * 使手机号登录
	 */
	mobile(false),
	/**
	 * 使用邮箱登录
	 */
	email(false),
	/**
	 * 使用weixin登录
	 */
	wechat(true),
	/**
	 * 使用qq登录
	 */
	qq(false);

	private boolean ifThirdPart;

	private IdentityType(boolean ifThirdPart) {
		this.ifThirdPart = ifThirdPart;
	}

	/**
	 * 是否第三方登录
	 * 
	 * @return
	 */
	public boolean getIfThirdPart() {
		return ifThirdPart;
	}

}
