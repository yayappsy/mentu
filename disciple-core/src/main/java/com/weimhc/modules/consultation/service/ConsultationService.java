/**
 * 
 */
package com.weimhc.modules.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.consultation.dao.ConsultationDao;
import com.weimhc.modules.consultation.entity.Consultation;

/**
 * 咨询Service
 * @author zsm
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class ConsultationService extends CrudServiceImpl<ConsultationDao, Consultation> {

	public Consultation get(String id) {
		return super.get(id);
	}

	public long count(Consultation consultation) {
		return super.count(consultation);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Consultation> findList(Consultation consultation) {
		return super.findList(consultation);
	}
	
	public Page<Consultation> findPage(Page<Consultation> page, Consultation consultation) {
		return super.findPage(page, consultation);
	}
	
	@Transactional(readOnly = false)
	public void save(Consultation consultation) {
		super.save(consultation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Consultation consultation) {
		super.deleteEntity(consultation);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Consultation consultation = null;
			for (String id : ids) {
				consultation = new Consultation(id);
				deleteEntity(consultation);
			}
		}
	}
	
}