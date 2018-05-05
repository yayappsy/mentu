/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.ExpectPractice;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 期望实习DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface ExpectPracticeDao extends CrudDao<ExpectPractice> {

    String getIndustryIds(String userId);
}