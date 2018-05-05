/**
 * 
 */
package com.weimhc.front.core.security;

import com.weimhc.modules.user.entity.IdentityType;

/**
 * 用户和密码（包含验证码）令牌类
 * 
 * @version 2016-02-25
 */
public class UsernamePasswordToken
		extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captchaId;
	private String captcha;
	private boolean mobileLogin;
	private boolean isNeedCaptcha;

	/**
	 * 登录类型
	 */
	private IdentityType identityType;

	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha,
			boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.mobileLogin = mobileLogin;
		this.identityType = IdentityType.username;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	public void setMobileLogin(boolean mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	/**
	 * @return the captchaId
	 */
	public String getCaptchaId() {
		return captchaId;
	}

	/**
	 * @param captchaId
	 *            the captchaId to set
	 */
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	/**
	 * 获取 是否需要校验验证码
	 * 
	 * @return the isNeedCaptcha
	 */
	public boolean getIsNeedCaptcha() {
		return isNeedCaptcha;
	}

	/**
	 * 设置 是否需要校验验证码
	 * 
	 * @param isNeedCaptcha
	 *            the isNeedCaptcha to set
	 */
	public void setIsNeedCaptcha(boolean isNeedCaptcha) {
		this.isNeedCaptcha = isNeedCaptcha;
	}

	/**
	 * 返回 登录类型
	 * 
	 * @return the identityType
	 */
	public IdentityType getIdentityType() {
		return identityType;
	}

	/**
	 * 设置 登录类型
	 * 
	 * @param identityType
	 *            the identityType to set
	 */
	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

}