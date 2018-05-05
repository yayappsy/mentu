/**
 * 
 */
package com.weimhc.modules.user.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.user.entity.UserLoginAuthorization;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 平台用户登录授权表DAO接口
 * @author zsm
 * @version 2017-09-05
 */
@MyBatisDao
public interface UserLoginAuthorizationDao extends CrudDao<UserLoginAuthorization> {

}