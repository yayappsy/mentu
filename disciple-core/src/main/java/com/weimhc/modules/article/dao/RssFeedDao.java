/**
 * 
 */
package com.weimhc.modules.article.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.article.entity.RssFeed;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 信息源接口地址DAO接口
 * @author zsm
 * @version 2017-05-08
 */
@MyBatisDao
public interface RssFeedDao extends SortableDao<RssFeed> {

}