/**
 * 
 */
package com.thinkgem.javamg.common.service;

import java.util.List;

import com.thinkgem.javamg.common.persistence.CrudDao;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.persistence.Page;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
public interface CrudService<D extends CrudDao<T>, T extends DataEntity<T>>
		extends BaseService {

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id);

	/**
	 * 获取单条数据
	 * <p>
	 * 不推荐使用，改为使用 getEntity
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	public T get(T entity);

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T getEntity(T entity);

	/**
	 * 查询列表数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity);

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 真实删除数据
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 查询实体对象总数
	 * 
	 * @return 实体对象总数
	 */
	public long count(T entity);

	/**
	 * 判断实体对象是否存在
	 * 
	 * @param id
	 *            ID
	 * @return 实体对象是否存在
	 */
	boolean exists(String id);

}
