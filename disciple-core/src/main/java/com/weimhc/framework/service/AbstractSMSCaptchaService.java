/*
 * 
 * 
 * 
 */
package com.weimhc.framework.service;

import org.apache.commons.lang3.RandomStringUtils;

import com.weimhc.modules.base.utils.setting.CaptchaType;

/**
 * Service - 验证码
 * 
 * 短信验证码
 * 
 */
public abstract class AbstractSMSCaptchaService implements CaptchaService {

	/**
	 * 通过captchaId生成验证码
	 * 
	 * @param captchaId
	 * @return
	 */
	@Override
	public String generateCaptcha(CaptchaType captchaType, String captchaId) {
		String captcha = RandomStringUtils.randomNumeric(6);
		return captcha;
	}

}