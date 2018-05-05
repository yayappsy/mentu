/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteVisitor;

/**
 * 统计新老访客DAO接口
 * 
 * @author lc
 * @version 2017-04-14
 */
@MyBatisDao
public interface StatsSiteVisitorDao extends CrudDao<StatsSiteVisitor> {
	StatsSiteVisitor findStatsSiteVisitor(StatsSiteVisitor statsSiteVisitor);

	List<StatsSiteVisitor> findStatsVisitor(StatsSiteVisitor statsSiteVisitor);
}