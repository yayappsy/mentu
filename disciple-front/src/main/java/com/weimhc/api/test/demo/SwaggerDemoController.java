package com.weimhc.api.test.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.utils.JedisUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.test.dto.req.TestRQ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@Api(value = "test-api")
@Controller
@RequestMapping("${apiPath}/test")
public class SwaggerDemoController
		extends ApiBaseController {

	@ApiOperation(value = "query api basic information", authorizations = {
			@Authorization(value = "api_key"),
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:pets", description = ""),
					@AuthorizationScope(scope = "read:pets", description = "") }) })
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> queryAPIInfo(
			@RequestBody TestRQ body,
			HttpServletRequest request) {

		String token = request.getHeader("token");
		String value = JedisUtils.get(token);

		if (StringUtils.isBlank(value)) {
			value = "shaozuo";
			JedisUtils.set(token, value, 60 * 60 * 7);
		}

		Map<String, String> map = new HashMap<>();
		map.put("language", "Java");
		map.put("format", "JSON");
		map.put("tools", "swagger");
		map.put("version", "1.0");
		return map;
	}

	@ApiOperation(value = "query data with parameters", authorizations = {
			@Authorization(value = "api_key"),
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:pets", description = ""),
					@AuthorizationScope(scope = "read:pets", description = "") }) })
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> queryData(
			@RequestParam String words,
			@RequestBody TestRQ body,
			HttpServletRequest request) {
		logger.info("查询更新新版本号");
		String token = request.getHeader("token");
		String value = JedisUtils.get(token);
		if (StringUtils.isNotBlank(words)
				&& StringUtils.equals(value, words)) {
			logger.debug("find keywords shaozuo");
		} else {
			logger.debug("not find");
		}
		Map<String, String> map = new HashMap<>();
		map.put("keyword", words);
		map.put("data", "this is demo data");
		return map;
	}
}