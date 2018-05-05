/**
 * 
 */
package com.weimhc.modules.activity.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.activity.entity.ActivityParticipant;

/**
 * 活动申请人DAO接口
 * 
 * @author zsm
 * @version 2017-04-24
 */
@MyBatisDao
public interface ActivityParticipantDao
		extends SortableDao<ActivityParticipant> {

	/**
	 * 更新申请状态
	 * 
	 * @param activityParticipant
	 */
	int updateStatus(ActivityParticipant activityParticipant);

}