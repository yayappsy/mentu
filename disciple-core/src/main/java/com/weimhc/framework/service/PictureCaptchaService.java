/*
 * 
 * 
 * 
 */
package com.weimhc.framework.service;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.base.utils.setting.CaptchaType;
import com.weimhc.modules.base.utils.setting.SettingUtils;

/**
 * Service - 验证码
 * 
 * 图片验证码
 * 
 */
@Service
public class PictureCaptchaService extends AbstractSMSCaptchaService {

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;

	public BufferedImage buildImage(String captchaId) {
		return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
	}

	@Override
	public boolean isValid(CaptchaType captchaType, String captchaId, String captcha) {

		if (captchaType == null
				|| ArrayUtils.contains(SettingUtils.getCaptchaTypes(), captchaType)) {
			if (StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha)) {
				try {
					return imageCaptchaService.validateResponseForID(captchaId,
							captcha.toUpperCase());
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}