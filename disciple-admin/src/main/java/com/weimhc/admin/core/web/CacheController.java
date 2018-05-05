/*
 * 
 * 
 * 
 */
package com.weimhc.admin.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.javamg.common.utils.EhCacheUtils;
import com.thinkgem.javamg.common.web.BaseController;
import com.weimhc.modules.sys.utils.AreaUtils;

import net.sf.ehcache.CacheManager;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Controller
@RequestMapping("${adminPath}/cache")
public class CacheController extends BaseController {

	/**
	 * 首页
	 */
	@RequestMapping(value = { "index", "" })
	public String index(HttpServletRequest request, Model model) {

		CacheManager cacheManager = EhCacheUtils.getCacheManager();
		model.addAttribute("cacheNames", cacheManager.getCacheNames());

		return "/cache/cache";
	}

	@RequestMapping("removeCache")
	public String removeCache(HttpServletRequest request, Model model) {
		CacheManager cacheManager = EhCacheUtils.getCacheManager();
		cacheManager.removeCache(AreaUtils.AREA_CACHE);
		cacheManager.removeCache("sysCache");
		return "/cache/success";
	}

	@RequestMapping("removeCacheByName")
	public String removeCacheByName(@RequestParam String cacheName,
			HttpServletRequest request, Model model) {
		CacheManager cacheManager = EhCacheUtils.getCacheManager();
		cacheManager.removeCache(cacheName);
		return "/cache/success";
	}

}