/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.cache.config.JedisCacheConfiguration;
import com.weimhc.framework.cache.config.JedisConfiguration;

/**
 * Cache工具类
 * 
 * @version 2013-5-29
 */
public class CacheUtils {

	private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

	/**
	 * 是否使用redis缓存
	 */
	private static final boolean REDIS_IS_ENABLED = StringUtils.equals(Global.TRUE,
			Global.getConfig("redis.is.enabled"));

	/**
	 * 分隔符
	 */
	public static final String SEPARATOR = "_";

	/**
	 * redis 默认缓存时间
	 */
	private static final String SYS_CACHE = "sysCache";

	/**
	 * 是否启用缓存
	 * 
	 * @return
	 */
	public static boolean getRedisIsEnable() {
		return REDIS_IS_ENABLED;
	}

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
	 * 获取缓存，并使用配置好的缓存存储时间设置
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		int timeToIdleSeconds = 0;
		if (getRedisIsEnable()) {
			timeToIdleSeconds = getTimeToIdleSeconds(cacheName);
		}
		return get(cacheName, key, timeToIdleSeconds);
	}

	/**
	 * 获取缓存
	 * 
	 * @param cacheName
	 * @param key
	 * @param timeToIdleSeconds
	 *            缓存存活时间（最后一次访问或更新到现在的时间）
	 * @return
	 */
	public static Object get(String cacheName, String key, int timeToIdleSeconds) {
		logger.debug("在{}中获取缓存{}，并刷新过期{}", cacheName, key, timeToIdleSeconds);
		if (getRedisIsEnable()) {
			return JedisUtils.getObject(getJedisCacheKey(cacheName, key), timeToIdleSeconds);
		} else {
			return EhCacheUtils.get(cacheName, key);
		}
	}

	/**
	 * 获取jedis的实际key
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	private static String getJedisCacheKey(String cacheName, String key) {
		JedisConfiguration cacheConfiguration = JedisConfiguration.getInstance();
		JedisCacheConfiguration jedisCache = JedisConfiguration.getInstance()
				.getCacheConfiguration(cacheName);
		StringBuilder cacheKey = new StringBuilder(cacheConfiguration.getPublicKeyPrefix());
		if (!jedisCache.isSingleton()) {
			cacheKey.append(SEPARATOR);
			cacheKey.append(cacheConfiguration.getPrivateKeyPrefix());
		}
		cacheKey.append(SEPARATOR);
		cacheKey.append(cacheName);
		cacheKey.append(SEPARATOR);
		cacheKey.append(key);
		return cacheKey.toString();
	}

	/**
	 * 获取jedis-cache配置好的过期时间
	 * 
	 * @param cacheName
	 * @return
	 */
	private static int getTimeToIdleSeconds(String cacheName) {
		JedisCacheConfiguration jedisCache = JedisConfiguration.getInstance()
				.getCacheConfiguration(cacheName);
		int timeToIdleSeconds = 0;
		if (!jedisCache.isEternal()) {
			timeToIdleSeconds = (int) jedisCache.getTimeToLiveSeconds();
		}
		return timeToIdleSeconds;
	}

	/**
	 * 写入缓存,使用配置的缓存时间设置
	 * 
	 * <p>
	 * 如果当前使用的是redis缓存，缓存时间为jedis config中配置的时间
	 * </p>
	 * <p>
	 * 如果当前使用的是ehcache缓存，缓存时间为cache config中配置好的时间
	 * </p>
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		int timeToIdleSeconds = 0;
		if (getRedisIsEnable()) {
			timeToIdleSeconds = getTimeToIdleSeconds(cacheName);
		}
		put(cacheName, key, value, timeToIdleSeconds);
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
	 *            缓存存活时间（最后一次访问或更新到现在的时间） 如果设定值小于等于0，则不会生效
	 */
	public static void put(String cacheName, String key, Object value, int timeToIdleSeconds) {
		logger.debug("在{}中存入缓存{},存活时间{}", cacheName, key, timeToIdleSeconds);
		if (getRedisIsEnable()) {
			JedisUtils.setObject(getJedisCacheKey(cacheName, key), value, timeToIdleSeconds);
		} else {
			EhCacheUtils.put(cacheName, key, value, timeToIdleSeconds);
		}
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		logger.debug("从{}中移除缓存{}", cacheName, key);
		if (getRedisIsEnable()) {
			JedisUtils.del(getJedisCacheKey(cacheName, key));
		} else {
			EhCacheUtils.remove(cacheName, key);
		}
	}

}
