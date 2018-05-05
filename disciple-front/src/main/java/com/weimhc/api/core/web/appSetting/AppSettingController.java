/**
 * 
 */
package com.weimhc.api.core.web.appSetting;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.JedisUtils;
import com.weimhc.api.core.dto.resp.AppSettingDto;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.UploadUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 时间戳 Controller
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Api
@Controller("apiAppSettingController")
@RequestMapping(value = "${apiPath}/appSetting")
public class AppSettingController extends ApiBaseController {

	@ApiOperation(value = "获取app设置", notes = "获取app设置", tags = { "app设置" })
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<AppSettingDto> list() {
		ApiResult<AppSettingDto> apiResult = new ApiResult<>();

		AppSettingDto appSettingDto = (AppSettingDto) CacheUtils
				.get("appSetting", "appSetting");
		if (appSettingDto == null) {
			appSettingDto = new AppSettingDto();
			appSettingDto.setImageUrlrefix(UploadUtils.BASE_URL);
			CacheUtils.put("appSetting", "appSetting", appSettingDto);

		}

		if (CacheUtils.getRedisIsEnable()) {
			Set<String> keysSet = JedisUtils.keys("captcha*");
			if (keysSet.size() > 0) {
				String[] keys = keysSet.toArray(new String[0]);
				JedisUtils.del(keys);
			}
		}
		apiResult.setData(appSettingDto);

		return apiResult;
	}

}
