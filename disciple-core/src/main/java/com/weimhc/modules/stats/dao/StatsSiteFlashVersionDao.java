/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteFlashVersion;

/**
 * 统计flash版本DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteFlashVersionDao
		extends CrudDao<StatsSiteFlashVersion> {
	List<StatsSiteFlashVersion> findSiteFlashVersion(
			StatsSiteFlashVersion statsSiteFlashVersion);
}