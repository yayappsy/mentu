package com.weimhc.api.test.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.JedisUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.test.dto.CityDto;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api
@Controller
@RequestMapping("${apiPath}/city")
public class CityController extends ApiBaseController {

	@ApiOperation(value = "新增城市信息", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "新增城市信息")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<CityDto> add(@RequestBody CityDto body,
			HttpServletRequest request) {
		ApiResult<CityDto> api = new ApiResult<CityDto>();
		JedisUtils.setObject(body.getId(), body, 60 * 60);
		api.setData(body);

		return api;
	}

	@ApiOperation(value = "修改城市信息", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "修改城市信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<CityDto> update(@RequestBody CityDto body,
			HttpServletRequest request) {
		ApiResult<CityDto> api = new ApiResult<CityDto>();
		CityDto dd = (CityDto) JedisUtils.getObject(body.getId());
		dd.setName(body.getName());
		JedisUtils.setObject(body.getId(), dd, 60 * 60);
		api.setData(body);

		return api;
	}

	@ApiOperation(value = "删除城市信息", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "删除城市信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<CityDto> delete(@RequestParam String id,
			HttpServletRequest request) {
		ApiResult<CityDto> api = new ApiResult<CityDto>();
		JedisUtils.delObject(id);
		return api;
	}

	@ApiOperation(value = "根据id获取城市信息", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "根据id获取城市信息")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<CityDto> getCityById(@RequestParam String id,
			HttpServletRequest request) {
		ApiResult<CityDto> api = new ApiResult<CityDto>();

		CityDto city = (CityDto) JedisUtils.getObject(id);
		api.setData(city);

		return api;
	}

	@ApiOperation(value = "根据查询获取城市列表", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "根据查询获取城市列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<CityDto>> getCityList(@RequestParam String words,
			HttpServletRequest request) {
		ApiResult<List<CityDto>> cityListRS = new ApiResult<List<CityDto>>();

		List<CityDto> cities = Lists.newArrayList();
		CityDto city = new CityDto();
		city.setName("北京");
		city.setId("2");
		cities.add(city);

		return cityListRS;
	}

	@ApiOperation(value = "根据查询获取城市列表", authorizations = {
			@Authorization(value = "token"),
			@Authorization(value = "timestamp") }, notes = "根据查询获取城市列表")
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	@ResponseBody
	public ApiResult<List<CityDto>> getCityListPage(@RequestParam String words,
			DefaultPageRQ body, HttpServletRequest request) {
		ApiResult<List<CityDto>> cityListRS = new ApiResult<List<CityDto>>();

		List<CityDto> cities = Lists.newArrayList();
		CityDto city = new CityDto();
		city.setName("北京");
		city.setId("2");
		cities.add(city);

		return cityListRS;
	}
}