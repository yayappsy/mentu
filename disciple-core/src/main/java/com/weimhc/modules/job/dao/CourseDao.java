/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.Course;

import com.weimhc.framework.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 课程培训表DAO接口
 * @author cwl
 * @version 2017-12-31
 */
@MyBatisDao
public interface CourseDao extends CrudDao<Course> {

    void updateCompanyId(@Param(value = "companyId") String companyId);
}