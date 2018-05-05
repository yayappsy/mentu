/**
 * 
 */
package com.weimhc.modules.remind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.modules.remind.dao.RemindDao;
import com.weimhc.modules.remind.dao.RemindTemplateDao;
import com.weimhc.modules.remind.entity.Remind;
import com.weimhc.modules.remind.entity.RemindParameter;
import com.weimhc.modules.remind.entity.RemindTemplate;
import com.weimhc.modules.remind.utils.RemindUtils;

/**
 * 业务提醒Service
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class RemindService extends CrudServiceImpl<RemindDao, Remind> {

	@Autowired
	private RemindTemplateDao remindTemplateDao;

	@Override
	public Remind get(String id) {
		Remind remind = super.get(id);
		if (StringUtils.isNotBlank(remind.getId())) {
			remind.setRemindTemplateList(
					remindTemplateDao.findAllList(new RemindTemplate(remind)));
		}
		return remind;
	}

	/**
	 * 对提醒进行管理,不实际修改业务提醒模板
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(Remind remind) {
		super.save(remind);
		dao.deleteSelectableParameter(remind);
		if (remind.getRemindParameterList() != null) {
			for (RemindParameter entity : remind.getRemindParameterList()) {
				entity.preInsert();
			}
			dao.insertSelectableParameter(remind);
		}
		if (remind.getRemindTemplateList() != null) {
			for (RemindTemplate remindTemplate : remind
					.getRemindTemplateList()) {
				if (StringUtils.isBlank(remindTemplate.getId())
						&& remindTemplate.getRemindWay() != null) {
					remindTemplate.setIsNewRecord(true);
					remindTemplate.setId(
							remind.getId() + ConstantUtils.SEPARATOR_UNDERLINE
									+ remindTemplate.getRemindWay().getId());
					remindTemplate.setRemind(remind);
					remindTemplate.preInsert();
					remindTemplateDao.insert(remindTemplate);
				} else {
					if (RemindTemplate.DEL_FLAG_DELETE
							.equals(remindTemplate.getDelFlag())) {
						remindTemplateDao.delete(remindTemplate.getId());
					}
				}

			}
		}

	}

	/**
	 * 更新业务提醒信息
	 * 
	 * @param remind
	 */
	@Transactional(readOnly = false)
	public void saveIndex(Remind remind) {
		super.save(remind);
		if (remind.getRemindTemplateList() != null) {
			String title;
			String content;
			for (RemindTemplate remindTemplate : remind
					.getRemindTemplateList()) {
				title = RemindUtils.transform2MustacheParameter(
						remindTemplate.getDisplayTitle(), null);
				content = RemindUtils.transform2MustacheParameter(
						remindTemplate.getDisplayContent(), null);
				remindTemplate.setActualTitle(title);
				remindTemplate.setActualContent(content);
				remindTemplate.preUpdate();
				remindTemplateDao.update(remindTemplate);
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Remind remind = null;
			for (String id : ids) {
				remind = new Remind(id);
				deleteEntity(remind);
			}
		}
	}

}