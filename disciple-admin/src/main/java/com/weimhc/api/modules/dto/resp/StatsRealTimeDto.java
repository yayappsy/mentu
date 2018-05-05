/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import java.util.Date;

import com.weimhc.framework.dto.AbstractDto;

/**
 * 网站基础dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsRealTimeDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;

	/**
	 * 实时在线人数
	 */
	private long realTimeVisitor;
	/**
	 * 当前浏览量
	 */
	private long realTimeViewCount;
	/**
	 * 当前访客数
	 */
	private long realTimeVisitorCount;

	/**
	 * 当前访客数
	 */
	private Date realTime;

	/**
	 * 获取实时在线人数
	 * 
	 * @return the realTimeVisitor
	 */
	public Long getRealTimeVisitor() {
		return realTimeVisitor;
	}

	/**
	 * 设置实时在线人数
	 * 
	 * @param realTimeVisitor
	 *            the realTimeVisitor to set
	 */
	public void setRealTimeVisitor(long realTimeVisitor) {
		this.realTimeVisitor = realTimeVisitor;
	}

	/**
	 * 获取当前浏览量
	 * 
	 * @return the realTimeViewCount
	 */
	public long getRealTimeViewCount() {
		return realTimeViewCount;
	}

	/**
	 * 设置当前浏览量
	 * 
	 * @param realTimeViewCount
	 *            the realTimeViewCount to set
	 */
	public void setRealTimeViewCount(long realTimeViewCount) {
		this.realTimeViewCount = realTimeViewCount;
	}

	/**
	 * 获取当前访客数
	 * 
	 * @return the realTimeVisitorCount
	 */
	public long getRealTimeVisitorCount() {
		return realTimeVisitorCount;
	}

	/**
	 * 设置当前访客数
	 * 
	 * @param realTimeVisitorCount
	 *            the realTimeVisitorCount to set
	 */
	public void setRealTimeVisitorCount(long realTimeVisitorCount) {
		this.realTimeVisitorCount = realTimeVisitorCount;
	}

	/**
	 * @return the realTime
	 */
	public Date getRealTime() {
		return realTime;
	}

	/**
	 * @param realTime
	 *            the realTime to set
	 */
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}

}