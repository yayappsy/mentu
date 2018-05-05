/**
 * 
 */
package com.weimhc.modules.track.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.track.dao.VisitTrackDao;
import com.weimhc.modules.track.entity.VisitTrack;

/**
 * 访问跟踪Service
 * 
 * @author lc
 * @version 2017-04-07
 */
@Service
@Transactional(readOnly = true)
public class VisitTrackService
		extends CrudServiceImpl<VisitTrackDao, VisitTrack> {
	public long findRealTimeVisitor(Date searchDate) {
		return dao.findRealTimeVisitor(searchDate);
	}

	public long findRealTimeViewCount(VisitTrack visitTrack) {
		return dao.findRealTimeViewCount(visitTrack);
	}

	public long findRealTimeVisitorCount(VisitTrack visitTrack) {
		return dao.findRealTimeVisitorCount(visitTrack);
	}

	public long findMinuteVisitorCount(Date searchDate) {
		return dao.findMinuteVisitorCount(searchDate);
	}

	public long findMinuteViewCount(Date searchDate) {
		return dao.findMinuteViewCount(searchDate);
	}

	public long countFlow(String shareSoureId) {
		return dao.countFlow(shareSoureId);
	}
}