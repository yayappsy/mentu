/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsVisitDistrictDao;
import com.weimhc.modules.stats.entity.StatsVisitDistrict;

/**
 * 地域统计Service
 * 
 * @author lc
 * @version 2017-04-11
 */
@Service
@Transactional(readOnly = true)
public class StatsVisitDistrictService
		extends CrudServiceImpl<StatsVisitDistrictDao, StatsVisitDistrict> {

	public List<StatsVisitDistrict> findStatsVisit(
			StatsVisitDistrict statsVisitDistrict) {
		return dao.findStatsVisit(statsVisitDistrict);
	}
}