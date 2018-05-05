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

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsSiteVisitorDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteVisitor;
import com.weimhc.modules.stats.service.StatsSiteVisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 新老访客Controller
 * 
 * @author lc
 * @version 2017-04-19
 */
@Api
@Controller("apiAdminStatsSiteVisitorController")
@RequestMapping(value = "${apiPath}/stats/visitor")
public class StatsSiteVisitorController extends ApiBaseController {

	@Autowired
	private StatsSiteVisitorService statsSiteVisitorService;

	@ApiOperation(value = "统计访问者", notes = "根据时间统计访问者", tags = { "统计" })
	@RequestMapping(value = "findSiteByVisitor", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteVisitorDto>> findSiteByVisitor(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteVisitorDto>> apiResult = new ApiResult<>();
		StatsSiteVisitor statsSiteVisitor = new StatsSiteVisitor();
		statsSiteVisitor
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteVisitor
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteVisitor.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteVisitor> statsSiteVisitors = statsSiteVisitorService
				.findStatsVisitor(statsSiteVisitor);

		List<StatsSiteVisitorDto> lists = CustomBeanUtils
				.copyList(statsSiteVisitors, StatsSiteVisitorDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}