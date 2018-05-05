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
import com.weimhc.api.modules.dto.resp.StatsSiteOsDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteOs;
import com.weimhc.modules.stats.service.StatsSiteOsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 统计网络设备类型Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteOsController")
@RequestMapping(value = "${apiPath}/stats/os")
public class StatsSiteOsController extends ApiBaseController {

	@Autowired
	private StatsSiteOsService statsSiteOsService;

	@ApiOperation(value = "统计访问设备操作系统", notes = "根据时间统计访问设备操作系统", tags = {
			"统计" })
	@RequestMapping(value = "findSiteOs", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteOsDto>> findSiteOs(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteOsDto>> apiResult = new ApiResult<>();
		StatsSiteOs statsSiteOs = new StatsSiteOs();
		statsSiteOs.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteOs.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteOs.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteOs> statsSiteOsList = statsSiteOsService
				.findSiteOs(statsSiteOs);

		List<StatsSiteOsDto> lists = CustomBeanUtils.copyList(statsSiteOsList,
				StatsSiteOsDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}