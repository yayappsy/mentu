package com.weimhc.framework.web.servlet.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.web.utils.MessageSourceUtils;

/***
 * API，SpringMVC拦截异常并返回Json
 * 
 * @author laozh
 *
 */
public class JsonExceptionHandler extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonExceptionHandler.class);

	/**
	 * app的api接口前缀
	 */
	private static final String apiPath = Global.getConfig("apiPath");

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub

		logger.error("异常", ex);
		String uri = request.getServletPath();
		logger.debug(uri);
		boolean isApiPath = uri.startsWith(apiPath);
		logger.debug("uri is api request:" + isApiPath);
		if (isApiPath) {
			// 输出错误Json
			ModelAndView mav = new ModelAndView();
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", -4);
			result.put("message", MessageSourceUtils
					.getMessage("error.common.serviceError"));
			result.put("data", "");
			view.setAttributesMap(result);
			mav.setView(view);
			return mav;
		}

		return super.doResolveException(request, response, handler, ex);
	}
}
