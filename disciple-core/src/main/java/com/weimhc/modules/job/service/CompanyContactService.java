/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.CompanyContact;
import com.weimhc.modules.job.dao.CompanyContactDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 公司面试邀请模板Service
 * @author cwl
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class CompanyContactService extends CrudServiceImpl<CompanyContactDao, CompanyContact> {

	
}