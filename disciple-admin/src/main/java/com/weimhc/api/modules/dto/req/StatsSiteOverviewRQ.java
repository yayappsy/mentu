/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import java.util.Date;

import com.weimhc.framework.dto.req.AbstractRQ;

/**
 * 网站概况RQ
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteOverviewRQ extends AbstractRQ {

	private static final long serialVersionUID = 1L;
	/**
	 * 查询时间
	 */
	private Date searchDate;

	/**
	 * 获取查询时间
	 * 
	 * @return the searchDate
	 */
	public Date getSearchDate() {
		return searchDate;
	}

	/**
	 * 设置查询时间
	 * 
	 * @param searchDate
	 *            the searchDate to set
	 */
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

}