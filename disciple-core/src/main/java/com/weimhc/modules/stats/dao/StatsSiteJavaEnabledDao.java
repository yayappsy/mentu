/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteJavaEnabled;

/**
 * 统计是否支持javaDAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteJavaEnabledDao extends CrudDao<StatsSiteJavaEnabled> {
	List<StatsSiteJavaEnabled> findSiteJavaEnabled(
			StatsSiteJavaEnabled statsSiteJavaEnabled);
}