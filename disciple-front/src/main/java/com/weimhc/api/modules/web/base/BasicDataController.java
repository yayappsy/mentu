/**
 * 
 */
package com.weimhc.api.modules.web.base;

import com.google.common.collect.Lists;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.BaseNameDto;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.base.entity.BaseIndustry;
import com.weimhc.modules.base.entity.Education;
import com.weimhc.modules.base.entity.Nation;
import com.weimhc.modules.base.entity.ReligiousBelief;
import com.weimhc.modules.base.utils.BaseIndustryUtils;
import com.weimhc.modules.base.utils.EducationUtils;
import com.weimhc.modules.base.utils.NationUtils;
import com.weimhc.modules.base.utils.ReligiousBeliefUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 基础数据Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiBasicDataController")
@RequestMapping(value = "${apiPath}/basic")
public class BasicDataController extends ApiBaseController {

	@ApiOperation(value = "获取民族列表", notes = "获取民族列表", tags = "基础数据", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/nation/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<BaseNameDto>> nationList(HttpServletRequest request) {
		ApiResult<List<BaseNameDto>> result = new ApiResult<>();
		List<Nation> list = NationUtils.findAll();
		List<BaseNameDto> lists = CustomBeanUtils.copyList(list, BaseNameDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "获取宗教信仰列表", notes = "获取宗教信仰列表", tags = "基础数据", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/religiousBelief/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<BaseNameDto>> religiousBeliefList(HttpServletRequest request) {
		ApiResult<List<BaseNameDto>> result = new ApiResult<>();
		List<ReligiousBelief> list = ReligiousBeliefUtils.findAll();
		List<BaseNameDto> lists = CustomBeanUtils.copyList(list, BaseNameDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "获取职业列表", notes = "获取职业列表", tags = "基础数据", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/industry/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<BaseNameDto>> industryList(HttpServletRequest request) {
		ApiResult<List<BaseNameDto>> result = new ApiResult<>();
		List<BaseIndustry> list = BaseIndustryUtils.findAll();
		List<BaseNameDto> lists = Lists.newArrayList();
		lists = CustomBeanUtils.copyList(list, BaseNameDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "获取文化程度列表", notes = "获取文化程度列表", tags = "基础数据", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/education/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<BaseNameDto>> educationList(HttpServletRequest request) {
		ApiResult<List<BaseNameDto>> result = new ApiResult<>();
		List<Education> list = EducationUtils.findAll();
		List<BaseNameDto> lists = Lists.newArrayList();
		lists = CustomBeanUtils.copyList(list, BaseNameDto.class);
		result.setData(lists);
		return result;
	}

}