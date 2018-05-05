/**
 * 
 */
package com.weimhc.api.modules.web.stats;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsSiteOverviewDto;
import com.weimhc.api.modules.dto.resp.StatsSiteOverviewsDto;
import com.weimhc.api.modules.dto.resp.StatsSiteSearchKeywordsDto;
import com.weimhc.api.modules.dto.resp.StatsSiteVisitorDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteOverview;
import com.weimhc.modules.stats.entity.StatsSiteSearchKeywords;
import com.weimhc.modules.stats.entity.StatsSiteVisitor;
import com.weimhc.modules.stats.service.StatsSiteOverviewService;
import com.weimhc.modules.stats.service.StatsSiteSearchKeywordsService;
import com.weimhc.modules.stats.service.StatsSiteVisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsOverviewController")
@RequestMapping(value = "${apiPath}/stats/overview")
public class StatsSiteOverviewController extends ApiBaseController {

	@Autowired
	private StatsSiteOverviewService statsSiteOverviewService;
	@Autowired
	private StatsSiteSearchKeywordsService statsSiteSearchKeywordsService;
	@Autowired
	private StatsSiteVisitorService statsSiteVisitorService;

	@ApiOperation(value = "统计浏览量", notes = "根据时间统计统计浏览量", tags = { "统计" })
	@RequestMapping(value = "findSiteView", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsSiteOverviewDto> findSiteView(Date searchDate,
			HttpServletRequest request) {
		ApiResult<StatsSiteOverviewDto> apiResult = new ApiResult<>();
		StatsSiteOverview statsSiteOverview = new StatsSiteOverview();
		/*statsSiteOverview.setYear(DateUtils.getYear());
		statsSiteOverview.setMonth(DateUtils.getMonth());
		statsSiteOverview.setDay(DateUtils.getDay());*/
		statsSiteOverview
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteOverview
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteOverview.setDay(new SimpleDateFormat("dd").format(searchDate));
		statsSiteOverview = statsSiteOverviewService
				.findStatsSiteOverview(statsSiteOverview);
		StatsSiteOverviewDto statsSiteOverviewDto = new StatsSiteOverviewDto();
		BeanUtils.copyProperties(statsSiteOverview, statsSiteOverviewDto);
		apiResult.setData(statsSiteOverviewDto);
		return apiResult;
	}

	@ApiOperation(value = "统计访问趋势", notes = "访问趋势", tags = { "统计" })
	@RequestMapping(value = "findSiteViewTrend", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsSiteOverviewsDto> findSiteViewTrend(
			String searchParameter, Date searchDate,
			HttpServletRequest request) {
		ApiResult<StatsSiteOverviewsDto> apiResult = new ApiResult<>();
		StatsSiteOverview statsSiteOverview = new StatsSiteOverview();
		statsSiteOverview
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteOverview
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteOverview.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteOverview> statsSiteOverviewList = statsSiteOverviewService
				.findStatsSiteHour(statsSiteOverview);
		List<StatsSiteOverviewDto> lists = CustomBeanUtils
				.copyList(statsSiteOverviewList, StatsSiteOverviewDto.class);
		StatsSiteOverviewsDto statsSiteOverviewDto = new StatsSiteOverviewsDto();
		statsSiteOverviewDto.setStatsSiteOverviews(lists);
		statsSiteOverviewDto.setSearchParameter(searchParameter);
		apiResult.setData(statsSiteOverviewDto);
		return apiResult;
	}

	/**
	 * 统计搜索关键字
	 * 
	 * @param searchDate
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "统计搜索关键字", notes = "统计搜索关键字", tags = { "统计" })
	@RequestMapping(value = "findStatsSiteSearchKeyword", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsSiteSearchKeywordsDto> findStatsSiteSearchKeywords(
			Date searchDate, HttpServletRequest request) {
		ApiResult<StatsSiteSearchKeywordsDto> apiResult = new ApiResult<>();
		StatsSiteSearchKeywords statsSiteSearchKeywords = new StatsSiteSearchKeywords();
		statsSiteSearchKeywords
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSearchKeywords
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSearchKeywords
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteSearchKeywords> statsSiteSearchKeywordsList = statsSiteSearchKeywordsService
				.findStatsSiteSearchKeywords(statsSiteSearchKeywords);

		StatsSiteSearchKeywordsDto statsSiteSearchKeywordsDto = new StatsSiteSearchKeywordsDto();
		statsSiteSearchKeywordsDto
				.setStatsSiteSearchKeywords(statsSiteSearchKeywordsList);
		apiResult.setData(statsSiteSearchKeywordsDto);
		return apiResult;
	}

	/**
	 * 统计新老访客
	 * 
	 * @param searchDate
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "统计访问者", notes = "统计访问者", tags = { "统计" })
	@RequestMapping(value = "findVisitor", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsSiteVisitorDto> findStatsSiteVisitor(
			boolean ifNewVisitor, Date searchDate, HttpServletRequest request) {
		ApiResult<StatsSiteVisitorDto> apiResult = new ApiResult<>();
		StatsSiteVisitor statsSiteVisitor = new StatsSiteVisitor();
		statsSiteVisitor
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteVisitor
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteVisitor.setDay(new SimpleDateFormat("dd").format(searchDate));
		statsSiteVisitor.setIfNewVisitor(ifNewVisitor);
		StatsSiteVisitor searchStatsSiteVisitor = statsSiteVisitorService
				.findStatsSiteVisitor(statsSiteVisitor);

		StatsSiteVisitorDto statsSiteVisitorDto = new StatsSiteVisitorDto();
		BeanUtils.copyProperties(searchStatsSiteVisitor, statsSiteVisitorDto);
		apiResult.setData(statsSiteVisitorDto);
		return apiResult;
	}

}