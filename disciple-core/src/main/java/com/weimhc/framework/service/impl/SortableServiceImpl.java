/**
 * 
 */
package com.weimhc.framework.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.framework.persistence.SortableEntity;
import com.weimhc.framework.service.SortableService;
import com.weimhc.framework.utils.PageUtils;

/**
 * 排序实现
 *
 * @author laozh
 * @version 2017年3月31日
 */
public abstract class SortableServiceImpl<D extends SortableDao<T>, T extends SortableEntity<T>>
		extends CrudServiceImpl<D, T> implements SortableService<D, T> {

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
