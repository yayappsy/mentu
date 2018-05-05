/**
 * 
 */
package com.weimhc.modules.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.activity.dao.ActivityDao;
import com.weimhc.modules.activity.entity.Activity;

/**
 * 活动Service
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class ActivityService
		extends SortableServiceImpl<ActivityDao, Activity> {

}