/**
 * 
 */
package com.weimhc.modules.suggestion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.suggestion.dao.SuggestionSubjectDao;
import com.weimhc.modules.suggestion.entity.SuggestionSubject;

/**
 * 建议主题Service
 * @author lc
 * @version 2016-12-01
 */
@Service
@Transactional(readOnly = true)
public class SuggestionSubjectService extends CrudServiceImpl<SuggestionSubjectDao, SuggestionSubject> {

	public SuggestionSubject get(String id) {
		return super.get(id);
	}

	public long count(SuggestionSubject suggestionSubject) {
		return super.count(suggestionSubject);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<SuggestionSubject> findList(SuggestionSubject suggestionSubject) {
		return super.findList(suggestionSubject);
	}
	
	public Page<SuggestionSubject> findPage(Page<SuggestionSubject> page, SuggestionSubject suggestionSubject) {
		return super.findPage(page, suggestionSubject);
	}
	
	@Transactional(readOnly = false)
	public void save(SuggestionSubject suggestionSubject) {
		super.save(suggestionSubject);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(SuggestionSubject suggestionSubject) {
		super.deleteEntity(suggestionSubject);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			SuggestionSubject suggestionSubject = null;
			for (String id : ids) {
				suggestionSubject = new SuggestionSubject(id);
				deleteEntity(suggestionSubject);
			}
		}
	}
	
}