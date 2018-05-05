/**
 * 
 */
package com.weimhc.modules.suggestion.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.suggestion.entity.Suggestion;

/**
 * 建议DAO接口
 * @author lc
 * @version 2016-12-01
 */
@MyBatisDao
public interface SuggestionDao extends CrudDao<Suggestion> {
	
}