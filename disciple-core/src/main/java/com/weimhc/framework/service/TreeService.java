/**
 * 
 */
package com.weimhc.framework.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.TreeEntity;
import com.weimhc.framework.persistence.TreeDao;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public interface TreeService<D extends TreeDao<T>, T extends TreeEntity<T>>
		extends CrudService<D, T> {

	/**
	 * 预留接口，用户更新子节前调用
	 * 
	 * @param childEntity
	 */
	public void preUpdateChild(T entity, T childEntity);

	/**
	 * 更新排序
	 * 
	 * @param list
	 */
	public void updateSort(List<T> list);

}
