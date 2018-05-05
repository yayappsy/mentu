/**
 * 
 */
package com.weimhc.modules.suggestion.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.suggestion.entity.SuggestionSubject;

/**
 * 建议主题DAO接口
 * @author lc
 * @version 2016-12-01
 */
@MyBatisDao
public interface SuggestionSubjectDao extends CrudDao<SuggestionSubject> {
	
}