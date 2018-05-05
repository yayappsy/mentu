/**
 * 
 */
package com.thinkgem.javamg.common.persistence;

import java.util.List;

/**
 * DAO支持类实现
 * 
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

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
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 查询所有数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);

	/**
	 * 查询所有数据列表
	 * 
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 删除数据（真实删除）
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id);

	/**
	 * 删除数据（真实删除）
	 * 
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

	/**
	 * 判断实体对象是否存在
	 * 
	 * @param id
	 *            ID
	 * @return 实体对象是否存在
	 */
	boolean exists(String id);

	/**
	 * 查询实体对象总数
	 * 
	 * @return 实体对象总数
	 */
	public long count(T entity);

}