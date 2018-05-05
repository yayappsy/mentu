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
import com.weimhc.api.modules.dto.resp.StatsSiteBrowserDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteBrowser;
import com.weimhc.modules.stats.service.StatsSiteBrowserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteBrowserController")
@RequestMapping(value = "${apiPath}/stats/browser")
public class StatsSiteBrowserController extends ApiBaseController {

	@Autowired
	private StatsSiteBrowserService statsSiteBrowserService;

	@ApiOperation(value = "统计访问者浏览器类型", notes = "根据时间统计访问浏览器类型", tags = {
			"统计" })
	@RequestMapping(value = "findSiteBrowser", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteBrowserDto>> findSiteBrowser(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteBrowserDto>> apiResult = new ApiResult<>();
		StatsSiteBrowser statsSiteBrowser = new StatsSiteBrowser();
		statsSiteBrowser
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteBrowser
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteBrowser.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteBrowser> statsSiteBrowsers = statsSiteBrowserService
				.findSiteBrowser(statsSiteBrowser);

		List<StatsSiteBrowserDto> lists = CustomBeanUtils
				.copyList(statsSiteBrowsers, StatsSiteBrowserDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}