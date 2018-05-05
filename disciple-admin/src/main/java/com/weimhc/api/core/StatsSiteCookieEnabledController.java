/**
 * 
 */
package com.weimhc.api.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsSiteCookieEnabledDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteCookieEnabled;
import com.weimhc.modules.stats.service.StatsSiteCookieEnabledService;

import io.swagger.annotations.Api;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsCookieEnabledController")
@RequestMapping(value = "${apiPath}/stats/cookieEnabled")
public class StatsSiteCookieEnabledController extends ApiBaseController {

	@Autowired
	private StatsSiteCookieEnabledService statsSiteCookieEnabledService;

	@RequestMapping(value = "findSiteCookieEnabled")
	@ResponseBody
	public ApiResult<List<StatsSiteCookieEnabledDto>> findSiteCookieEnabled(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteCookieEnabledDto>> apiResult = new ApiResult<>();
		StatsSiteCookieEnabled statsSiteCookieEnabled = new StatsSiteCookieEnabled();
		statsSiteCookieEnabled
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteCookieEnabled
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteCookieEnabled
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteCookieEnabled> statsSiteCookieEnableds = statsSiteCookieEnabledService
				.findSiteCookieEnabled(statsSiteCookieEnabled);

		List<StatsSiteCookieEnabledDto> lists = CustomBeanUtils.copyList(
				statsSiteCookieEnableds, StatsSiteCookieEnabledDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}