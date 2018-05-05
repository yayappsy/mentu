/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteIsp;

/**
 * 统计网络提供商DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteIspDao extends CrudDao<StatsSiteIsp> {
	List<StatsSiteIsp> findSiteIsp(StatsSiteIsp statsSiteIsp);

}