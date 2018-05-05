/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.SchoolLive;

import com.weimhc.framework.persistence.CrudDao;

import java.util.List;

/**
 * 校园经历DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface SchoolLiveDao extends CrudDao<SchoolLive> {

    List<SchoolLive> getByResumeId(String resumeId);
}