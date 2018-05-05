/**
 * 
 */
package com.weimhc.modules.remind.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.remind.dao.RemindWayDao;
import com.weimhc.modules.remind.entity.RemindWay;

/**
 * 提醒方式Service
 * @author zsm
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class RemindWayService extends CrudServiceImpl<RemindWayDao, RemindWay> {

	public RemindWay get(String id) {
		return super.get(id);
	}

	public long count(RemindWay remindWay) {
		return super.count(remindWay);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<RemindWay> findList(RemindWay remindWay) {
		return super.findList(remindWay);
	}
	
	public Page<RemindWay> findPage(Page<RemindWay> page, RemindWay remindWay) {
		return super.findPage(page, remindWay);
	}
	
	@Transactional(readOnly = false)
	public void save(RemindWay remindWay) {
		super.save(remindWay);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(RemindWay remindWay) {
		super.deleteEntity(remindWay);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			RemindWay remindWay = null;
			for (String id : ids) {
				remindWay = new RemindWay(id);
				deleteEntity(remindWay);
			}
		}
	}
	
}