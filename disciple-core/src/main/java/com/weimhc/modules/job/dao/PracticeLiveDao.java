/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.PracticeLive;

import com.weimhc.framework.persistence.CrudDao;

import java.util.List;

/**
 * 实习经历DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface PracticeLiveDao extends CrudDao<PracticeLive> {

    List<PracticeLive> getByResumeId(String resumeId);
}