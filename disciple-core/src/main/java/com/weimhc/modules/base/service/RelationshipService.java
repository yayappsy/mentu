/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.RelationshipDao;
import com.weimhc.modules.base.entity.Relationship;

/**
 * 民族Service
 * 
 * @author lc
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class RelationshipService
		extends CrudServiceImpl<RelationshipDao, Relationship> {

	@Override
	public Relationship get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Relationship nation) {
		return super.count(nation);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Relationship> findList(Relationship nation) {
		return super.findList(nation);
	}

	@Override
	public Page<Relationship> findPage(Page<Relationship> page,
			Relationship nation) {
		return super.findPage(page, nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Relationship nation) {
		super.save(nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Relationship nation) {
		super.deleteEntity(nation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Relationship nation = null;
			for (String id : ids) {
				nation = new Relationship(id);
				deleteEntity(nation);
			}
		}
	}

}