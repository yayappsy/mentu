/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteJavaEnabledDao;
import com.weimhc.modules.stats.entity.StatsSiteJavaEnabled;

/**
 * 统计是否支持javaService
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteJavaEnabledService
		extends CrudServiceImpl<StatsSiteJavaEnabledDao, StatsSiteJavaEnabled> {

	public List<StatsSiteJavaEnabled> findSiteJavaEnabled(
			StatsSiteJavaEnabled statsSiteJavaEnabled) {
		return dao.findSiteJavaEnabled(statsSiteJavaEnabled);
	}
}