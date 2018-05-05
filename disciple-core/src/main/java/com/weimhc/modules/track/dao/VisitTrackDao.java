/**
 * 
 */
package com.weimhc.modules.track.dao;

import java.util.Date;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.track.entity.VisitTrack;

/**
 * 访问跟踪DAO接口
 * 
 * @author lc
 * @version 2017-04-07
 */
@MyBatisDao
public interface VisitTrackDao extends CrudDao<VisitTrack> {
	long findRealTimeVisitor(Date searchDate);

	long findRealTimeViewCount(VisitTrack visitTrack);

	long findRealTimeVisitorCount(VisitTrack visitTrack);

	long findMinuteVisitorCount(Date searchDate);

	long findMinuteViewCount(Date searchDate);

	long countFlow(String shareSoureId);
}