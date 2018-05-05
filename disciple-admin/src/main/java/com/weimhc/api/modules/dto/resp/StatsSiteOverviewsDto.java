/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;

/**
 * 网站概况dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteOverviewsDto extends AbstractDto {

	private static final long serialVersionUID = 1L;

	private List<StatsSiteOverviewDto> statsSiteOverviews = Lists
			.newArrayList();

	private String searchParameter;
	private String timeParameter;

	/**
	 * @return the statsSiteOverviews
	 */
	public List<StatsSiteOverviewDto> getStatsSiteOverviews() {
		return statsSiteOverviews;
	}

	/**
	 * @param statsSiteOverviews
	 *            the statsSiteOverviews to set
	 */
	public void setStatsSiteOverviews(
			List<StatsSiteOverviewDto> statsSiteOverviews) {
		this.statsSiteOverviews = statsSiteOverviews;
	}

	/**
	 * @return the searchParameter
	 */
	public String getSearchParameter() {
		return searchParameter;
	}

	/**
	 * @param searchParameter
	 *            the searchParameter to set
	 */
	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}

	/**
	 * @return the timeParameter
	 */
	public String getTimeParameter() {
		return timeParameter;
	}

	/**
	 * @param timeParameter
	 *            the timeParameter to set
	 */
	public void setTimeParameter(String timeParameter) {
		this.timeParameter = timeParameter;
	}

}