/**
 * 
 */
package com.weimhc.framework.service;

import java.util.List;

import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * @author szuo
 * @param <D>
 *
 */
public interface SortableService<D extends SortableDao<T>, T extends SortableEntity<T>>
		extends CrudService<D, T> {
	/**
	 * 更新排序
	 * 
	 * @param list
	 */
	public void updateSort(List<T> list);
}
