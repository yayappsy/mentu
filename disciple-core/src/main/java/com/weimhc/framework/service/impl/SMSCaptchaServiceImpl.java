/*
 * 
 * 
 * 
 */
package com.weimhc.framework.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.weimhc.framework.service.AbstractSMSCaptchaService;
import com.weimhc.modules.base.utils.setting.CaptchaType;

/**
 * Service - 验证码
 * 
 * 短信验证码
 * 
 */
@Service("sMSCaptchaService")
public class SMSCaptchaServiceImpl extends AbstractSMSCaptchaService {

	@Override
	public boolean isValid(CaptchaType captchaType, String captchaId, String captcha) {
		if (StringUtils.isBlank(captchaId) || StringUtils.isBlank(captcha)) {
			return false;
		}
		String storeKey = captchaType + captchaId;
		String storedCaptcha = (String) CacheUtils.get(CAPTCHA_CACHE, storeKey);
		if (StringUtils.isBlank(storedCaptcha) || !StringUtils.equals(storedCaptcha, captcha)) {
			return false;
		}
		CacheUtils.remove(CAPTCHA_CACHE, storeKey);

		return true;

	}

	/**
	 * 通过captchaId生成验证码
	 * 
	 * @param captchaId
	 * @return
	 */
	@Override
	public String generateCaptcha(CaptchaType captchaType, String captchaId) {
		String captcha = super.generateCaptcha(captchaType, captchaId);
		CacheUtils.put(CAPTCHA_CACHE, captchaType + captchaId, captcha);
		return captcha;
	}

}