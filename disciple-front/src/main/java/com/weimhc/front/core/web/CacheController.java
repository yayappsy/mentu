/*
 * 
 * 
 * 
 */
package com.weimhc.front.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.EhCacheUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.web.BaseController;

import net.sf.ehcache.CacheManager;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Controller
@RequestMapping("${frontPath}/cache")
public class CacheController extends BaseController {

	/**
	 * 首页
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		String cacheName = request.getParameter("cacheName");
		if (StringUtils.isNotBlank(cacheName)) {
			CacheManager cacheManager = EhCacheUtils.getCacheManager();
			cacheManager.removeCache(cacheName);
		}
		return "redirect:" + Global.getFrontPath() + "/";
	}

	@RequestMapping("removeCache")
	public String removeCache(HttpServletRequest request, Model model) {
		CacheManager cacheManager = EhCacheUtils.getCacheManager();
		cacheManager.removeCache("productCache");
		cacheManager.removeCache("navigationCache");
		cacheManager.removeCache("adCache");
		cacheManager.removeCache("frontCategoryCache");
		cacheManager.removeCache("frontMarketCache");
		cacheManager.removeCache("articleCache");
		cacheManager.removeCache("sysCache");
		return "redirect:" + Global.getFrontPath() + "/";
	}

}