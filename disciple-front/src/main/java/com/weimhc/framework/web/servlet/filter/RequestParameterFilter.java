/**
 * 
 */
package com.weimhc.framework.web.servlet.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.web.servlet.http.BodyReaderHttpServletRequestWrapper;
import com.weimhc.framework.web.servlet.http.BodyReaderHttpServletResponseWrapper;

/**
 * api 输出请求参数
 * 
 * <p>
 * 当 enctype="multipart/form-data"时，不输出
 * </p>
 * <p>
 * multipart/form-data 不对字符编码。 在使用包含文件上传控件的表单时，必须使用该值。
 * </p>
 * 
 * @version 2014-8-19
 */
public class RequestParameterFilter implements Filter {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			if (!ServletFileUpload
					.isMultipartContent((HttpServletRequest) request)) {
				// 获取请求参数
				Map<String, String[]> parameterMap = request.getParameterMap();
				String requestString = JsonMapper.toJsonString(parameterMap);

				BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(
						(HttpServletRequest) request);
				logger.debug("请求参数: {} 请求的body:{}  URI: {}", requestString,
						requestWrapper.getBody(),
						requestWrapper.getRequestURI());
				if (requestWrapper != null) {
					request = requestWrapper;
				}
			}

		}
		if (logger.isDebugEnabled()) {
			BodyReaderHttpServletResponseWrapper myresponse = new BodyReaderHttpServletResponseWrapper(
					(HttpServletResponse) response);
			chain.doFilter(request, myresponse);
			byte out[] = myresponse.getBuffer();
			// 将返回数据重新写入Response中
			response.getOutputStream().write(out);
			// 拿出缓存中的数据
			logger.debug("返回类型: {}", response.getContentType());
			if (!StringUtils.contains(response.getContentType(), "image")) {
				logger.debug("返回数据: {}", new String(myresponse.getBuffer()));
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}

}
