/**
 * 
 */
package com.weimhc.modules.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.user.dao.UserAuthDao;
import com.weimhc.modules.user.dao.UserInfoDao;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 用户信息Service
 * 
 * @author lc
 * @version 2017-01-04
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudServiceImpl<UserInfoDao, UserInfo> {

	@Autowired
	private UserAuthDao userAuthDao;

	/**
	 * 先删除登录凭证，再删除其他
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(UserInfo userInfo) {
		userAuthDao.deleteEntity(new UserAuth(userInfo));
		super.deleteEntity(userInfo);
	}

	/**
	 * 真实删除数据（先删除登录凭证，再删除其他）
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(UserInfo userInfo) {
		userAuthDao.delete(new UserAuth(userInfo));
		super.delete(userInfo);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			UserInfo userInfo = null;
			for (String id : ids) {
				userInfo = new UserInfo(id);
				deleteEntity(userInfo);
			}
		}
	}

	public UserInfo getByResume(String userId) {
		return dao.getByResume(userId);
	}

	public UserInfo getByUserId(String userInfoId) {
		return dao.getByUserId(new UserInfo(userInfoId));
	}
}