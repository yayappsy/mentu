/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteSourceDao;
import com.weimhc.modules.stats.entity.StatsSiteSource;

/**
 * 统计来源网站Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteSourceService
		extends CrudServiceImpl<StatsSiteSourceDao, StatsSiteSource> {

	public List<StatsSiteSource> findSiteSource(
			StatsSiteSource statsSiteSource) {
		return dao.findSiteSource(statsSiteSource);

	}

	public List<StatsSiteSource> findSourceByHour(
			StatsSiteSource statsSiteSource) {
		return dao.findSourceByHour(statsSiteSource);
	}
}