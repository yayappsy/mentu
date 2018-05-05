/**
 * 
 */
package com.weimhc.modules.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.MailConfigDao;
import com.weimhc.modules.base.entity.MailConfig;
import com.weimhc.modules.base.utils.MailConfigUtils;

/**
 * 邮件发送配置信息Service
 * 
 * @author zsm
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class MailConfigService
		extends CrudServiceImpl<MailConfigDao, MailConfig> {

	/**
	 * 保存实体，并清除缓存
	 * 
	 * @see com.weimhc.framework.service.impl.CrudServiceImpl#save(com.thinkgem.javamg.common.persistence.DataEntity)
	 */
	@Override
	public void save(MailConfig entity) {
		super.save(entity);
		MailConfigUtils.clearCache();
	}

}