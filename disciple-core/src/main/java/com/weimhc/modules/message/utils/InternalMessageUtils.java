/**
 * 
 */
package com.weimhc.modules.message.utils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.IdGen;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.message.dao.InternalMessageDao;
import com.weimhc.modules.message.dao.ReceivedMessageDao;
import com.weimhc.modules.message.entity.InternalMessage;
import com.weimhc.modules.message.entity.ReceivedMessage;

/**
 * 站内信工具类
 * 
 * @author laozh
 *
 */
public abstract class InternalMessageUtils {

	private static InternalMessageDao internalMessageDao = SpringContextHolder
			.getBean(InternalMessageDao.class);

	private static ReceivedMessageDao receivedMessageDao = SpringContextHolder
			.getBean(ReceivedMessageDao.class);

	/**
	 * 站内信相关缓存
	 */
	public final static String INTERNAL_MESSAGE_CACHE = "InternalMessageCache";

	/** 可发送的站内信 */
	public final static String CACHE_INTERNAL_MESSAGE_LIST = "internalMessage_send_list";

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
		Object obj = CacheUtils.get(INTERNAL_MESSAGE_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置站内信相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(INTERNAL_MESSAGE_CACHE, key, value);
	}

	/**
	 * 创建站内信 并发送
	 * 
	 * @param internalMessageDto
	 */
	public static void sendInternalMessage(InternalMessage internalMessage) {

		if (internalMessage.getIsNewRecord()) {
			internalMessage.setIsMass(false);
			internalMessage.preInsert();
			internalMessageDao.insert(internalMessage);
		}
		if (internalMessage.getRecipientIds() != null) {
			ReceivedMessage receivedMessage = new ReceivedMessage();
			receivedMessage.setMessage(internalMessage);
			for (String recipientId : internalMessage.getRecipientIds()) {
				receivedMessage.setRecipientId(recipientId);
				receivedMessage.setIsNewRecord(true);
				receivedMessage.setId(IdGen.uuidByIdWorker());
				receivedMessage.preInsert();
				receivedMessageDao.insert(receivedMessage);
			}
		}

	}

}
