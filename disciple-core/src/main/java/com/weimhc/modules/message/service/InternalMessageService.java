/**
 * 
 */
package com.weimhc.modules.message.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.message.dao.InternalMessageDao;
import com.weimhc.modules.message.entity.InternalMessage;

/**
 * 站内信Service
 * @author zsm
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class InternalMessageService extends CrudServiceImpl<InternalMessageDao, InternalMessage> {

	public InternalMessage get(String id) {
		return super.get(id);
	}

	public long count(InternalMessage internalMessage) {
		return super.count(internalMessage);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<InternalMessage> findList(InternalMessage internalMessage) {
		return super.findList(internalMessage);
	}
	
	public Page<InternalMessage> findPage(Page<InternalMessage> page, InternalMessage internalMessage) {
		return super.findPage(page, internalMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(InternalMessage internalMessage) {
		super.save(internalMessage);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(InternalMessage internalMessage) {
		super.deleteEntity(internalMessage);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			InternalMessage internalMessage = null;
			for (String id : ids) {
				internalMessage = new InternalMessage(id);
				deleteEntity(internalMessage);
			}
		}
	}
	
}