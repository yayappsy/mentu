/**
 * 
 */
package com.weimhc.modules.ad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.ad.dao.AdPositionDao;
import com.weimhc.modules.ad.entity.AdPosition;

/**
 * 广告位Service
 * 
 * @author lc
 * @version 2016-06-07
 */
@Service
@Transactional(readOnly = true)
public class AdPositionService
		extends CrudServiceImpl<AdPositionDao, AdPosition> {

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			AdPosition adPosition = null;
			for (String id : ids) {
				adPosition = new AdPosition(id);
				deleteEntity(adPosition);
			}
		}
	}

}