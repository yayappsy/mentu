/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.base.entity.CourseTime;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 课程时间配置DAO接口
 * @author lc
 * @version 2017-06-12
 */
@MyBatisDao
public interface CourseTimeDao extends SortableDao<CourseTime> {

}