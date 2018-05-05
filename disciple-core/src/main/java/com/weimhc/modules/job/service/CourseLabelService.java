/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.CourseLabel;
import com.weimhc.modules.job.dao.CourseLabelDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 课程标签Service
 * @author cwl
 * @version 2017-12-31
 */
@Service
@Transactional(readOnly = true)
public class CourseLabelService extends SortableServiceImpl<CourseLabelDao, CourseLabel> {

	
}