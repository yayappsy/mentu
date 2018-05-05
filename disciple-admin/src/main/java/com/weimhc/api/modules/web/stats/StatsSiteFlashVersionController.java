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
import com.weimhc.api.modules.dto.resp.StatsSiteFlashVersionDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteFlashVersion;
import com.weimhc.modules.stats.service.StatsSiteFlashVersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteFlashVersionController")
@RequestMapping(value = "${apiPath}/stats/flashVersion")
public class StatsSiteFlashVersionController extends ApiBaseController {

	@Autowired
	private StatsSiteFlashVersionService statsSiteFlashVersionService;

	@ApiOperation(value = "统计访问者是否可执行flash", notes = "根据时间统计访问者是否可执行flash", tags = {
			"统计" })
	@RequestMapping(value = "findSiteFlashVersion", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteFlashVersionDto>> findSiteFlashVersion(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteFlashVersionDto>> apiResult = new ApiResult<>();
		StatsSiteFlashVersion statsSiteFlashVersion = new StatsSiteFlashVersion();
		statsSiteFlashVersion
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteFlashVersion
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteFlashVersion
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteFlashVersion> statsSiteFlashVersions = statsSiteFlashVersionService
				.findSiteFlashVersion(statsSiteFlashVersion);

		List<StatsSiteFlashVersionDto> lists = CustomBeanUtils.copyList(
				statsSiteFlashVersions, StatsSiteFlashVersionDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}