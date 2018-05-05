package com.weimhc.modules.track.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.modules.track.entity.VisitTrack;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 根据userAgent 获取浏览器和操作系统信息
 * 
 * 
 */
public abstract class VisitBrowserAndOsUtils {

	private static Logger logger = LoggerFactory
			.getLogger(VisitBrowserAndOsUtils.class);

	public static void dealWithBrowserAndOs(VisitTrack visitTrack,
			String userAgentStr) {
		logger.debug("浏览器代理对象:" + userAgentStr);
		UserAgent userAgent = UserAgentUtils.getUserAgent(userAgentStr);
		Browser browser = userAgent.getBrowser();
		visitTrack.setBrowserType(browser.getBrowserType().getName());
		visitTrack.setBrowserName(browser.getName());
		logger.debug("版本:" + userAgent.getBrowserVersion());
		if (userAgent.getBrowserVersion() != null) {
			visitTrack.setBrowserVersion(
					userAgent.getBrowserVersion().getVersion());
		}
		visitTrack.setDeviceType(
				userAgent.getOperatingSystem().getDeviceType().getName());
		visitTrack.setOs(userAgent.getOperatingSystem().getName());
	}

}