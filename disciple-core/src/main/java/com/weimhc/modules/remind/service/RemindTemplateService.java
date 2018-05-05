/**
 * 
 */
package com.weimhc.modules.remind.service;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.remind.dao.RemindTemplateDao;
import com.weimhc.modules.remind.entity.RemindTemplate;
import com.weimhc.modules.remind.utils.RemindUtils;

/**
 * 提醒模板Service
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class RemindTemplateService
		extends CrudServiceImpl<RemindTemplateDao, RemindTemplate> {

	/* (non-Javadoc)
	 * @see com.weimhc.framework.service.impl.CrudServiceImpl#save(com.thinkgem.javamg.common.persistence.DataEntity)
	 */
	@Override
	public void save(RemindTemplate entity) {
		entity.setDisplayTitle(
				StringEscapeUtils.unescapeHtml4(entity.getDisplayTitle()));
		entity.setDisplayContent(
				StringEscapeUtils.unescapeHtml4(entity.getDisplayContent()));
		entity.setActualTitle(RemindUtils.transform2MustacheParameter(
				entity.getDisplayTitle(),
				RemindUtils.findRemindParameterAll()));
		entity.setActualContent(RemindUtils.transform2MustacheParameter(
				entity.getDisplayContent(),
				RemindUtils.findRemindParameterAll()));

		super.save(entity);
	}

	/**
	 * 更新提醒模板内容
	 * 
	 * @param remindTemplate
	 */
	@Transactional(readOnly = false)
	public void updateRemindContent(RemindTemplate remindTemplate) {
		dao.updateRemindContent(remindTemplate);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			RemindTemplate remindTemplate = null;
			for (String id : ids) {
				remindTemplate = new RemindTemplate(id);
				deleteEntity(remindTemplate);
			}
		}
	}

}