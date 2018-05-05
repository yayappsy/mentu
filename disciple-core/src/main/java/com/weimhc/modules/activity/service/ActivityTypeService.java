/**
 * 
 */
package com.weimhc.modules.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.activity.dao.ActivityTypeDao;
import com.weimhc.modules.activity.entity.ActivityType;

/**
 * 活动类型Service
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Service
@Transactional(readOnly = true)
public class ActivityTypeService
		extends SortableServiceImpl<ActivityTypeDao, ActivityType> {

	/**
	 * 保存数据（插入或更新） 插入数字
	 * 
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(ActivityType entity) {
		if (entity.getIsNewRecord()) {

			entity.getSqlMap().put("countType", "beforeInsert");
			Long counts = dao.count(entity);
			entity.setId(String.valueOf(counts + 1));
			entity.setSort(counts.intValue() + 1);
			entity.setIsNewRecord(true);

			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

}