/**
 * 
 */
package com.weimhc.framework.cache.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.SystemPath;
import com.weimhc.framework.utils.XMLUtil;

/**
 * 自定义缓存配置
 * 
 * @author shaozuo
 *
 */
@XmlRootElement(name = "jedisCache")
public final class JedisConfiguration {

	public static final String DEAULT_CACHE_NAME = "defaultCache";

	private static JedisConfiguration instance;

	/**
	 * 是否使用redis缓存
	 */
	private static final String REDIS_CONFIG_FILE = Global.getConfig("redis.configFile");

	/**
	 * 公共key前缀(共享时使用)
	 */
	private String publicKeyPrefix;

	/**
	 * 私有key前缀(每个项目单独使用)
	 */
	private String privateKeyPrefix;

	/**
	 * 默认缓存，如果没有配置缓存，则使用默认缓存的配置
	 */
	private JedisCacheConfiguration defaultCache;
	/**
	 * 缓存配置列表
	 */
	private List<JedisCacheConfiguration> caches;

	private final Map<String, JedisCacheConfiguration> jedisCacheConfigurations = new ConcurrentHashMap<String, JedisCacheConfiguration>();

	private void add(String cacheName, JedisCacheConfiguration cacheConfiguration) {
		jedisCacheConfigurations.put(cacheName, cacheConfiguration);
	}

	private void addAll(Collection<JedisCacheConfiguration> cacheConfigurations) {
		for (JedisCacheConfiguration cacheConfiguration : cacheConfigurations) {
			jedisCacheConfigurations.put(cacheConfiguration.getName(), cacheConfiguration);
		}
	}

	/**
	 * 返回 公共key前缀
	 * 
	 * @return the publicKeyPrefix
	 */
	@XmlElement
	public String getPublicKeyPrefix() {
		return publicKeyPrefix;
	}

	/**
	 * 设置 公共key前缀
	 * 
	 * @param publicKeyPrefix
	 *            the publicKeyPrefix to set
	 */
	public void setPublicKeyPrefix(String publicKeyPrefix) {
		this.publicKeyPrefix = publicKeyPrefix;
	}

	/**
	 * 返回 私有key前缀
	 * 
	 * @return the privateKeyPrefix
	 */
	@XmlElement
	public String getPrivateKeyPrefix() {
		return privateKeyPrefix;
	}

	/**
	 * 设置 私有key前缀
	 * 
	 * @param privateKeyPrefix
	 *            the privateKeyPrefix to set
	 */
	public void setPrivateKeyPrefix(String privateKeyPrefix) {
		this.privateKeyPrefix = privateKeyPrefix;
	}

	/**
	 * 返回 默认缓存
	 * 
	 * @return the defaultCache
	 */
	@XmlElement(name = "defaultCache")
	public JedisCacheConfiguration getDefaultCache() {
		return defaultCache;
	}

	/**
	 * 设置 默认缓存
	 * 
	 * @param defaultCache
	 *            the defaultCache to set
	 */
	public void setDefaultCache(JedisCacheConfiguration defaultCache) {
		this.defaultCache = defaultCache;
		this.defaultCache.setName(DEAULT_CACHE_NAME);
		add(DEAULT_CACHE_NAME, defaultCache);
	}

	/**
	 * 返回 缓存配置列表
	 * 
	 * @return the caches
	 */
	@XmlAnyElement(lax = true)
	public List<JedisCacheConfiguration> getCaches() {
		return caches;
	}

	/**
	 * 设置 缓存配置列表
	 * 
	 * @param caches
	 *            the caches to set
	 */
	public void setCaches(List<JedisCacheConfiguration> caches) {
		this.caches = caches;
	}

	/**
	 * 通过缓存名称 获取缓存配置,如果没有找到，使用默认的缓存
	 * 
	 * @param cacheName
	 * @return
	 */
	public JedisCacheConfiguration getCacheConfiguration(String cacheName) {
		JedisCacheConfiguration result = jedisCacheConfigurations.get(cacheName);
		if (result == null) {
			result = defaultCache;
		}
		return result;
	}

	/**
	 * 获取配置实例
	 * 
	 * @return
	 */
	public static JedisConfiguration getInstance() {
		if (instance == null) {
			instance = (JedisConfiguration) XMLUtil.convertXmlFileToObject(JedisConfiguration.class,
					SystemPath.getClassPath() + REDIS_CONFIG_FILE);
			instance.addAll(instance.getCaches());
		}
		return instance;
	}
}
