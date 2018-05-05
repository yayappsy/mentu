/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import java.util.List;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;
import com.weimhc.modules.stats.entity.StatsSiteSearchKeywords;

/**
 * 网站概况dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteSearchKeywordsDto extends AbstractDto {

	private static final long serialVersionUID = 1L;

	private List<StatsSiteSearchKeywords> statsSiteSearchKeywords = Lists
			.newArrayList();

	/**
	 * @return the statsSiteSearchKeywords
	 */
	public List<StatsSiteSearchKeywords> getStatsSiteSearchKeywords() {
		return statsSiteSearchKeywords;
	}

	/**
	 * @param statsSiteSearchKeywords
	 *            the statsSiteSearchKeywords to set
	 */
	public void setStatsSiteSearchKeywords(
			List<StatsSiteSearchKeywords> statsSiteSearchKeywords) {
		this.statsSiteSearchKeywords = statsSiteSearchKeywords;
	}

}