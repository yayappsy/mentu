package com.weimhc.framework.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.taobao.TaobaoResult;
import com.weimhc.framework.taobao.resp.IpAddressInfo;

/**
 * 自定义访问对象工具类
 * 
 * 获取对象的IP地址等信息
 * 
 * @author szuo
 *
 */
public abstract class AccessUtils {

	private static Logger logger = LoggerFactory.getLogger(AccessUtils.class);

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		logger.debug(ip);
		return ip;
	}

	/**
	 * 获取用户真实IP地址 使用RequestContextHolder获取
	 * 
	 * 获取不到返回""
	 * 
	 * @return
	 */
	public static String getIpAddress() {
		RequestAttributes requestAttributes = RequestContextHolder
				.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes)
					.getRequest();
			return getIpAddress(request);
		}
		return "";
	}

	/**
	 * 获取ip信息
	 * 
	 * @param request
	 * @return
	 */
	public static IpAddressInfo getIpInfo(HttpServletRequest request) {
		return getIpInfo(getIpAddress(request));
	}

	/**
	 * 获取ip信息
	 * 
	 * @param ip
	 * @return
	 */
	public static IpAddressInfo getIpInfo(String ip) {
		TypeReference<TaobaoResult<IpAddressInfo>> typeRef = new TypeReference<TaobaoResult<IpAddressInfo>>() {
		};
		IpAddressInfo ipAddressInfo;
		String resultStr = IpAddressInfoUtils.getIpInfoByTaobao(ip);
		if (StringUtils.indexOf(resultStr, "country") >= 0) {
			TaobaoResult<IpAddressInfo> apiResult = JsonMapper
					.fromJsonString(resultStr, typeRef);
			ipAddressInfo = apiResult.getData();
		} else {
			ipAddressInfo = new IpAddressInfo();
			ipAddressInfo.setIp(ip);
		}
		return ipAddressInfo;
	}

	public static void main(String[] args) {
		IpAddressInfo ipAddressInfo = getIpInfo("124.200.148.61");
		System.out.println(ipAddressInfo.getArea());
	}

}
