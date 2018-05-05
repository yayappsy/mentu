/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteResolution;

/**
 * 统计屏幕分辨率DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteResolutionDao extends CrudDao<StatsSiteResolution> {
	List<StatsSiteResolution> findSiteResolution(
			StatsSiteResolution StatsSiteResolution);

}