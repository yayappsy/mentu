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
import com.weimhc.api.modules.dto.resp.StatsSiteIspDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteIsp;
import com.weimhc.modules.stats.service.StatsSiteIspService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteIspController")
@RequestMapping(value = "${apiPath}/stats/isp")
public class StatsSiteIspController extends ApiBaseController {

	@Autowired
	private StatsSiteIspService statsSiteIspService;

	@ApiOperation(value = "统计访问者网络信息", notes = "根据时间统计访问者网络信息", tags = { "统计" })
	@RequestMapping(value = "findSiteIsp", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteIspDto>> findSiteIsp(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsSiteIspDto>> apiResult = new ApiResult<>();
		StatsSiteIsp statsSiteIsp = new StatsSiteIsp();
		statsSiteIsp.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteIsp.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteIsp.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteIsp> statsSiteIsps = statsSiteIspService
				.findSiteIsp(statsSiteIsp);

		List<StatsSiteIspDto> lists = CustomBeanUtils.copyList(statsSiteIsps,
				StatsSiteIspDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}