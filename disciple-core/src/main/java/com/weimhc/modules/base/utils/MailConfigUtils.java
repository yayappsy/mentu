/*
 * 
 * 
 * 
 */
package com.weimhc.modules.base.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.base.dao.MailConfigDao;
import com.weimhc.modules.base.entity.MailConfig;

/**
 * Utils - 邮箱信息
 * 
 * 
 */
public abstract class MailConfigUtils {

	private static MailConfigDao mailConfigDao = SpringContextHolder
			.getBean(MailConfigDao.class);

	/**
	 * 邮箱信息相关缓存 暂时未使用
	 */
	public final static String MAIL_CONFIG_CACHE = "mailConfigCache";
	/** 邮箱信息相关缓存列表 */
	public final static String CACHE_MAIL_CONFIG_LIST = "mailConfig_list";
	/** 邮箱信息相关缓存列表 */
	public final static String CACHE_MAIL_CONFIG_BY_ID = "mailConfig_id";

	/**
	 * 获取邮箱信息相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置邮箱信息相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 清除所有邮箱相关缓存
	 */
	public static void clearCache() {
		CacheUtils.remove(CACHE_MAIL_CONFIG_LIST);
	}

	/**
	 * 获取所有的邮箱信息
	 * 
	 * @return
	 */
	public static List<MailConfig> findAll() {
		@SuppressWarnings("unchecked")
		List<MailConfig> list = (List<MailConfig>) getCache(
				CACHE_MAIL_CONFIG_LIST);
		if (list == null || list.size() == 0) {
			list = mailConfigDao.findAllList(new MailConfig());
			putCache(CACHE_MAIL_CONFIG_LIST, list);
		}
		return list;

	}

	/**
	 * id查询邮箱信息
	 * 
	 * @param id
	 * @return
	 */
	public static MailConfig findById(String id) {
		List<MailConfig> mailConfigList = findAll();
		for (MailConfig mailConfig : mailConfigList) {
			if (StringUtils.equals(id, mailConfig.getId())) {
				return mailConfig;
			}
		}
		return new MailConfig();
	}

	/**
	 * 获取默认企业邮箱
	 * 
	 * @return
	 */
	public static MailConfig findDefault() {
		List<MailConfig> mailConfigList = findAll();
		if (!mailConfigList.isEmpty()) {
			return mailConfigList.get(0);
		}
		return new MailConfig();
	}

}