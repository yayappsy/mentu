/**
 * 
 */
package com.weimhc.framework.spring;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

/**
 * 增加设置 requiredShutdownHook开关
 *
 * @author laozh
 * @version 2017年5月18日
 */
public class CustomEhCacheManagerFactoryBean extends EhCacheManagerFactoryBean {

	private boolean requiredShutdownHook;

	/**
	 * 返回 设置 是否需要开启 ehcache shutdown hook
	 * 
	 * @return the requiredShutdownHook
	 */
	public boolean isRequiredShutdownHook() {
		return requiredShutdownHook;
	}

	/**
	 * 设置 返回是否需要开启 ehcache shutdown hook
	 * 
	 * @param requiredShutdownHook
	 *            the requiredShutdownHook to set
	 */
	public void setRequiredShutdownHook(boolean requiredShutdownHook) {
		this.requiredShutdownHook = requiredShutdownHook;
	}

	@Override
	public void afterPropertiesSet() throws CacheException {
		if (requiredShutdownHook) {
			System.setProperty(CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,
					"true");
		}
		super.afterPropertiesSet();
	}

}
