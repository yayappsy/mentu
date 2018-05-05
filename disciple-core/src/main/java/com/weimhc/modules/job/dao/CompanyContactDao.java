/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.CompanyContact;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 公司面试邀请模板DAO接口
 * @author cwl
 * @version 2018-01-08
 */
@MyBatisDao
public interface CompanyContactDao extends CrudDao<CompanyContact> {

}