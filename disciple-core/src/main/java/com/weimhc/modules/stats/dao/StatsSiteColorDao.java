/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteColor;

/**
 * 统计屏幕颜色DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteColorDao extends CrudDao<StatsSiteColor> {
	List<StatsSiteColor> findSiteColor(StatsSiteColor statsSiteColor);
}