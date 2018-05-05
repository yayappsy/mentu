/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.SnDao;
import com.weimhc.modules.base.entity.Sn;
import com.weimhc.modules.base.entity.SnType;
import com.weimhc.modules.base.utils.SnUtils;

/**
 * 编号Service
 * 
 * @author zsm
 * @version 2016-02-01
 */
@Service
@Transactional(readOnly = true)
public class SnService extends CrudServiceImpl<SnDao, Sn> {

	@Autowired
	private SnDao snDao;

	@Override
	public Sn get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Sn sn) {
		return super.count(sn);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Sn> findList(Sn sn) {
		return super.findList(sn);
	}

	@Override
	public Page<Sn> findPage(Page<Sn> page, Sn sn) {
		return super.findPage(page, sn);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Sn sn) {
		super.save(sn);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Sn sn) {
		super.deleteEntity(sn);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Sn sn = null;
			for (String id : ids) {
				sn = new Sn(id);
				deleteEntity(sn);
			}
		}
	}

	/**
	 * 根据编号类型生成所需编号
	 * 
	 * @param type
	 * @param factor
	 *            影响因子
	 * @return
	 */
	@Transactional(readOnly = false)
	public String generate(SnType type, String factor) {
		Sn searchSn = new Sn(type);
		searchSn = snDao.getEntity(searchSn);
		String serialNumber = SnUtils.generateSn(searchSn);
		this.save(searchSn);
		return serialNumber;
	}

	/**
	 * 根据编号类型生成所需编号
	 * 
	 * @param type
	 * @return
	 */
	@Transactional(readOnly = false)
	public String generate(SnType type) {
		return generate(type, "");
	}
}