/**
 * 
 */
package com.weimhc.modules.suggestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.suggestion.dao.SuggestionDao;
import com.weimhc.modules.suggestion.entity.Suggestion;

/**
 * 建议Service
 * @author lc
 * @version 2016-12-01
 */
@Service
@Transactional(readOnly = true)
public class SuggestionService extends CrudServiceImpl<SuggestionDao, Suggestion> {

	public Suggestion get(String id) {
		return super.get(id);
	}

	public long count(Suggestion suggestion) {
		return super.count(suggestion);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<Suggestion> findList(Suggestion suggestion) {
		return super.findList(suggestion);
	}
	
	public Page<Suggestion> findPage(Page<Suggestion> page, Suggestion suggestion) {
		return super.findPage(page, suggestion);
	}
	
	@Transactional(readOnly = false)
	public void save(Suggestion suggestion) {
		super.save(suggestion);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(Suggestion suggestion) {
		super.deleteEntity(suggestion);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Suggestion suggestion = null;
			for (String id : ids) {
				suggestion = new Suggestion(id);
				deleteEntity(suggestion);
			}
		}
	}
	
}