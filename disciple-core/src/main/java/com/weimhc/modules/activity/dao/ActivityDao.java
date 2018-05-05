/**
 * 
 */
package com.weimhc.modules.activity.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.activity.entity.Activity;

/**
 * 活动DAO接口
 * @author zsm
 * @version 2017-04-24
 */
@MyBatisDao
public interface ActivityDao extends SortableDao<Activity> {
	
}