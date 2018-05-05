/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteResolutionDao;
import com.weimhc.modules.stats.entity.StatsSiteResolution;

/**
 * 统计屏幕分辨率Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteResolutionService
		extends CrudServiceImpl<StatsSiteResolutionDao, StatsSiteResolution> {

	public List<StatsSiteResolution> findSiteResolution(
			StatsSiteResolution StatsSiteResolution) {
		return dao.findSiteResolution(StatsSiteResolution);
	}
}