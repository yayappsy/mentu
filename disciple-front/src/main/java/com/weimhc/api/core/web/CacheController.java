/*
 * 
 * 
 * 
 */
package com.weimhc.api.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.javamg.common.utils.EhCacheUtils;
import com.thinkgem.javamg.common.web.BaseController;
import com.weimhc.framework.dto.resp.ApiResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.ehcache.CacheManager;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Api
@Controller("apiCacheController")
@RequestMapping(value = "${apiPath}/cache")
public class CacheController extends BaseController {

	@ApiOperation(value = "清除缓存", notes = "清除缓存", tags = "清除缓存")
	@RequestMapping(value = "removeCache", method = RequestMethod.POST)
	public ApiResult<?> removeCache(HttpServletRequest request, Model model) {
		CacheManager cacheManager = EhCacheUtils.getCacheManager();
		cacheManager.removeCache("sysCache");
		ApiResult<?> result = new ApiResult<>();
		return result;
	}

}