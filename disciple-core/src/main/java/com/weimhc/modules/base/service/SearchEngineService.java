/**
 * 
 */
package com.weimhc.modules.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.SearchEngineDao;
import com.weimhc.modules.base.entity.SearchEngine;

/**
 * 搜索引擎DAO接口
 * 
 * @author zsm
 * @version 2017-04-11
 */
@Service
@Transactional(readOnly = true)
public class SearchEngineService
		extends CrudServiceImpl<SearchEngineDao, SearchEngine> {

}