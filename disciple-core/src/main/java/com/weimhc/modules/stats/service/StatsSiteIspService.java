/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteIspDao;
import com.weimhc.modules.stats.entity.StatsSiteIsp;

/**
 * 统计网络提供商Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteIspService
		extends CrudServiceImpl<StatsSiteIspDao, StatsSiteIsp> {

	public List<StatsSiteIsp> findSiteIsp(StatsSiteIsp statsSiteIsp) {
		return dao.findSiteIsp(statsSiteIsp);
	}
}