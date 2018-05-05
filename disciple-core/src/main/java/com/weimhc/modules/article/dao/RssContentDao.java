/**
 * 
 */
package com.weimhc.modules.article.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.article.entity.RssContent;

/**
 * 信息源内容DAO接口
 * 
 * @author zsm
 * @version 2017-05-08
 */
@MyBatisDao
public interface RssContentDao extends SortableDao<RssContent> {

	/**
	 * 更新获取数量
	 * 
	 * @return
	 */
	int updateFetchCount(RssContent rssContent);
}