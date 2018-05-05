package com.weimhc.config;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.thinkgem.javamg.common.config.Global;

import net.sf.ehcache.CacheManager;

/**
 * 缓存配置
 */
@Configuration
public class EhcacheConfig {

	static {
		System.setProperty(CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY, "true");
	}

	@Bean
	public EhCacheManagerFactoryBean cacheManager() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(
				new ClassPathResource(Global.getConfig("ehcache.configFile")));
		return ehCacheManagerFactoryBean;

	}
}