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
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.dto.AbstractDto;
import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.PageRS;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api
@Controller
@RequestMapping("${apiPath}/demo")
public class DemoController extends ApiBaseController {

	@ApiOperation(value = "新增城市信息", notes = "新增城市信息", tags = {
			"城市信息" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<AbstractDto> add(@RequestBody AbstractRQ body,
			HttpServletRequest request) {
		ApiResult<AbstractDto> apiResult = new ApiResult<>();

		return apiResult;
	}

	@ApiOperation(value = "修改城市信息", notes = "修改城市信息", tags = {
			"城市信息" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<AbstractDto> update(@RequestBody AbstractRQ body,
			HttpServletRequest request) {
		ApiResult<AbstractDto> apiResult = new ApiResult<>();

		return apiResult;
	}

	@ApiOperation(value = "删除城市信息", notes = "删除城市信息", tags = {
			"城市信息" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<AbstractDto> delete(@RequestParam String id,
			HttpServletRequest request) {
		ApiResult<AbstractDto> apiResult = new ApiResult<>();
		return apiResult;
	}

	@ApiOperation(value = "根据id获取城市信息", tags = { "城市信息" }, authorizations = {
			@Authorization(value = "token") }, notes = "根据id获取城市信息")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<AbstractDto> getCityById(@RequestParam String id,
			HttpServletRequest request) {
		ApiResult<AbstractDto> apiResult = new ApiResult<>();

		return apiResult;
	}

	@ApiOperation(value = "根据查询获取城市列表", notes = "根据查询获取城市列表", tags = {
			"城市信息" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<AbstractDto>> list(HttpServletRequest request) {
		ApiResult<List<AbstractDto>> apiResult = new ApiResult<>();

		List<AbstractDto> lists = Lists.newArrayList();

		apiResult.setData(lists);
		return apiResult;
	}

	@ApiOperation(value = "分页获取城市列表", notes = "分页获取城市列表", tags = {
			"城市信息" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	@ResponseBody
	public ApiResult<PageRS<AbstractDto>> getListByPage(
			@RequestParam String words, DefaultPageRQ body,
			HttpServletRequest request) {
		ApiResult<PageRS<AbstractDto>> apiResult = new ApiResult<>();

		return apiResult;
	}
}