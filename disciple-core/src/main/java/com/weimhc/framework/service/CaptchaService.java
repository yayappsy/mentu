/*
 * 
 * 
 * 
 */
package com.weimhc.framework.service;

import com.weimhc.modules.base.utils.setting.CaptchaType;

/**
 * Service - 验证码
 * 
 * 验证码
 * 
 */
public interface CaptchaService {

	public static final String CAPTCHA_CACHE = "captchaCache";

	/***
	 * 验证验证码是否有效
	 * 
	 * @param captchaType
	 *            验证码类型
	 * @param captchaId
	 *            验证码编号
	 * @param captcha
	 *            验证码
	 * @return
	 */
	public boolean isValid(CaptchaType captchaType, String captchaId, String captcha);

	/**
	 * 通过captchaId生成验证码
	 * 
	 * @param captchaType
	 *            验证码类型
	 * 
	 * @param captchaId
	 * @return
	 */
	public String generateCaptcha(CaptchaType captchaType, String captchaId);

}