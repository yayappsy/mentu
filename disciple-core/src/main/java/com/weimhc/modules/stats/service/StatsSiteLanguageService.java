/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteLanguageDao;
import com.weimhc.modules.stats.entity.StatsSiteLanguage;

/**
 * 统计语言环境Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteLanguageService
		extends CrudServiceImpl<StatsSiteLanguageDao, StatsSiteLanguage> {

	public List<StatsSiteLanguage> findSiteLanguage(
			StatsSiteLanguage statsSiteLanguage) {
		return dao.findSiteLanguage(statsSiteLanguage);
	}
}