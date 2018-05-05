/**
 * 
 */
package com.weimhc.framework.persistence;

/**
 * DAO支持类实现
 * 
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T>
		extends com.thinkgem.javamg.common.persistence.CrudDao<T> {

	/**
	 * 逻辑删除（更新del_flag字段为1，如果没有del_flag字段，则为真实删除 ）
	 * 
	 * @param entity
	 * @return
	 */
	public int deleteEntity(T entity);

	/**
	 * 批量逻辑删除,只支持使用id删除
	 */
	int deleteBatchEntity(String[] ids);

	/**
	 * 批量删除（真实删除）,只支持使用id删除
	 * 
	 * @param ids
	 */
	int deleteBatch(String[] ids);

}