/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.LearningLive;

import com.weimhc.framework.persistence.CrudDao;

import java.util.List;

/**
 * 学术经历DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface LearningLiveDao extends CrudDao<LearningLive> {

    List<LearningLive> getByResumeId(String resumeId);
}