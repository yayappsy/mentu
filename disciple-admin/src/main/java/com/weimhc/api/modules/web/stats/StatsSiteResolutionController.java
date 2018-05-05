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
import com.weimhc.api.modules.dto.resp.StatsSiteResolutionDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteResolution;
import com.weimhc.modules.stats.service.StatsSiteResolutionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 屏幕分辨率Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteResolutionController")
@RequestMapping(value = "${apiPath}/stats/resolution")
public class StatsSiteResolutionController extends ApiBaseController {

	@Autowired
	private StatsSiteResolutionService statsSiteResolutionService;

	@ApiOperation(value = "统计访问设备屏幕", notes = "根据时间统计访设备屏幕", tags = { "统计" })
	@RequestMapping(value = "findSiteResolution", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteResolutionDto>> findSiteResolution(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteResolutionDto>> apiResult = new ApiResult<>();
		StatsSiteResolution statsSiteResolution = new StatsSiteResolution();
		statsSiteResolution
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteResolution
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteResolution
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteResolution> statsSiteResolutions = statsSiteResolutionService
				.findSiteResolution(statsSiteResolution);

		List<StatsSiteResolutionDto> lists = CustomBeanUtils
				.copyList(statsSiteResolutions, StatsSiteResolutionDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}