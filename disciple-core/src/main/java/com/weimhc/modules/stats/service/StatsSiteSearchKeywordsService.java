/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteSearchKeywordsDao;
import com.weimhc.modules.stats.entity.StatsSiteSearchKeywords;

/**
 * 统计搜索关键字Service
 * 
 * @author lc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteSearchKeywordsService extends
		CrudServiceImpl<StatsSiteSearchKeywordsDao, StatsSiteSearchKeywords> {

	public List<StatsSiteSearchKeywords> findStatsSiteSearchKeywords(
			StatsSiteSearchKeywords statsSiteSearchKeywords) {
		return dao.findStatsSiteSearchKeywords(statsSiteSearchKeywords);

	}

	public List<StatsSiteSearchKeywords> findStatsSiteSearchKeywordsHour(
			StatsSiteSearchKeywords statsSiteSearchKeywords) {
		return dao.findStatsSiteSearchKeywordsHour(statsSiteSearchKeywords);

	}
}