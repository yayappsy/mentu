/**
 * 
 */
package com.weimhc.framework.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.TreeEntity;
import com.thinkgem.javamg.common.service.ServiceException;
import com.thinkgem.javamg.common.utils.Reflections;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.framework.service.TreeService;
import com.weimhc.framework.utils.PageUtils;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class TreeServiceImpl<D extends TreeDao<T>, T extends TreeEntity<T>>
		extends CrudServiceImpl<D, T> implements TreeService<D, T> {

	/**
	 * 更新节点前调用
	 */
	public void preUpdate(T entity) {

	}

	@Override
	@Transactional(readOnly = false)
	public void save(T entity) {

		@SuppressWarnings("unchecked")
		Class<T> entityClass = Reflections.getClassGenricType(getClass(), 1);

		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null
				|| StringUtils.isBlank(entity.getParentId())
				|| "0".equals(entity.getParentId())) {
			entity.setParent(null);
		} else {
			entity.setParent(super.get(entity.getParentId()));
		}
		if (entity.getParent() == null) {
			T parentEntity = null;
			try {
				parentEntity = entityClass.getConstructor(String.class)
						.newInstance("0");
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			entity.setParent(parentEntity);
			entity.getParent().setParentIds(StringUtils.EMPTY);
			entity.getParent().setName(StringUtils.EMPTY);
			entity.getParent().setParentNames(StringUtils.EMPTY);
		}

		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = entity.getParentIds();
		String oldParentNames = entity.getParentNames() + entity.getOldName();

		// 设置新的父节点串
		entity.setParentIds(entity.getParent().getParentIds()
				+ entity.getParent().getId() + ",");

		// 设置新的父节点名称串
		entity.setParentNames(entity.getParent().getParentNames()
				+ entity.getParent().getName() + ",");

		entity.setGrade(StringUtils.split(entity.getParentIds(), ",").length);

		// 不能使用与父节点串相同的逻辑，因为在修改过程中，父节点（id）是不变的，
		// 而现在父节点名称是有变动的,现在的问题是怎么获得之前的变动。
		String newParentNames = entity.getParentNames() + entity.getName();

		// 更新前操作
		preUpdate(entity);

		// 保存或更新实体
		super.save(entity);

		// 更新子节点 parentIds
		T o = null;
		try {
			o = entityClass.newInstance();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		o.setParentIds("%," + entity.getId() + ",%");
		List<T> list = dao.findByParentIdsLike(o);
		for (T e : list) {
			if (e.getParentIds() != null && oldParentIds != null) {
				e.setParentIds(e.getParentIds().replace(oldParentIds,
						entity.getParentIds()));
				// 更新父名称
				if (e.getParentNames() != null && oldParentNames != null) {
					e.setParentNames(e.getParentNames().replace(oldParentNames,
							newParentNames));
				}
				e.setGrade(StringUtils.split(e.getParentIds(), ",").length);
				preUpdateChild(entity, e);
				dao.updateParentIds(e);
			}
		}

	}

	/**
	 * 预留接口，用户更新子节前调用
	 * 
	 * @param childEntity
	 */
	@Override
	public void preUpdateChild(T entity, T childEntity) {

	}

	@Override
	@Transactional(readOnly = false)
	public void updateSort(List<T> list) {
		if (list.size() <= MAX_UPDATE_RECORDS) {
			dao.updateSort(list);
		} else {
			int maxLength = list.size();
			int times = PageUtils.getPageCount(maxLength, MAX_UPDATE_RECORDS);
			int fromIndex = 0;
			int toIndex = 0;
			for (int i = 0; i < times; i++) {
				fromIndex = i * MAX_UPDATE_RECORDS;
				toIndex = (i + 1) * MAX_UPDATE_RECORDS;
				if (i == times - 1) {
					toIndex = maxLength;
				}
				dao.updateSort(list.subList(fromIndex, toIndex));
			}
		}

	}
}
