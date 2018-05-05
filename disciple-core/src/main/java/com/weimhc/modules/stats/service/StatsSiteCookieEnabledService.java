/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteCookieEnabledDao;
import com.weimhc.modules.stats.entity.StatsSiteCookieEnabled;

/**
 * 统计是否支持cookieService
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteCookieEnabledService extends
		CrudServiceImpl<StatsSiteCookieEnabledDao, StatsSiteCookieEnabled> {

	public List<StatsSiteCookieEnabled> findSiteCookieEnabled(
			StatsSiteCookieEnabled statsSiteCookieEnabled) {
		return dao.findSiteCookieEnabled(statsSiteCookieEnabled);
	}
}