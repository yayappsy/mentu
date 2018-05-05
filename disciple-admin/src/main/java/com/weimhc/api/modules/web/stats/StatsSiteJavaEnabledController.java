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
import com.weimhc.api.modules.dto.resp.StatsSiteJavaEnabledDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteJavaEnabled;
import com.weimhc.modules.stats.service.StatsSiteJavaEnabledService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsJavaEnabledController")
@RequestMapping(value = "${apiPath}/stats/javaEnabled")
public class StatsSiteJavaEnabledController extends ApiBaseController {

	@Autowired
	private StatsSiteJavaEnabledService statsSiteJavaEnabledService;

	@ApiOperation(value = "统计访问者是否可执行java", notes = "根据时间统计访问者是否可执行java", tags = {
			"统计" })
	@RequestMapping(value = "findSiteJavaEnabled", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteJavaEnabledDto>> findSiteJavaEnabled(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteJavaEnabledDto>> apiResult = new ApiResult<>();
		StatsSiteJavaEnabled statsSiteJavaEnabled = new StatsSiteJavaEnabled();
		statsSiteJavaEnabled
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteJavaEnabled
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteJavaEnabled
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteJavaEnabled> statsSiteJavaEnableds = statsSiteJavaEnabledService
				.findSiteJavaEnabled(statsSiteJavaEnabled);

		List<StatsSiteJavaEnabledDto> lists = CustomBeanUtils
				.copyList(statsSiteJavaEnableds, StatsSiteJavaEnabledDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}