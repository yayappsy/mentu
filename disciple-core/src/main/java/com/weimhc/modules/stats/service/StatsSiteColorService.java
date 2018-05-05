/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteColorDao;
import com.weimhc.modules.stats.entity.StatsSiteColor;

/**
 * 统计屏幕颜色Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteColorService
		extends CrudServiceImpl<StatsSiteColorDao, StatsSiteColor> {

	public List<StatsSiteColor> findSiteColor(StatsSiteColor statsSiteColor) {
		return dao.findSiteColor(statsSiteColor);
	}
}