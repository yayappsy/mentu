/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.CourseLabel;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 课程标签DAO接口
 * @author cwl
 * @version 2017-12-31
 */
@MyBatisDao
public interface CourseLabelDao extends SortableDao<CourseLabel> {

}