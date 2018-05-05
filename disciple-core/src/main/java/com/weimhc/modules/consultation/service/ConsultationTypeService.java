/**
 * 
 */
package com.weimhc.modules.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.consultation.dao.ConsultationTypeDao;
import com.weimhc.modules.consultation.entity.ConsultationType;

/**
 * 咨询类型Service
 * @author zsm
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class ConsultationTypeService extends CrudServiceImpl<ConsultationTypeDao, ConsultationType> {

	public ConsultationType get(String id) {
		return super.get(id);
	}

	public long count(ConsultationType consultationType) {
		return super.count(consultationType);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<ConsultationType> findList(ConsultationType consultationType) {
		return super.findList(consultationType);
	}
	
	public Page<ConsultationType> findPage(Page<ConsultationType> page, ConsultationType consultationType) {
		return super.findPage(page, consultationType);
	}
	
	@Transactional(readOnly = false)
	public void save(ConsultationType consultationType) {
		super.save(consultationType);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(ConsultationType consultationType) {
		super.deleteEntity(consultationType);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ConsultationType consultationType = null;
			for (String id : ids) {
				consultationType = new ConsultationType(id);
				deleteEntity(consultationType);
			}
		}
	}
	
}