/**
 * 
 */
package com.weimhc.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.message.dao.ReceivedMessageDao;
import com.weimhc.modules.message.entity.ReceivedMessage;

/**
 * 接收到的消息Service
 * @author zsm
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class ReceivedMessageService extends CrudServiceImpl<ReceivedMessageDao, ReceivedMessage> {

	public ReceivedMessage get(String id) {
		return super.get(id);
	}

	public long count(ReceivedMessage receivedMessage) {
		return super.count(receivedMessage);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<ReceivedMessage> findList(ReceivedMessage receivedMessage) {
		return super.findList(receivedMessage);
	}
	
	public Page<ReceivedMessage> findPage(Page<ReceivedMessage> page, ReceivedMessage receivedMessage) {
		return super.findPage(page, receivedMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(ReceivedMessage receivedMessage) {
		super.save(receivedMessage);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(ReceivedMessage receivedMessage) {
		super.deleteEntity(receivedMessage);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ReceivedMessage receivedMessage = null;
			for (String id : ids) {
				receivedMessage = new ReceivedMessage(id);
				deleteEntity(receivedMessage);
			}
		}
	}
	
}