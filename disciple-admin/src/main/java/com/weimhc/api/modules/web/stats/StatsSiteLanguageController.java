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
import com.weimhc.api.modules.dto.resp.StatsSiteLanguageDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.stats.entity.StatsSiteLanguage;
import com.weimhc.modules.stats.service.StatsSiteLanguageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsSiteLanguageController")
@RequestMapping(value = "${apiPath}/stats/language")
public class StatsSiteLanguageController extends ApiBaseController {

	@Autowired
	private StatsSiteLanguageService statsSiteLanguageService;

	@ApiOperation(value = "统计访问者语言环境", notes = "根据时间统计访问语言环境", tags = { "统计" })
	@RequestMapping(value = "findSiteLanguage", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsSiteLanguageDto>> findSiteLanguage(
			Date searchDate, HttpServletRequest request) {
		ApiResult<List<StatsSiteLanguageDto>> apiResult = new ApiResult<>();
		StatsSiteLanguage statsSiteLanguage = new StatsSiteLanguage();
		statsSiteLanguage
				.setYear(new SimpleDateFormat("yyyy").format(searchDate));
		statsSiteLanguage
				.setMonth(new SimpleDateFormat("MM").format(searchDate));
		statsSiteLanguage.setDay(new SimpleDateFormat("dd").format(searchDate));
		List<StatsSiteLanguage> statsSiteLanguages = statsSiteLanguageService
				.findSiteLanguage(statsSiteLanguage);

		List<StatsSiteLanguageDto> lists = CustomBeanUtils
				.copyList(statsSiteLanguages, StatsSiteLanguageDto.class);
		apiResult.setData(lists);
		/*	StatsSiteBrowserDto statsSiteBrowserDto = new StatsSiteBrowserDto();
			statsVisitDistrictsDto.setStatsVisitDistricts(lists);
			statsVisitDistrictsDto
					.setAverageStatsAccessTime(averageStatsAccessTime);
			apiResult.setData(statsVisitDistrictsDto);*/
		return apiResult;
	}

}