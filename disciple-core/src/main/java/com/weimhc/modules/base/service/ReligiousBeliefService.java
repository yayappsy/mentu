/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.ReligiousBeliefDao;
import com.weimhc.modules.base.entity.ReligiousBelief;

/**
 * 宗教信仰Service
 * @author lc
 * @version 2017-01-05
 */
@Service
@Transactional(readOnly = true)
public class ReligiousBeliefService extends CrudServiceImpl<ReligiousBeliefDao, ReligiousBelief> {

	public ReligiousBelief get(String id) {
		return super.get(id);
	}

	public long count(ReligiousBelief religiousBelief) {
		return super.count(religiousBelief);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<ReligiousBelief> findList(ReligiousBelief religiousBelief) {
		return super.findList(religiousBelief);
	}
	
	public Page<ReligiousBelief> findPage(Page<ReligiousBelief> page, ReligiousBelief religiousBelief) {
		return super.findPage(page, religiousBelief);
	}
	
	@Transactional(readOnly = false)
	public void save(ReligiousBelief religiousBelief) {
		super.save(religiousBelief);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(ReligiousBelief religiousBelief) {
		super.deleteEntity(religiousBelief);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ReligiousBelief religiousBelief = null;
			for (String id : ids) {
				religiousBelief = new ReligiousBelief(id);
				deleteEntity(religiousBelief);
			}
		}
	}
	
}