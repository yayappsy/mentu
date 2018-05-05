/**
 * 
 */
package com.weimhc.api.modules.web.stats;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsSiteSourceDto;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.PageRS;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteSource;
import com.weimhc.modules.stats.service.StatsSiteSourceService;
import com.weimhc.modules.track.entity.SourceType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 来源分析Controller
 * 
 * @author lc
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteSourceController")
@RequestMapping(value = "${apiPath}/stats/source")
public class StatsSiteSourceController extends ApiBaseController {

	@Autowired
	private StatsSiteSourceService statsSiteSourceService;

	@ApiOperation(value = "统计访问来源", notes = "根据时间统计访问来源", tags = { "统计" })
	@RequestMapping(value = "findSiteSource", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteSourceDto>> findSiteSource(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteSourceDto>> apiResult = new ApiResult<>();
		StatsSiteSource statsSiteSource = new StatsSiteSource();
		statsSiteSource
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSource.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSource.setDay(new SimpleDateFormat("dd").format(searchDate));

		List<StatsSiteSource> statsSiteSources = statsSiteSourceService
				.findSiteSource(statsSiteSource);
		List<StatsSiteSourceDto> lists = CustomBeanUtils
				.copyList(statsSiteSources, StatsSiteSourceDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

	@ApiOperation(value = "统计访问来源-以小时为单位", notes = "统计访问来源-以小时为单位", tags = {
			"统计" })
	@RequestMapping(value = "findSourceByHour", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteSourceDto>> findSourceByHour(Date searchDate,
			String searchVisit, HttpServletRequest request) {
		ApiResult<List<StatsSiteSourceDto>> apiResult = new ApiResult<>();
		StatsSiteSource statsSiteSource = new StatsSiteSource();
		statsSiteSource
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSource.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSource.setDay(new SimpleDateFormat("dd").format(searchDate));
		if (searchVisit.equals("new")) {
			statsSiteSource.setIfNewVisitor(true);
		} else {
			statsSiteSource.setIfNewVisitor(false);
		}
		List<StatsSiteSource> statsSiteSources = statsSiteSourceService
				.findSourceByHour(statsSiteSource);
		List<StatsSiteSourceDto> lists = CustomBeanUtils
				.copyList(statsSiteSources, StatsSiteSourceDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

	/**
	 * 按照搜索引擎查询
	 * 
	 * @param searchDate
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "统计访问来源-搜索引擎", notes = "统计访问来源-搜索引擎", tags = { "统计" })
	@RequestMapping(value = "findSiteEngine", method = RequestMethod.GET)
	@ResponseBody
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	public ApiResult<PageRS<StatsSiteSourceDto>> findSiteEngine(
			DefaultPageRQ body, Date searchDate, HttpServletRequest request) {
		ApiResult<PageRS<StatsSiteSourceDto>> apiResult = new ApiResult<>();
		StatsSiteSource statsSiteSource = new StatsSiteSource();
		statsSiteSource
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSource.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSource.setDay(new SimpleDateFormat("dd").format(searchDate));
		statsSiteSource.setSourceType(SourceType.searchEngine);
		Page<StatsSiteSource> page = new Page<StatsSiteSource>(body.getPageNo(),
				body.getPageSize());
		List<StatsSiteSource> statsSiteSources = statsSiteSourceService
				.findSiteSource(statsSiteSource);
		page.setList(statsSiteSources);
		List<StatsSiteSourceDto> lists = Lists.newArrayList();
		PageRS<StatsSiteSourceDto> pageRS = new PageRS<>();
		if (page.getList().size() < 1
				|| page.getPageable().getLast() < body.getPageNo()) {
			pageRS.setCurrentCount(0);
		} else {
			lists = CustomBeanUtils.copyList(page.getList(),
					StatsSiteSourceDto.class);
		}
		pageRS.setPageable(page.getPageable());
		pageRS.setDataList(lists);

		apiResult.setData(pageRS);
		return apiResult;
	}

	@ApiOperation(value = "统计访问来源-搜索引擎-按天", notes = "统计访问来源-搜索引擎-按天", tags = {
			"统计" })
	@RequestMapping(value = "findSiteEngineByDay", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteSourceDto>> findSiteEngine(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteSourceDto>> apiResult = new ApiResult<>();
		StatsSiteSource statsSiteSource = new StatsSiteSource();
		statsSiteSource
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSource.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSource.setDay(new SimpleDateFormat("dd").format(searchDate));
		statsSiteSource.setSourceType(SourceType.searchEngine);
		List<StatsSiteSource> statsSiteSources = statsSiteSourceService
				.findSiteSource(statsSiteSource);

		List<StatsSiteSourceDto> lists = CustomBeanUtils
				.copyList(statsSiteSources, StatsSiteSourceDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

	@ApiOperation(value = "统计访问来源-搜索引擎-小时", notes = "统计访问来源-搜索引擎-小时", tags = {
			"统计" })
	@RequestMapping(value = "findSiteEngineByHour", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteSourceDto>> findSiteEngineByHour(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteSourceDto>> apiResult = new ApiResult<>();
		StatsSiteSource statsSiteSource = new StatsSiteSource();
		statsSiteSource
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteSource.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteSource.setDay(new SimpleDateFormat("dd").format(searchDate));
		statsSiteSource.setSourceType(SourceType.searchEngine);
		List<StatsSiteSource> statsSiteSources = statsSiteSourceService
				.findSourceByHour(statsSiteSource);

		List<StatsSiteSourceDto> lists = CustomBeanUtils
				.copyList(statsSiteSources, StatsSiteSourceDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}
}