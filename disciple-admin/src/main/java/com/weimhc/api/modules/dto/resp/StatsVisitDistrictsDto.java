/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;

/**
 * 网站概况dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsVisitDistrictsDto extends AbstractDto {

	private static final long serialVersionUID = 1L;

	private List<StatsVisitDistrictDto> statsVisitDistricts = Lists
			.newArrayList();
	/**
	 * 统计平均使用时长
	 */
	private Date averageStatsAccessTime;

	/**
	 * 获取地域统计数据
	 * 
	 * @return the statsVisitDistricts
	 */
	public List<StatsVisitDistrictDto> getStatsVisitDistricts() {
		return statsVisitDistricts;
	}

	/**
	 * 设置地域统计数据
	 * 
	 * @param statsVisitDistricts
	 *            the statsVisitDistricts to set
	 */
	public void setStatsVisitDistricts(
			List<StatsVisitDistrictDto> statsVisitDistricts) {
		this.statsVisitDistricts = statsVisitDistricts;
	}

	/**
	 * @return the averageStatsAccessTime
	 */
	public Date getAverageStatsAccessTime() {
		return averageStatsAccessTime;
	}

	/**
	 * @param averageStatsAccessTime
	 *            the averageStatsAccessTime to set
	 */
	public void setAverageStatsAccessTime(Date averageStatsAccessTime) {
		this.averageStatsAccessTime = averageStatsAccessTime;
	}

}