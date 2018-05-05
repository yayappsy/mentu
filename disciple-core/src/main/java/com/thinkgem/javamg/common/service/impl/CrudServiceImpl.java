/**
 * 
 */
package com.thinkgem.javamg.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.CrudDao;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.service.CrudService;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudServiceImpl<D extends CrudDao<T>, T extends DataEntity<T>>
		extends BaseServiceImpl implements CrudService<D, T> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * <p>
	 * 不推荐使用，改为使用 getEntity
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	@Deprecated
	public T get(T entity) {
		return dao.get(entity);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public T getEntity(T entity) {
		return dao.getEntity(entity);
	}

	/**
	 * 查询列表数据
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 * @return
	 */
	@Override
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()) {
			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

	/**
	 * 真实删除数据
	 * 
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * 查询实体对象总数
	 * 
	 * @return 实体对象总数
	 */
	@Override
	public long count(T entity) {
		return dao.count(entity);
	}

	/**
	 * 判断实体对象是否存在
	 * 
	 * @param id
	 *            ID
	 * @return 实体对象是否存在
	 */
	@Override
	public boolean exists(String id) {
		return dao.exists(id);
	}

}
