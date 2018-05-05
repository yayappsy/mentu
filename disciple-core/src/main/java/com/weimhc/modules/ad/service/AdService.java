/**
 * 
 */
package com.weimhc.modules.ad.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.ad.dao.AdDao;
import com.weimhc.modules.ad.entity.Ad;
import com.weimhc.modules.ad.utils.AdUtils;

/**
 * 广告基本信息Service
 * 
 * @author zsm
 * @version 2017-04-01
 */
@Service
@Transactional(readOnly = true)
public class AdService extends SortableServiceImpl<AdDao, Ad> {

	@Override
	public Ad get(String id) {
		Ad ad = super.get(id);
		return ad;
	}

	/**
	 * 保存广告，并清除缓存
	 * 
	 */
	@Override
	public void save(Ad entity) {
		super.save(entity);
		AdUtils.removeAdCache();
	}

	@Override
	public void deleteEntity(Ad entity) {
		super.deleteEntity(entity);
		AdUtils.removeAdCache();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Ad ad = null;
			for (String id : ids) {
				ad = new Ad(id);
				deleteEntity(ad);
			}
		}
	}

	public List<Ad> findCarouselList(Ad ad) {
		return dao.findCarouselList(ad);
	}

	public List<Ad> findImageList(Ad ad) {
		return dao.findImageList(ad);

	}

	public List<Ad> findCenterImage(Ad ad) {
		return dao.findCenterImage(ad);
	}
}