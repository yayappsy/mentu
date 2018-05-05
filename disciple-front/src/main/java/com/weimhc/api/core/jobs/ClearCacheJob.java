/**
 * 
 */
package com.weimhc.api.core.jobs;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thinkgem.javamg.common.utils.EhCacheUtils;
import com.weimhc.framework.job.AbstractJob;
import com.weimhc.modules.sys.utils.AreaUtils;

import net.sf.ehcache.CacheManager;

/**
 * 查询检测状态 定时任务Job
 * 
 * @author zsm
 * @version 2016-13-20
 */
@Component
@Lazy(false)
public class ClearCacheJob extends AbstractJob {

	/***
	 * 定时任务，定时(每分钟)，清除用户缓存
	 */
	@Scheduled(cron = "${job.clearCache.cron}")
	public void clearCache() {
		if (checkJobIsOpen()) {
			CacheManager cacheManager = EhCacheUtils.getCacheManager();
			cacheManager.removeCache(AreaUtils.AREA_CACHE);
			cacheManager.removeCache("sysCache");
			cacheManager.removeCache("memberCache");
		}

	}
}