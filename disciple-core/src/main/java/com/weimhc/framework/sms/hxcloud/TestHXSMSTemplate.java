/**
 */
package com.weimhc.framework.sms.hxcloud;

/**
 * 发送短信验证码
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public class TestHXSMSTemplate extends AbstractHXSMSTemplate {

	private static final long serialVersionUID = 5966120237917901891L;

	/**
	 * 验证码
	 */
	private String captcha;
	/**
	 * 验证码描述
	 */
	private String captchaRemarks;

	/**
	 *
	 * 返回 验证码
	 * 
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 设置 验证码
	 * 
	 * @param captcha
	 *            the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 *
	 * 返回 验证码描述
	 * 
	 * @return the captchaRemarks
	 */
	public String getCaptchaRemarks() {
		return captchaRemarks;
	}

	/**
	 * 设置 验证码描述
	 * 
	 * @param captchaRemarks
	 *            the captchaRemarks to set
	 */
	public void setCaptchaRemarks(String captchaRemarks) {
		this.captchaRemarks = captchaRemarks;
	}

	@Override
	public String getProductId() {
		return null;
	}

	@Override
	public String getSigningId() {
		return null;
	}

	@Override
	public String getTemplateId() {
		return null;
	}

}
