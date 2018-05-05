/**
 * 
 */
package com.weimhc.modules.article.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.article.entity.RssFeed;
import com.weimhc.modules.article.dao.RssFeedDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 信息源接口地址Service
 * @author zsm
 * @version 2017-05-08
 */
@Service
@Transactional(readOnly = true)
public class RssFeedService extends SortableServiceImpl<RssFeedDao, RssFeed> {

	
}