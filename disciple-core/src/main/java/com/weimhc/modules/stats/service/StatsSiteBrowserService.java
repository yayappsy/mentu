/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteBrowserDao;
import com.weimhc.modules.stats.entity.StatsSiteBrowser;

/**
 * 统计浏览器Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteBrowserService
		extends CrudServiceImpl<StatsSiteBrowserDao, StatsSiteBrowser> {

	public List<StatsSiteBrowser> findSiteBrowser(
			StatsSiteBrowser statsSiteBrowser) {
		return dao.findSiteBrowser(statsSiteBrowser);
	}
}