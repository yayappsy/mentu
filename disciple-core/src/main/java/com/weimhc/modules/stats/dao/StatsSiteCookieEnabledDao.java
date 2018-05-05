/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteCookieEnabled;

/**
 * 统计是否支持cookieDAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteCookieEnabledDao
		extends CrudDao<StatsSiteCookieEnabled> {
	List<StatsSiteCookieEnabled> findSiteCookieEnabled(
			StatsSiteCookieEnabled statsSiteCookieEnabled);
}