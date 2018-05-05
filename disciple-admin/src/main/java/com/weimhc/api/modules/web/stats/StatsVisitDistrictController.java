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

import com.thinkgem.javamg.common.utils.DateUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsVisitDistrictDto;
import com.weimhc.api.modules.dto.resp.StatsVisitDistrictsDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsVisitDistrict;
import com.weimhc.modules.stats.service.StatsVisitDistrictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsVisitDistrictController")
@RequestMapping(value = "${apiPath}/stats/visit")
public class StatsVisitDistrictController extends ApiBaseController {

	@Autowired
	private StatsVisitDistrictService statsVisitDistrictService;

	@ApiOperation(value = "统计访问地区", notes = "根据时间统计访问地区", tags = { "统计" })
	@RequestMapping(value = "findSiteVitit", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsVisitDistrictsDto> findSiteVitit(Date searchDate,
			HttpServletRequest request) {
		ApiResult<StatsVisitDistrictsDto> apiResult = new ApiResult<>();
		StatsVisitDistrict statsVisitDistrict = new StatsVisitDistrict();
		statsVisitDistrict
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsVisitDistrict
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsVisitDistrict
				.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsVisitDistrict> statsVisitDistricts = statsVisitDistrictService
				.findStatsVisit(statsVisitDistrict);
		long averageStatsAccessTime = 0;
		for (StatsVisitDistrict statsVisitDistrictCount : statsVisitDistricts) {

			averageStatsAccessTime = averageStatsAccessTime
					+ statsVisitDistrictCount.getAverageAccessTime().getTime();
		}
		averageStatsAccessTime = averageStatsAccessTime
				/ statsVisitDistricts.size();
		List<StatsVisitDistrictDto> lists = CustomBeanUtils
				.copyList(statsVisitDistricts, StatsVisitDistrictDto.class);
		StatsVisitDistrictsDto statsVisitDistrictsDto = new StatsVisitDistrictsDto();
		statsVisitDistrictsDto.setStatsVisitDistricts(lists);
		statsVisitDistrictsDto.setAverageStatsAccessTime(DateUtils
				.parseDate(DateUtils.formatDateTime(averageStatsAccessTime)));
		apiResult.setData(statsVisitDistrictsDto);
		return apiResult;
	}

}