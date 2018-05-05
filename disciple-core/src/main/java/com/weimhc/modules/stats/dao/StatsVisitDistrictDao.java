/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsVisitDistrict;

/**
 * 地域统计DAO接口
 * 
 * @author lc
 * @version 2017-04-11
 */
@MyBatisDao
public interface StatsVisitDistrictDao extends CrudDao<StatsVisitDistrict> {

	List<StatsVisitDistrict> findStatsVisit(
			StatsVisitDistrict statsVisitDistrict);

}