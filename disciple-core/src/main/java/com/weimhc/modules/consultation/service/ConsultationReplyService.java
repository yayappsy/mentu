/**
 * 
 */
package com.weimhc.modules.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.consultation.dao.ConsultationReplyDao;
import com.weimhc.modules.consultation.entity.ConsultationReply;

/**
 * 咨询回复Service
 * @author zsm
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class ConsultationReplyService extends CrudServiceImpl<ConsultationReplyDao, ConsultationReply> {

	public ConsultationReply get(String id) {
		return super.get(id);
	}

	public long count(ConsultationReply consultationReply) {
		return super.count(consultationReply);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<ConsultationReply> findList(ConsultationReply consultationReply) {
		return super.findList(consultationReply);
	}
	
	public Page<ConsultationReply> findPage(Page<ConsultationReply> page, ConsultationReply consultationReply) {
		return super.findPage(page, consultationReply);
	}
	
	@Transactional(readOnly = false)
	public void save(ConsultationReply consultationReply) {
		super.save(consultationReply);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(ConsultationReply consultationReply) {
		super.deleteEntity(consultationReply);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ConsultationReply consultationReply = null;
			for (String id : ids) {
				consultationReply = new ConsultationReply(id);
				deleteEntity(consultationReply);
			}
		}
	}
	
}