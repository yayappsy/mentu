/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteLanguage;

/**
 * 统计语言环境DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteLanguageDao extends CrudDao<StatsSiteLanguage> {

	List<StatsSiteLanguage> findSiteLanguage(
			StatsSiteLanguage statsSiteLanguage);

}