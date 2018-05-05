/**
 * 
 */
package com.weimhc.modules.user.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.user.entity.UserAuth;

/**
 * 用户授权信息DAO接口
 * 
 * @author zsm
 * @version 2017-02-15
 */
@MyBatisDao
public interface UserAuthDao extends CrudDao<UserAuth> {

	/**
	 * 更新用户授权信息
	 * 
	 * @param userAuth
	 */
	int updatePassword(UserAuth userAuth);

	/**
	 * 新增用户授权信息
	 * 
	 * @param userAuth
	 */
	int insertPassword(UserAuth userAuth);

	boolean countIdentity(UserAuth userAuth);
}