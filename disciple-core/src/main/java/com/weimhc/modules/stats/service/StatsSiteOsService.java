/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteOsDao;
import com.weimhc.modules.stats.entity.StatsSiteOs;

/**
 * 统计网络设备类型Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteOsService
		extends CrudServiceImpl<StatsSiteOsDao, StatsSiteOs> {

	public List<StatsSiteOs> findSiteOs(StatsSiteOs statsSiteOs) {
		return dao.findSiteOs(statsSiteOs);
	}
}