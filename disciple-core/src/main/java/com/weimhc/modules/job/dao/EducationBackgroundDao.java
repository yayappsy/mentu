/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.EducationBackground;

import com.weimhc.framework.persistence.CrudDao;

import java.util.List;

/**
 * 教育背景DAO接口
 * @author cwl
 * @version 2017-12-26
 */
@MyBatisDao
public interface EducationBackgroundDao extends CrudDao<EducationBackground> {

    List<EducationBackground> getByResumeId(String resumeId);
}