/*
 * 
 * 
 * 
 */
package com.weimhc.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.base.utils.setting.SiteSettingUtils;

/**
 * Utils - Cookie
 * 
 * 
 * 
 */
public final class CookieUtils {

	private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	/**
	 * 不可实例化
	 */
	private CookieUtils() {
	}

	/**
	 * 设置 Cookie（生成时间为1天）
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, 60 * 60 * 24);
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			Integer maxAge) {
		addCookie(request, response, name, value, maxAge,
				SiteSettingUtils.getCookiePath(),
				SiteSettingUtils.getCookieDomain(), null);
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, null,
				SiteSettingUtils.getCookiePath(),
				SiteSettingUtils.getCookieDomain(), null);
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 * @param secure
	 *            是否启用加密
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			// 如果没有设置路径，则使用"/"
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			} else {
				cookie.setPath("/");
			}
			// 如果没有设置域名，则使用host
			if (StringUtils.isEmpty(domain)) {
				domain = request.getHeader("Host");
			}
			cookie.setDomain(domain);
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie名称
	 * @return 若不存在则返回null
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
				;
			}
		}
		return null;
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String path,
			String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			// 如果没有设置路径，则使用"/"
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			} else {
				cookie.setPath("/");
			}
			// 如果没有设置域名，则使用host
			if (StringUtils.isEmpty(domain)) {
				domain = request.getHeader("Host");
			}
			cookie.setDomain(domain);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		removeCookie(request, response, name, SiteSettingUtils.getCookiePath(),
				SiteSettingUtils.getCookieDomain());
	}

}