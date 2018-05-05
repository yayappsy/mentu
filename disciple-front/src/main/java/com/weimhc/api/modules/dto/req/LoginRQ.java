package com.weimhc.api.modules.dto.req;

import com.weimhc.modules.base.utils.setting.CaptchaType;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录使用的请求
 * 
 * @author szuo
 *
 */
public class LoginRQ extends BaseLoginRQ {

	private static final long serialVersionUID = -2659037797172700696L;

	/**
	 * 加密之后的密码
	 * 
	 */
	private String enPassword;

	/**
	 * 验证码类型
	 * 
	 */
	private CaptchaType captchaType;

	/**
	 * 验证码
	 * 
	 */
	private String imageCaptcha;

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
	 * 获取 验证码放类型
	 * 
	 * @return the captchaType
	 */
	@ApiModelProperty(notes = "验证码类型", example = "memberRegister", allowableValues = "memberLogin,memberRegister,findPassword,resetPassword", required = true)
	public CaptchaType getCaptchaType() {
		return captchaType;
	}

	/**
	 * 设定 验证码放类型
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setCaptchaType(CaptchaType captchaType) {
		this.captchaType = captchaType;
	}

	/**
	 * 获取图片验证码
	 * 
	 * @return the imageCaptcha
	 */
	@ApiModelProperty(value = "图片验证码")
	public String getImageCaptcha() {
		return imageCaptcha;
	}

	/**
	 * 设置图片验证码
	 * 
	 * @param imageCaptcha
	 *            the imageCaptcha to set
	 */
	public void setImageCaptcha(String imageCaptcha) {
		this.imageCaptcha = imageCaptcha;
	}
}
