/**
 * 
 */
package com.weimhc.modules.stats.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.stats.dao.StatsSiteVisitorDao;
import com.weimhc.modules.stats.entity.StatsSiteVisitor;

/**
 * 统计新老访客Service
 * 
 * @author lc
 * @version 2017-04-14
 */
@Service
@Transactional(readOnly = true)
public class StatsSiteVisitorService
		extends CrudServiceImpl<StatsSiteVisitorDao, StatsSiteVisitor> {

	public StatsSiteVisitor findStatsSiteVisitor(
			StatsSiteVisitor statsSiteVisitor) {
		return dao.findStatsSiteVisitor(statsSiteVisitor);
	}

	public List<StatsSiteVisitor> findStatsVisitor(
			StatsSiteVisitor statsSiteVisitor) {
		return dao.findStatsVisitor(statsSiteVisitor);
	}
}