package com.weimhc.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/***
 * request工具类 获取request的相关信息路径
 * <p>
 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c
 * </p>
 * 
 * @author szuo
 *
 */
public abstract class RequestUtils {

	private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	/***
	 * 获取域名 例如：
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * www.laozhaishaozuo.com
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getDomain(HttpServletRequest request) {
		return request.getServerName();
	}

	/***
	 * 获取带协议的域名 例如：http://www.laozhaishaozuo.com
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * http://www.laozhaishaozuo.com
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getHttpDomain(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName();
	}

	/***
	 * 获取带协议端口号的域名 例如：http://www.laozhaishaozuo.com:80
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * http://www.laozhaishaozuo.com:80
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getHttpDomainPort(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort();
	}

	/***
	 * 获取带协议的应用路径
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * http://www.laozhaishaozuo.com:80/myblog
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getContextHttpUri(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	/***
	 * 获取RealPath
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");

	}

	/**
	 * 获取服务器基本路径
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * myblog/authen/login.do?ab=c
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getQueryUrl(HttpServletRequest request) {
		return request.getContextPath() + request.getServletPath()
				+ getQueryString(request);
	}

	/***
	 * 获取queryString
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * ab=c
	 * </p>
	 * 
	 * @param request
	 * @return 如果request.getQueryString() 不为空,则 返回:"?" +
	 *         request.getQueryString()；否则返回:：""
	 */
	public static String getQueryString(HttpServletRequest request) {
		return StringUtils.isBlank(request.getQueryString()) ? ""
				: "?" + request.getQueryString();
	}

	/***
	 * 获取请求全路径
	 * <p>
	 * 例如：http://www.laozhaishaozuo.com:80/myblog/authen/login.do?ab=c中的
	 * 
	 * myblog/authen/login.do?ab=c
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String getFullRequestURL(HttpServletRequest request) {
		return request.getRequestURL().toString() + getQueryString(request);
	}

	/**
	 * 通过spring 获得request
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = RequestContextHolder
				.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes)
					.getRequest();
			return request;
		}
		return null;
	}

	/**
	 * 获取请求Body
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream,
					Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					;
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					;
				}
			}
		}
		return sb.toString();
	}

}
