/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.ExpectPractice;
import com.weimhc.modules.job.dao.ExpectPracticeDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 期望实习Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class ExpectPracticeService extends CrudServiceImpl<ExpectPracticeDao, ExpectPractice> {

	
}