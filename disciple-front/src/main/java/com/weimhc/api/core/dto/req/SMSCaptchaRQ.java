/**
 * 
 */
package com.weimhc.api.core.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.modules.base.utils.setting.CaptchaType;

import io.swagger.annotations.ApiModelProperty;

/**
 * 验证码 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class SMSCaptchaRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 手机号
	 * 
	 */
	private String mobile;

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
	 * 获取 手机号
	 * 
	 * @return the mobile
	 */
	@ApiModelProperty(value = "手机号", example = "18800000000")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设定 手机号
	 * 
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	@ApiModelProperty(value = "图片验证码", required = false, example = "")
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