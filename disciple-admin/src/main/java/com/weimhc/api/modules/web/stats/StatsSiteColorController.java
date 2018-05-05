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
import com.weimhc.api.modules.dto.resp.StatsSiteColorDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteColor;
import com.weimhc.modules.stats.service.StatsSiteColorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteColorController")
@RequestMapping(value = "${apiPath}/stats/color")
public class StatsSiteColorController extends ApiBaseController {

	@Autowired
	private StatsSiteColorService statsSiteColorService;

	@ApiOperation(value = "统计访问屏幕色度", notes = "根据时间统计访问者统计访问屏幕色度", tags = {
			"统计" })
	@RequestMapping(value = "findSiteColor", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteColorDto>> findSiteColor(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteColorDto>> apiResult = new ApiResult<>();
		StatsSiteColor statsSiteColor = new StatsSiteColor();
		statsSiteColor.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteColor.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteColor.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteColor> statsSiteColors = statsSiteColorService
				.findSiteColor(statsSiteColor);

		List<StatsSiteColorDto> lists = CustomBeanUtils
				.copyList(statsSiteColors, StatsSiteColorDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}