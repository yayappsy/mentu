/**
 * 
 */
package com.weimhc.modules.gen.service;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.modules.gen.dao.GenTemplateDao;
import com.weimhc.modules.gen.entity.GenTemplate;

/**
 * 代码模板Service
 * 
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenTemplateService extends BaseServiceImpl {

	@Autowired
	private GenTemplateDao genTemplateDao;

	public GenTemplate get(String id) {
		return genTemplateDao.get(id);
	}

	public Page<GenTemplate> find(Page<GenTemplate> page,
			GenTemplate genTemplate) {
		genTemplate.setPage(page);
		page.setList(genTemplateDao.findList(genTemplate));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(GenTemplate genTemplate) {
		if (genTemplate.getContent() != null) {
			genTemplate.setContent(
					StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
		}
		if (StringUtils.isBlank(genTemplate.getId())) {
			genTemplate.preInsert();
			genTemplateDao.insert(genTemplate);
		} else {
			genTemplate.preUpdate();
			genTemplateDao.update(genTemplate);
		}
	}

	@Transactional(readOnly = false)
	public void delete(GenTemplate genTemplate) {
		genTemplateDao.delete(genTemplate.getId());
	}

}
