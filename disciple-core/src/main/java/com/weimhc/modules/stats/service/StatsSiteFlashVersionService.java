/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteFlashVersionDao;
import com.weimhc.modules.stats.entity.StatsSiteFlashVersion;

/**
 * 统计flash版本Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteFlashVersionService extends
		CrudServiceImpl<StatsSiteFlashVersionDao, StatsSiteFlashVersion> {

	public List<StatsSiteFlashVersion> findSiteFlashVersion(
			StatsSiteFlashVersion statsSiteFlashVersion) {
		return dao.findSiteFlashVersion(statsSiteFlashVersion);
	}
}