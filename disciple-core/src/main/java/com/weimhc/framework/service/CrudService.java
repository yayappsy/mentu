/**
 * 
 */
package com.weimhc.framework.service;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.framework.persistence.CrudDao;

/**
 * @author szuo
 * @param <D>
 *
 */
public interface CrudService<D extends CrudDao<T>, T extends DataEntity<T>>
		extends com.thinkgem.javamg.common.service.CrudService<D, T> {

	/**
	 * 批量删除（真实删除）,只支持使用id删除
	 * 
	 * @param ids
	 */
	public void deleteBatch(String... ids);

	/**
	 * 逻辑删除数据（一般为逻辑删除，更新del_flag字段为1,如果没有del_flag字段，则为真实删除）
	 * 
	 * @param id
	 */
	public void deleteEntity(T entity);

	/**
	 * 逻辑批量删除数据（一般为逻辑删除，更新del_flag字段为1,如果没有del_flag字段，则为真实删除）
	 * 
	 * @param id
	 */
	@Deprecated
	public void deleteEntity(String... ids);

	/**
	 * 逻辑批量删除数据（一般为逻辑删除，更新del_flag字段为1,如果没有del_flag字段，则为真实删除） ,只支持使用id删除
	 * 
	 * @param id
	 */
	public void deleteBatchEntity(String... ids);
}
