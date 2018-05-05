package com.weimhc.modules.base.utils.setting;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SiteSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(SiteSettingUtils.class);

	/** 网站设置 */
	public static final String SITE_SETTING = "site";

	/** 网站设置 */
	public enum SiteSetting {

		/** 网站名称 */
		siteName,

		/** 网站网址 */
		siteUrl,
		/** app网址 */
		appUrl,

		/** logo */
		logo,

		/** 关键字 */
		keywords,

		/** 网站联系地址 */
		address,

		/** 联系电话 */
		phone,

		/** 邮政编码 */
		zipCode,

		/** E-mail */
		email,

		/** 备案编号 */
		certtext,

		/** 是否网站开启 */
		isSiteEnabled,

		/** 网站关闭消息 */
		siteCloseMessage,

		/** Cookie路径 */
		cookiePath,

		/** Cookie作用域 */
		cookieDomain,
	}

	// ============== 网站相关设置 ==============
	/**
	 * 获取网站名称
	 * 
	 * @return 网站名称
	 */
	public static String getSiteName() {
		String siteName = SettingUtils
				.getSettingValue(SiteSetting.siteName.name(), SITE_SETTING);
		return siteName;
	}

	/**
	 * 获取Cookie作用域
	 * 
	 * @return Cookie作用域
	 */
	public static String getCookieDomain() {
		String cookieDomain = SettingUtils
				.getSettingValue(SiteSetting.cookieDomain.name(), SITE_SETTING);
		return cookieDomain;
	}

	/**
	 * 获取Cookie路径
	 * 
	 * @return Cookie路径
	 */

	public static String getCookiePath() {
		String cookiePath = SettingUtils
				.getSettingValue(SiteSetting.cookiePath.name(), SITE_SETTING);
		return cookiePath;
	}

	/**
	 * 获取网站关闭消息
	 * 
	 * @return 网站关闭消息
	 */

	public static String getSiteCloseMessage() {
		String siteCloseMessage = SettingUtils.getSettingValue(
				SiteSetting.siteCloseMessage.name(), SITE_SETTING);
		return siteCloseMessage;
	}

	/**
	 * 获取是否网站开启
	 * 
	 * @return 是否网站开启
	 */
	public static Boolean getIsSiteEnabled() {
		String isSiteEnabled = SettingUtils.getSettingValue(
				SiteSetting.isSiteEnabled.name(), SITE_SETTING);
		return BooleanUtils.toBooleanObject(isSiteEnabled);
	}

	/**
	 * 获取备案编号
	 * 
	 * @return 备案编号
	 */

	public static String getCerttext() {
		String certtext = SettingUtils
				.getSettingValue(SiteSetting.certtext.name(), SITE_SETTING);
		return certtext;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */

	public static String getEmail() {
		String email = SettingUtils.getSettingValue(SiteSetting.email.name(),
				SITE_SETTING);
		return email;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */

	public static String getZipCode() {
		String zipCode = SettingUtils
				.getSettingValue(SiteSetting.zipCode.name(), SITE_SETTING);
		return zipCode;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */

	public static String getPhone() {
		String phone = SettingUtils.getSettingValue(SiteSetting.phone.name(),
				SITE_SETTING);
		return phone;
	}

	/**
	 * 获取网站联系地址
	 * 
	 * @return 网站联系地址
	 */

	public static String getAddress() {
		String address = SettingUtils
				.getSettingValue(SiteSetting.address.name(), SITE_SETTING);
		return address;
	}

	/**
	 * 获取网站关键字
	 * 
	 * @return 网站关键字
	 */
	public static String[] getHotSearches() {
		return StringUtils.split(SiteSettingUtils.getKeywords(),
				SettingUtils.SEPARATOR);
	}

	/**
	 * 获取网站关键字
	 * 
	 * @return 热门搜索
	 */

	public static String getKeywords() {
		String keywords = SettingUtils
				.getSettingValue(SiteSetting.keywords.name(), SITE_SETTING);
		return keywords;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public static String getLogo() {
		String siteName = SettingUtils
				.getSettingValue(SiteSetting.siteName.name(), SITE_SETTING);
		return siteName;
	}

	/**
	 * 获取网站网址
	 * 
	 * @return 网站网址
	 */
	public static String getSiteUrl() {
		String siteUrl = SettingUtils
				.getSettingValue(SiteSetting.siteUrl.name(), SITE_SETTING);
		return siteUrl;
	}

	/**
	 * 获取app网址
	 * 
	 * @return app网址
	 */
	public static String getAppUrl() {
		String appUrl = SettingUtils.getSettingValue(SiteSetting.appUrl.name(),
				SITE_SETTING);
		return appUrl;
	}
}
