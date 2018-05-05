/**
 * 
 */
package com.weimhc.modules.job.service;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.Course;
import com.weimhc.modules.job.dao.CourseDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 课程培训表Service
 * @author cwl
 * @version 2017-12-31
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudServiceImpl<CourseDao, Course> {


    @Transactional(readOnly = false)
    public void updateCompanyId(String companyId) {
        dao.updateCompanyId(companyId);
    }
}