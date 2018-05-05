/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.Access;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 个人评价DAO接口
 * @author cwl
 * @version 2018-01-07
 */
@MyBatisDao
public interface AccessDao extends CrudDao<Access> {

    Access getByResumeId(String resumeId);
}