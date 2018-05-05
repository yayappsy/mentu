/**
 * 
 */
package com.weimhc.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.sys.dao.AreaDao;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.utils.AreaUtils;

/**
 * 地区Service
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeServiceImpl<AreaDao, Area> {

	public List<Area> findAll() {
		return AreaUtils.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		AreaUtils.removeCache(AreaUtils.CACHE_AREA_LIST);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Area area = null;
			for (String id : ids) {
				area = new Area(id);
				super.deleteEntity(area);
			}
		}
	}

	/**
	 * 获取热门城市
	 * @return
	 */
	public List<Area> getHitCity() {
		return dao.getHitCity();
	}


	/**
	 * 获取子区域
	 * @return
	 */
	public List<Area> getByParentId(String parentId) {
		return dao.getByParentId(parentId);
	}
}
