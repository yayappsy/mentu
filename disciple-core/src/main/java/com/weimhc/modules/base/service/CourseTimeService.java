/**
 * 
 */
package com.weimhc.modules.base.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.base.entity.CourseTime;
import com.weimhc.modules.base.dao.CourseTimeDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 课程时间配置Service
 * @author lc
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class CourseTimeService extends SortableServiceImpl<CourseTimeDao, CourseTime> {

	
}