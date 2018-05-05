/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.weimhc.modules.user.entity.IdentityType;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * App菜单 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class RegisterRQ extends BaseRegisterRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 加密之后的密码
	 * 
	 */
	private String enPassword;
	/**
	 * 验证码
	 * 
	 */
	private String captcha;
	/**
	 * 验证码类型
	 * 
	 */
	private String captchaType;

	/**
	 * 登录标识
	 * 
	 */
	private String identifier;

	/**
	 * 登录类型
	 */
	private IdentityType identityType;

	/**
	 * 获取 验证码
	 * 
	 * @return the captcha
	 */
	@Override
	@ApiModelProperty(value = "验证码", example = "123456")
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 设定 验证码
	 * 
	 * @param captcha
	 *            the captcha to set
	 */
	@Override
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * 返回 加密之后的密码
	 * 
	 * @return the enPassword
	 */
	@ApiModelProperty(value = "加密之后的密码", example = "")
	public String getEnPassword() {
		return enPassword;
	}

	/**
	 * 设置 加密之后的密码
	 * 
	 * @param enPassword
	 *            the enPassword to set
	 */
	public void setEnPassword(String enPassword) {
		this.enPassword = enPassword;
	}

	/**
	 * 获取 验证码类型
	 * 
	 * @return the captchaType
	 */
	@ApiModelProperty(notes = "验证码类型", example = "memberRegister", allowableValues = "memberRegister,findPassword,resetPassword,bindingMobile", required = true)
	public String getCaptchaType() {
		return captchaType;
	}

	/**
	 * 设定 验证码类型
	 * 
	 * @param captchaType
	 *            the captchaType to set
	 */
	public void setCaptchaType(String captchaType) {
		this.captchaType = captchaType;
	}

	/**
	 * 获取登录类型
	 * 
	 * @return 登录类型
	 */
	@ApiModelProperty(value = "登录类型,与字段对应,如果登录类型为mobile,则手机号必填", example = "mobile", required = true)
	public IdentityType getIdentityType() {
		return identityType;
	}

	/**
	 * 设置登录类型
	 * 
	 * @param identityType
	 *            登录类型
	 */
	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	/**
	 * 获取 标识
	 * 
	 * @return 标识
	 * 
	 */
	@ApiIgnore
	public String getIdentifier() {
		switch (identityType) {
		case mobile:
			identifier = getMobile();
			break;
		default:
			break;
		}
		return identifier;
	}

	/**
	 * 设置 标识
	 * 
	 * @param identifier
	 *            标识
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the nickname
	 */
	@ApiModelProperty(value = "昵称")
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}