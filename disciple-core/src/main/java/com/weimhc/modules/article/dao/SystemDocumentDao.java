/**
 * 
 */
package com.weimhc.modules.article.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.article.entity.SystemDocument;

/**
 * 系统文章，主要用于注册协议或其他行为提示DAO接口
 * 
 * @author zsm
 * @version 2016-02-18
 */
@MyBatisDao
public interface SystemDocumentDao extends CrudDao<SystemDocument> {

}