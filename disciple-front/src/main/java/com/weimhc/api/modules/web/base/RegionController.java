/**
 * 
 */
package com.weimhc.api.modules.web.base;

import com.google.common.collect.Lists;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.AreaDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.base.utils.RegionUtils;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 地区Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Api(value = "area")
@Controller("apiRegionController")
@RequestMapping(value = "${apiPath}/basic/region")
public class RegionController extends ApiBaseController {

	@Autowired
	private AreaService areaService;

	@ApiOperation(value = "省市列表", notes = "省市区列表", tags = { "基础数据" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<AreaDto>> list() {
		ApiResult<List<AreaDto>> result = new ApiResult<>();
		List<AreaDto> lists = Lists.newArrayList();
		List<Region> areaList = RegionUtils.findRegionByType("2");
		lists = CustomBeanUtils.copyList(areaList, AreaDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "省市区列表", notes = "省市区列表", tags = { "基础数据" })
	@RequestMapping(value = "/listByType", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<AreaDto>> list(
			@ApiParam(value = "最低地区类型", required = true, allowableValues = "1,2,3") @RequestParam String type) {
		ApiResult<List<AreaDto>> result = new ApiResult<>();
		List<AreaDto> lists = Lists.newArrayList();
		List<Region> areaList = RegionUtils.findRegionByType(type);
		lists = CustomBeanUtils.copyList(areaList, AreaDto.class);
		result.setData(lists);
		return result;
	}

	@ApiOperation(value = "树形结构的省市区列表", notes = "树形结构的省市区列表", tags = { "基础数据" })
	@RequestMapping(value = "/listTreeByType", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<Map<String, Object>>> listTreeByType(
			@ApiParam(value = "最低地区类型(1:省，2:市，3：区)", required = true, allowableValues = "1,2,3") @RequestParam String type) {
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>();
		List<Map<String, Object>> areaList = RegionUtils.generateFrontRegionJosn("0", type);
		result.setData(areaList);
		return result;
	}

	@ApiOperation(value = "树形结构的省市区列表", notes = "树形结构的省市区列表，通过改变父id获取不同的层级", tags = { "基础数据" })
	@RequestMapping(value = "/listTree", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<Map<String, Object>>> listTreeByType(
			@ApiParam(value = "父id，0代表全球，100000代表中国", required = true, example = "0") @RequestParam String parentId,
			@ApiParam(value = "最低地区类型(1:省，2:市，3：区)", required = true, allowableValues = "1,2,3") @RequestParam String type) {
		ApiResult<List<Map<String, Object>>> result = new ApiResult<>();
		List<Map<String, Object>> areaList = RegionUtils.generateFrontRegionJosn("0", type);
		result.setData(areaList);
		return result;
	}

	@ApiOperation(value = "地区查询-根据父级id查询子级", notes = "地区查询-根据父级id查询子级", tags = { "基础数据" })
	@RequestMapping(value = "/listTreeByParentId", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<Area>> listTreeByParentId(
			@ApiParam(value = "父id，0代表全球，100000代表中国", required = true, example = "0") @RequestParam String parentId
			) {
		ApiResult<List<Area>> result = new ApiResult<>();

		List<Area> list = areaService.getByParentId(parentId);
		result.setData(list);
		return result;
	}

}
