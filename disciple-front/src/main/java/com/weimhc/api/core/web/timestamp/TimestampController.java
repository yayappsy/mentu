/**
 * 
 */
package com.weimhc.api.core.web.timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimhc.api.core.dto.resp.TimestampDto;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.dto.resp.ApiResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 时间戳 Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Api
@Controller("apiTimestampController")
@RequestMapping(value = "${apiPath}/timestamp")
public class TimestampController extends ApiBaseController {

	@ApiOperation(value = "获取服务器时间戳", notes = "获取服务器时间戳", tags = { "url校验" })
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<TimestampDto> list() {
		ApiResult<TimestampDto> apiResult = new ApiResult<>();
		apiResult.setData(new TimestampDto());
		return apiResult;
	}

}
