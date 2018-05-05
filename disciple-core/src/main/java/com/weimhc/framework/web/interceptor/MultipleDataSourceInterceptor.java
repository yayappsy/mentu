package com.weimhc.framework.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.spring.support.DataSourceKeyHolder;

public class MultipleDataSourceInterceptor extends AbstractInterceptor {

	private static final ThreadLocal<String> holderDataSourceKeyThreadLocal = new NamedThreadLocal<String>(
			"ThreadLocal HolderDataSourceKey");

	// 默认数据源标示
	private String defaultDataSourceKey;

	private String cookieName;

	/**
	 * Use the given name for cookies
	 */
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	/**
	 * Return the given name for cookies
	 */
	public String getCookieName() {
		return this.cookieName;
	}

	public String getDefaultDataSourceKey() {
		return defaultDataSourceKey;
	}

	public void setDefaultDataSourceKey(String defaultDataSourceKey) {
		this.defaultDataSourceKey = defaultDataSourceKey;
	}

	/**
	 * 判断是否需要改变数据源 需要改变的数据源
	 * 
	 * @param dataSourceKey
	 * @return
	 */
	private boolean checkIfChangingDataSourceKey(String holderDataSourceKey,
			String changingDataSourceKey) {
		logger.debug("数据源为:" + holderDataSourceKeyThreadLocal.get());
		// 首先判断是否修改过数据源,为空，则代表没有修改过
		if (StringUtils.isBlank(holderDataSourceKey)) {
			// 再判断传入的数据源标识是否与默认数据源相同，如果相同，不替换
			if (StringUtils.equals(changingDataSourceKey,
					getDefaultDataSourceKey())) {
				return false;
			}
		} else {
			// 如果修改过数据源，判断需要修改到数据源是否与现有的数据源相同，如果相同，不修改
			if (StringUtils.equals(holderDataSourceKey,
					changingDataSourceKey)) {
				return false;
			}
		}
		holderDataSourceKeyThreadLocal.set(changingDataSourceKey);
		return true;

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie cookie = WebUtils.getCookie(request, getCookieName());
		if (cookie != null) {
			String value = cookie.getValue();
			// 如果与默认数据源相同，则不替换。否则使用新的数据源标志。
			if (checkIfChangingDataSourceKey(
					DataSourceKeyHolder.getDataSourceKey(), value)) {
				// 避免重复修改数据源
				DataSourceKeyHolder.setDataSourceKey(value);// 线程绑定变量（该数据只有当前请求的线程可见）
				if (logger.isDebugEnabled()) {
					logger.debug("将数据源修改为:"
							+ DataSourceKeyHolder.getDataSourceKey());
				}

			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("使用的数据源是:" + (StringUtils
					.isBlank(DataSourceKeyHolder.getDataSourceKey())
							? getDefaultDataSourceKey()
							: DataSourceKeyHolder.getDataSourceKey()));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
