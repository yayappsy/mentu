/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteSource;

/**
 * 统计来源网站DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteSourceDao extends CrudDao<StatsSiteSource> {
	List<StatsSiteSource> findSiteSource(StatsSiteSource statsSiteSource);

	List<StatsSiteSource> findSourceByHour(StatsSiteSource statsSiteSource);
}