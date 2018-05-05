/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteSearchKeywords;

/**
 * 统计搜索关键字DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteSearchKeywordsDao
		extends CrudDao<StatsSiteSearchKeywords> {

	List<StatsSiteSearchKeywords> findStatsSiteSearchKeywords(
			StatsSiteSearchKeywords statsSiteSearchKeywords);

	List<StatsSiteSearchKeywords> findStatsSiteSearchKeywordsHour(
			StatsSiteSearchKeywords statsSiteSearchKeywords);

}