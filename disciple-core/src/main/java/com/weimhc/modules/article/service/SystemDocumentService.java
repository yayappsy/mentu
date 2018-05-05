/**
 * 
 */
package com.weimhc.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.article.dao.SystemDocumentDao;
import com.weimhc.modules.article.entity.SystemDocument;

/**
 * 系统文章，主要用于注册协议或其他行为提示Service
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Service
@Transactional(readOnly = true)
public class SystemDocumentService
		extends CrudServiceImpl<SystemDocumentDao, SystemDocument> {

	@Override
	public SystemDocument get(String id) {
		return super.get(id);
	}

	public long count(SystemDocument systemDocument) {
		return super.count(systemDocument);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	public List<SystemDocument> findList(SystemDocument systemDocument) {
		return super.findList(systemDocument);
	}

	public Page<SystemDocument> findPage(Page<SystemDocument> page,
			SystemDocument systemDocument) {
		return super.findPage(page, systemDocument);
	}

	@Transactional(readOnly = false)
	public void save(SystemDocument systemDocument) {
		super.save(systemDocument);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(SystemDocument systemDocument) {
		super.deleteEntity(systemDocument);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			SystemDocument systemDocument = null;
			for (String id : ids) {
				systemDocument = new SystemDocument(id);
				deleteEntity(systemDocument);
			}
		}
	}

}