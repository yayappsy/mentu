/**
 * 
 */
package com.weimhc.modules.article.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.article.entity.ArticleCategory;

/**
 * 文章分类DAO接口
 * 
 * @author lc
 * @version 2016-11-22
 */
@MyBatisDao
public interface ArticleCategoryDao extends TreeDao<ArticleCategory> {

}