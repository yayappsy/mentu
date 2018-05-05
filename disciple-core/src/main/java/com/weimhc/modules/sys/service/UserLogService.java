/**
 * Copyright &copy; 2012-2013 <a href="#">javamg</a> All rights reserved.
 */
package com.weimhc.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.service.impl.CrudServiceImpl;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.weimhc.modules.sys.dao.UserLogDao;
import com.weimhc.modules.sys.entity.UserLog;

/**
 * 日志Service
 * 
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class UserLogService extends CrudServiceImpl<UserLogDao, UserLog> {

	public Page<UserLog> findPage(Page<UserLog> page, UserLog log) {

		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null) {
			log.setBeginDate(DateUtils
					.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null) {
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}

		return super.findPage(page, log);

	}

}
