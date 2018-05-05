/**
 * 
 */
package com.weimhc.modules.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.base.dao.WorkingYearsDao;
import com.weimhc.modules.base.entity.WorkingYears;

/**
 * 工作年限Service
 * @author lc
 * @version 2017-04-06
 */
@Service
@Transactional(readOnly = true)
public class WorkingYearsService extends SortableServiceImpl<WorkingYearsDao, WorkingYears> {

	
}