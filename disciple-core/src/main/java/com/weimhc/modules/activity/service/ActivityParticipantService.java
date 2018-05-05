/**
 * 
 */
package com.weimhc.modules.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.activity.dao.ActivityParticipantDao;
import com.weimhc.modules.activity.entity.ActivityParticipant;

/**
 * 活动申请人Service
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class ActivityParticipantService extends
		SortableServiceImpl<ActivityParticipantDao, ActivityParticipant> {

	/**
	 * 审核申请
	 * 
	 * @param activityParticipant
	 */
	public void updateStatus(ActivityParticipant activityParticipant) {
		dao.updateStatus(activityParticipant);
	}

}