/**
 * 
 */
package com.weimhc.framework.sms;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.weimhc.framework.sms.dto.req.DefaultSMSDto;

/**
 * 发送短信工具类
 *
 * @author laozh
 * @version 2017年4月27日
 */
public abstract class SMSSendUtils {
	/**
	 * 短信相关缓存
	 */
	public final static String SMS_CACHE = "SMSCache";

	/** 可发送的短信 */
	public final static String CACHE_SMS_SEND_LIST = "sms_send_list";

	/**
	 * 获取相关缓存
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
		Object obj = CacheUtils.get(SMS_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置短信相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(SMS_CACHE, key, value);
	}

	/**
	 * 获取需要发送的短信列表
	 * 
	 * @return
	 */
	public static List<DefaultSMSDto> getAll() {
		@SuppressWarnings("unchecked")
		List<DefaultSMSDto> list = (List<DefaultSMSDto>) getCache(CACHE_SMS_SEND_LIST);
		return list;
	}

	/**
	 * 设置需要发送的短信信息
	 * 
	 * @param destination
	 *            目标手机
	 * @param content
	 * @return
	 */
	public static void putSMSInfo(String destination, String content) {
		putSMSInfo(new DefaultSMSDto(destination, content, null));
	}

	/**
	 * 设置需要发送的短信信息
	 * 
	 * @param destination
	 *            目标手机
	 * @param content
	 * @param data
	 * @return
	 */
	public static void putSMSInfo(String destination, String content, Map<String, String> data) {
		putSMSInfo(new DefaultSMSDto(destination, content, data));
	}

	/**
	 * 设置需要发送的短信信息
	 * 
	 * @param destination
	 *            目标手机
	 * @param content
	 * @return
	 */
	public static void putSMSInfo(DefaultSMSDto smsDto) {
		@SuppressWarnings("unchecked")
		List<DefaultSMSDto> list = (List<DefaultSMSDto>) getCache(CACHE_SMS_SEND_LIST);
		if (list == null) {
			list = Lists.newLinkedList();
		}
		list.add(smsDto);
		putCache(CACHE_SMS_SEND_LIST, list);
	}

	/**
	 * 移除已发送成功的短信
	 * 
	 * @param index
	 *            序号
	 * 
	 * @return
	 */
	public static boolean removeSMSInfo(DefaultSMSDto defaultDto) {
		@SuppressWarnings("unchecked")
		List<DefaultSMSDto> list = (List<DefaultSMSDto>) getCache(CACHE_SMS_SEND_LIST);
		if (list == null || list.isEmpty()) {
			return true;
		} else {
			list.remove(defaultDto);
		}
		putCache(CACHE_SMS_SEND_LIST, list);
		return true;
	}

	/**
	 * 移除所有短信
	 * 
	 * @return
	 */
	public static boolean clearAll() {
		List<DefaultSMSDto> list = Lists.newLinkedList();
		putCache(CACHE_SMS_SEND_LIST, list);
		return true;
	}

	/**
	 * 获取一个需要发送的短信
	 * 
	 * @return
	 */
	public static DefaultSMSDto get() {
		@SuppressWarnings("unchecked")
		List<DefaultSMSDto> list = (List<DefaultSMSDto>) getCache(CACHE_SMS_SEND_LIST);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 移除最新一个已经发送过的短信
	 * 
	 * @return
	 */
	public static DefaultSMSDto remove() {
		@SuppressWarnings("unchecked")
		List<DefaultSMSDto> list = (List<DefaultSMSDto>) getCache(CACHE_SMS_SEND_LIST);
		if (list != null && list.size() > 0) {
			DefaultSMSDto removedSMSDto = list.remove(0);
			putCache(CACHE_SMS_SEND_LIST, list);
			return removedSMSDto;
		}
		return null;
	}

}
