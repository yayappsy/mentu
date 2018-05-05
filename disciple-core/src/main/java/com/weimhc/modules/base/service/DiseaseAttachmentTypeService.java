/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.DiseaseAttachmentTypeDao;
import com.weimhc.modules.base.entity.DiseaseAttachmentType;

/**
 * 民族Service
 * 
 * @author lc
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class DiseaseAttachmentTypeService extends
		CrudServiceImpl<DiseaseAttachmentTypeDao, DiseaseAttachmentType> {

	@Override
	public DiseaseAttachmentType get(String id) {
		return super.get(id);
	}

	@Override
	public long count(DiseaseAttachmentType nation) {
		return super.count(nation);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<DiseaseAttachmentType> findList(DiseaseAttachmentType nation) {
		return super.findList(nation);
	}

	@Override
	public Page<DiseaseAttachmentType> findPage(
			Page<DiseaseAttachmentType> page, DiseaseAttachmentType nation) {
		return super.findPage(page, nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(DiseaseAttachmentType nation) {
		super.save(nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(DiseaseAttachmentType nation) {
		super.deleteEntity(nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			DiseaseAttachmentType nation = null;
			for (String id : ids) {
				nation = new DiseaseAttachmentType(id);
				deleteEntity(nation);
			}
		}
	}

}