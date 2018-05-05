/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteOverview;

/**
 * 统计网站概况DAO接口
 * 
 * @author lc
 * @version 2017-04-10
 */
@MyBatisDao
public interface StatsSiteOverviewDao extends CrudDao<StatsSiteOverview> {

	StatsSiteOverview findStatsSiteOverview(
			StatsSiteOverview statsSiteOverview);

	List<StatsSiteOverview> findStatsSiteHour(
			StatsSiteOverview statsSiteOverview);

}