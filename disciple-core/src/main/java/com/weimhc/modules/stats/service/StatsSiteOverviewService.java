/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteOverviewDao;
import com.weimhc.modules.stats.entity.StatsSiteOverview;

/**
 * 统计网站概况Service
 * 
 * @author lc
 * @version 2017-04-10
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteOverviewService
		extends CrudServiceImpl<StatsSiteOverviewDao, StatsSiteOverview> {

	public StatsSiteOverview findStatsSiteOverview(
			StatsSiteOverview statsSiteOverview) {
		return dao.findStatsSiteOverview(statsSiteOverview);
	}

	public List<StatsSiteOverview> findStatsSiteHour(
			StatsSiteOverview statsSiteOverview) {
		return dao.findStatsSiteHour(statsSiteOverview);
	}
}