package com.weimhc.framework.utils;

import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.http.invoker.HttpClientRestAPIInvoker;

/**
 * 根据IP地址获取详细的地域信息
 * 
 * 
 */
public abstract class IpAddressInfoUtils {

	private static Logger logger = LoggerFactory.getLogger(IpAddressInfoUtils.class);

	public static final String IP_INFO_CACHE = "ipInfoCache";

	public static final String CACHE_IP_INFO_MAP = "ipInfo_map";

	/**
	 * 淘宝的ip信息地址接口
	 */
	private static final String TAOBAO_IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

	/**
	 * 新浪的ip信息地址接口
	 */
	private static final String SINA_IP_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

	/**
	 * 获取搜索引擎相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置搜索引擎相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 根据地址接口和ip获取IP信息字符串
	 *
	 * @param url
	 *            地址接口
	 * @param ip
	 *            IP地址
	 * 
	 * @return 信息字符串字符串，将其中的中文转码
	 */
	private static String getIpInfoStr(String url, String ip) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> ipInfoStrMap = (Map<String, String>) getCache(CACHE_IP_INFO_MAP);
		if (ipInfoStrMap == null) {
			ipInfoStrMap = Maps.newHashMap();
		}
		String remoteIpInfoStr = ipInfoStrMap.get(ip);
		if (StringUtils.isBlank(remoteIpInfoStr)) {
			String path = url + ip;
			try {
				remoteIpInfoStr = HttpClientRestAPIInvoker.doGet(path);
				remoteIpInfoStr = StringEscapeUtils.unescapeJava(remoteIpInfoStr);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			ipInfoStrMap.put(ip, remoteIpInfoStr);
			putCache(CACHE_IP_INFO_MAP, ipInfoStrMap);
		}

		return remoteIpInfoStr;
	}

	/**
	 * 通过新浪获取IP地址信息
	 */
	public static String getIpInfoBySina(String ip) {
		if (StringUtils.isBlank(ip)) {
			return null;
		}
		String remoteIpInfo = getIpInfoStr(SINA_IP_URL, ip);
		return remoteIpInfo;
	}

	/**
	 * 通过淘宝获取IP地址信息
	 */
	public static String getIpInfoByTaobao(String ip) {
		if (StringUtils.isBlank(ip)) {
			return null;
		}
		String remoteIpInfo = getIpInfoStr(TAOBAO_IP_URL, ip);
		return remoteIpInfo;
	}

	// 测试
	public static void main(String[] args) {
		// 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
		String ip = "125.70.11.136";
		String address = getIpInfoByTaobao(ip);
		System.out.println(address);
		// 输出结果为：广东省,广州市,越秀区
	}
}