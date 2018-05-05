/**
 * 
 */
package com.weimhc.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.framework.service.CrudService;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudServiceImpl<D extends CrudDao<T>, T extends DataEntity<T>>
		extends BaseServiceImpl implements CrudService<D, T> {
	/**
	 * 一次最大更新数量
	 */
	public static final int MAX_UPDATE_RECORDS = 1000;

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
	 * 通过一定的条件,获取单条数据
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
	 * 查询所有满足条件的列表数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity) {
		return dao.findAllList(entity);
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

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBatch(String... ids) {
		dao.deleteBatch(ids);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(T entity) {
		dao.deleteEntity(entity);
	}

	@Override
	@Transactional(readOnly = false)
	@Deprecated
	public void deleteEntity(String... ids) {
		if (ids != null) {
			dao.deleteBatchEntity(ids);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBatchEntity(String... ids) {
		if (ids != null) {
			dao.deleteBatchEntity(ids);
		}
	}
}
