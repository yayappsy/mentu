/**
 * 
 */
package com.weimhc.modules.ad.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.ad.entity.Ad;

/**
 * 广告基本信息DAO接口
 * 
 * @author zsm
 * @version 2017-04-01
 */
@MyBatisDao
public interface AdDao extends SortableDao<Ad> {

	public List<Ad> findCarouselList(Ad ad);

	public List<Ad> findImageList(Ad ad);

	public List<Ad> findCenterImage(Ad ad);

}