/**
 * 
 */
package com.weimhc.modules.user.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.user.entity.UserHealthInfo;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 用户健康信息DAO接口
 * @author zsm
 * @version 2017-09-07
 */
@MyBatisDao
public interface UserHealthInfoDao extends CrudDao<UserHealthInfo> {

}