/**
 * 
 */
package com.weimhc.modules.navigation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.navigation.dao.NavigationDao;
import com.weimhc.modules.navigation.entity.Navigation;

/**
 * 导航栏Service
 * 
 * @author zsm
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class NavigationService
		extends TreeServiceImpl<NavigationDao, Navigation> {

	@Override
	public Navigation get(String id) {
		return super.get(id);
	}

	@Override
	public List<Navigation> findList(Navigation navigation) {
		if (StringUtils.isNotBlank(navigation.getParentIds())) {
			navigation.setParentIds("," + navigation.getParentIds() + ",");
		}
		return super.findList(navigation);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Navigation navigation) {
		super.save(navigation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Navigation navigation) {
		super.deleteEntity(navigation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Navigation navigation = null;
			for (String id : ids) {
				navigation = new Navigation(id);
				super.deleteEntity(navigation);
			}
		}
	}

	/**
	 * 批量更新，排序
	 * 
	 * @param list
	 */
	public void updateSort(List<Navigation> list) {
		dao.updateSort(list);
	}

}