package com.weimhc.framework.web.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.SpringContextHolder;

public abstract class MessageSourceUtils {

	private static ApplicationContext applicationContext = SpringContextHolder
			.getApplicationContext();

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(MessageSourceUtils.class);

	/**
	 * 获取国际化消息
	 * 
	 * @param request
	 *            请求，使用cookie等方式必须
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getMessage(HttpServletRequest request, String code,
			Object... args) {
		LocaleResolver localeResolver = SpringContextHolder
				.getBean(LocaleResolver.class);
		Locale locale = localeResolver.resolveLocale(request);
		return getMessage(locale, code, args);
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param local
	 *            本地化
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getMessage(Locale locale, String code,
			Object... args) {
		logger.debug("locale:" + locale + "  code:" + code);
		logger.debug(
				"content:" + applicationContext.getMessage(code, args, locale));
		return applicationContext.getMessage(code, args, locale);
	}

	/**
	 * 使用默认语言获取国际化消息
	 * 
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getMessageByLocaleStr(String localeStr, String code,
			Object... args) {
		Locale locale = StringUtils.parseLocaleString(localeStr);
		return getMessage(locale, code, args);
	}

	/**
	 * 使用默认语言获取国际化消息
	 * 
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getMessage(String code, Object... args) {
		Locale locale = StringUtils
				.parseLocaleString(Global.getConfig("message.locale.default"));
		return getMessage(locale, code, args);
	}
}
