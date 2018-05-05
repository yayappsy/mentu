/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.WuyinDao;
import com.weimhc.modules.base.entity.Wuyin;

/**
 * 五音 Service
 * 
 * @author zsm
 * @version 2017-03-06
 */
@Service
@Transactional(readOnly = true)
public class WuyinService extends CrudServiceImpl<WuyinDao, Wuyin> {

	@Override
	public Wuyin get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Wuyin wuyin) {
		return super.count(wuyin);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Wuyin> findList(Wuyin wuyin) {
		return super.findList(wuyin);
	}

	@Override
	public Page<Wuyin> findPage(Page<Wuyin> page, Wuyin wuyin) {
		return super.findPage(page, wuyin);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Wuyin wuyin) {
		super.save(wuyin);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Wuyin wuyin) {
		super.deleteEntity(wuyin);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Wuyin wuyin = null;
			for (String id : ids) {
				wuyin = new Wuyin(id);
				deleteEntity(wuyin);
			}
		}
	}

}