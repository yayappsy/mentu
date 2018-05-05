/**
 * 
 */
package com.weimhc.modules.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.ThirdVideoCloudDao;
import com.weimhc.modules.base.entity.ThirdVideoCloud;
import com.weimhc.modules.base.utils.ThirdVideoCloudUtils;

/**
 * 视屏云
 * 
 * @author zsm
 * @version 2017-02-01
 */
@Service
@Transactional(readOnly = true)
public class ThirdVideoCloudService extends CrudServiceImpl<ThirdVideoCloudDao, ThirdVideoCloud> {

	@Override
	public void save(ThirdVideoCloud entity) {
		super.save(entity);
		ThirdVideoCloudUtils.clearCache();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ThirdVideoCloud entity = null;
			for (String id : ids) {
				entity = new ThirdVideoCloud(id);
				deleteEntity(entity);
			}
		}
	}

}