/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 * 
 * @version 2013-5-29
 */
public class EhCacheUtils {

	private static Logger logger = LoggerFactory.getLogger(EhCacheUtils.class);

	private static CacheManager cacheManager = ((CacheManager) SpringContextHolder
			.getBean("cacheManager"));

	private static final String SYS_CACHE = "sysCache";

	/**
	 * 获取SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}

	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}

	/**
	 * 从SYS_CACHE缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}

	/**
	 * 获取缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		if (element == null) {
			return null;
		}
		logger.debug("{} 中 {} 的过期时间是：{}", cacheName, key, DateUtils
				.formatDateTime(new Date(element.getExpirationTime())));
		return element.getObjectValue();
	}

	/**
	 * 写入缓存,使用cache config中配置好的时间
	 * 
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            缓存关键字
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		put(cacheName, key, value, -1);
	}

	/**
	 * 写入缓存
	 * 
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            缓存关键字
	 * @param value
	 *            缓存值
	 * @param timeToIdleSeconds
	 *            缓存存活时间（最后一次访问或更新到现在的时间） ,必须大于0
	 */
	public static void put(String cacheName, String key, Object value,
			int timeToIdleSeconds) {
		Element element = new Element(key, value);
		if (timeToIdleSeconds > 0) {
			element.setTimeToIdle(timeToIdleSeconds);
		}
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * 获得一个Cache，没有则创建一个。
	 * 
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

}
