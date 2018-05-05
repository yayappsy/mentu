/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 用户代理字符串识别工具
 * 
 * @version 2014-6-13
 */
public class UserAgentUtils {

	private static final String WEIXIN_BROWSER_NAME = "MicroMessenger";

	/***
	 * 是否微信浏览器
	 * 
	 * @author szuo
	 * @param request
	 * @return
	 */
	public static boolean isWeixinBrowser(HttpServletRequest request) {
		return isSpecifyBrowser(request, WEIXIN_BROWSER_NAME);
	}

	/***
	 * 是否指定浏览器
	 * 
	 * @author szuo
	 * @param request
	 * @return
	 */
	public static boolean isSpecifyBrowser(HttpServletRequest request,
			String browserName) {
		String userAgentString = UserAgentUtils.getUserAgentString(request);
		if (StringUtils.contains(userAgentString, browserName)) {
			return true;
		}
		return false;
	}

	/***
	 * 获取用户代理对象字符串
	 * 
	 * @author szuo
	 * @param request
	 * @return
	 */
	public static String getUserAgentString(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * 获取用户代理对象
	 * 
	 * @param request
	 * @return
	 */
	public static UserAgent getUserAgent(HttpServletRequest request) {
		return UserAgent.parseUserAgentString(getUserAgentString(request));
	}

	/**
	 * 获取用户代理对象
	 * 
	 * @param request
	 * @return
	 */
	public static UserAgent getUserAgent(String userAgentStr) {
		return UserAgent.parseUserAgentString(userAgentStr);
	}

	/**
	 * 获取设备类型
	 * 
	 * @param request
	 * @return
	 */
	public static DeviceType getDeviceType(HttpServletRequest request) {
		return getUserAgent(request).getOperatingSystem().getDeviceType();
	}

	/**
	 * 获取操作系统
	 * 
	 * @param request
	 * @return
	 */
	public static OperatingSystem getOperatingSystem(
			HttpServletRequest request) {
		return getUserAgent(request).getOperatingSystem();
	}

	/**
	 * 是否是PC
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isComputer(HttpServletRequest request) {
		return DeviceType.COMPUTER.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobile(HttpServletRequest request) {
		return DeviceType.MOBILE.equals(getDeviceType(request));
	}

	/**
	 * 是否是平板
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isTablet(HttpServletRequest request) {
		return DeviceType.TABLET.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机和平板
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobileOrTablet(HttpServletRequest request) {
		DeviceType deviceType = getDeviceType(request);
		return DeviceType.MOBILE.equals(deviceType)
				|| DeviceType.TABLET.equals(deviceType);
	}

	/**
	 * 获取浏览类型
	 * 
	 * @param request
	 * @return
	 */
	public static Browser getBrowser(HttpServletRequest request) {
		return getUserAgent(request).getBrowser();
	}

	/**
	 * 通过 userAgent 字符串获取浏览器属性
	 * 
	 * @param userAgentStr
	 * @return
	 */
	public static Browser getBrowser(String userAgentStr) {
		return getUserAgent(userAgentStr).getBrowser();
	}

	/**
	 * 是否IE版本是否小于等于IE8
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLteIE8(HttpServletRequest request) {
		Browser browser = getBrowser(request);
		return Browser.IE5.equals(browser) || Browser.IE6.equals(browser)
				|| Browser.IE7.equals(browser) || Browser.IE8.equals(browser);
	}

}
