/**
 * 
 */
package com.weimhc.modules.user.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.user.entity.UserCorpInfo;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 用户公司信息DAO接口
 * @author zsm
 * @version 2017-09-07
 */
@MyBatisDao
public interface UserCorpInfoDao extends CrudDao<UserCorpInfo> {

}