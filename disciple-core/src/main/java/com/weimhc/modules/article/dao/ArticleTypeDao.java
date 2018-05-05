/**
 * 
 */
package com.weimhc.modules.article.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.article.entity.ArticleType;

/**
 * 文章类型DAO接口
 * 
 * @author lc
 * @version 2016-11-22
 */
@MyBatisDao
public interface ArticleTypeDao extends CrudDao<ArticleType> {

}