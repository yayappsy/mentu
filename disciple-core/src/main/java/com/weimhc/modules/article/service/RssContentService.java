/**
 * 
 */
package com.weimhc.modules.article.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.SortableServiceImpl;
import com.weimhc.modules.article.dao.RssContentDao;
import com.weimhc.modules.article.entity.RssContent;

/**
 * 信息源内容Service
 * 
 * @author zsm
 * @version 2017-05-08
 */
@Service
@Transactional(readOnly = true)
public class RssContentService
		extends SortableServiceImpl<RssContentDao, RssContent> {

}