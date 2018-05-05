/**
 * 
 */
package com.weimhc.framework.persistence;

import java.util.List;

/**
 * 可排序的实体 DAO支持类实现
 * 
 * @version 2014-05-16
 * @param <T>
 */
public interface SortableDao<T> extends CrudDao<T> {

	/**
	 * 更新排序
	 * 
	 * @param entity
	 * @return
	 */
	public int updateSort(List<T> list);

}