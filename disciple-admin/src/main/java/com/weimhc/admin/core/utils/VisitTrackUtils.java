/**
 * 
 */
package com.weimhc.admin.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.track.dao.VisitTrackDao;
import com.weimhc.modules.track.entity.VisitTrack;

/**
 * 字典工具类
 * 
 * @version 2014-11-7
 */
public class VisitTrackUtils {

	private static VisitTrackDao visitTrackDao = SpringContextHolder
			.getBean(VisitTrackDao.class);

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(VisitTrackUtils.class);

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request,
			VisitTrack visitTrack) {
		// 异步保存日志
		new SaveVisitTrackThread(visitTrack).start();

	}

	/**
	 * 保存日志线程
	 */
	public static class SaveVisitTrackThread extends Thread {

		private VisitTrack visitTrack;

		public SaveVisitTrackThread(VisitTrack visitTrack) {
			super(SaveVisitTrackThread.class.getSimpleName());
			this.visitTrack = visitTrack;
		}

		@Override
		public void run() {
			try {
				visitTrack.preInsert();
				visitTrackDao.insert(visitTrack);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

}
