/**
 * 
 */
package com.weimhc.modules.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.service.impl.BaseServiceImpl;
import com.weimhc.framework.im.easemob.EasemobIMUserManagerService;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.modules.base.entity.SnType;
import com.weimhc.modules.base.service.SnService;
import com.weimhc.modules.user.dao.UserAuthDao;
import com.weimhc.modules.user.dao.UserCorpInfoDao;
import com.weimhc.modules.user.dao.UserHealthInfoDao;
import com.weimhc.modules.user.dao.UserInfoDao;
import com.weimhc.modules.user.dao.UserLoginAuthorizationDao;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserCorpInfo;
import com.weimhc.modules.user.entity.UserHealthInfo;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.entity.UserLoginAuthorization;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 用户信息Service
 * 
 * @author lc
 * @version 2017-01-04
 */
@Service
@Transactional(readOnly = true)
public class UserInfoCommonService extends BaseServiceImpl {

	@Autowired
	protected UserInfoDao userInfoDao;

	@Autowired
	protected UserCorpInfoDao userCorpInfoDao;

	@Autowired
	protected UserHealthInfoDao userHealthInfoDao;

	@Autowired
	protected UserAuthDao userAuthDao;

	@Autowired
	protected UserLoginAuthorizationDao userLoginAuthorizationDao;

	@Autowired
	private SnService snService;

	@Autowired
	private EasemobIMUserManagerService easemobIMUserManagerService;

	/**
	 * 创建环信用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean createImUser(UserInfo userInfo) {
		easemobIMUserManagerService.createNewIMUserSingle(userInfo);
		userInfoDao.updateImUsername(userInfo);
		return true;
	}

	/**
	 * 维护用户信息
	 * 
	 * @param userInfo
	 * @param ifCreateUserAuth
	 *            是否创建用户登录凭证
	 * @param identityTypes
	 *            登录凭证类型
	 */
	@Transactional(readOnly = false)
	public void saveUserInfo(UserInfo userInfo, boolean ifCreateUserAuth,
			IdentityType... identityTypes) {
		if (userInfo.getIsNewRecord()) {
			userInfo.preInsert();
			userInfo.setSn(snService.generate(SnType.user, ""));
			userInfoDao.insert(userInfo);

			UserCorpInfo userCorpInfo = userInfo.getUserCorpInfo();
			userCorpInfo.setUserInfo(userInfo);
			userCorpInfo.preInsert();
			userCorpInfoDao.insert(userCorpInfo);

			UserHealthInfo userHealthInfo = userInfo.getUserHealthInfo();
			userHealthInfo.setUserInfo(userInfo);
			userHealthInfo.preInsert();
			userHealthInfoDao.insert(userHealthInfo);

			UserLoginAuthorization userLoginAuthorization = userInfo.getUserLoginAuthorization();
			userLoginAuthorization.setUserInfo(userInfo);
			userLoginAuthorization.preInsert();
			userLoginAuthorizationDao.insert(userLoginAuthorization);
		} else {
			userInfo.preUpdate();
			userInfoDao.update(userInfo);

			UserCorpInfo userCorpInfo = userInfo.getUserCorpInfo();
			userCorpInfo.setUserInfo(userInfo);
			userCorpInfo.preUpdate();
			userCorpInfoDao.update(userCorpInfo);

			UserHealthInfo userHealthInfo = userInfo.getUserHealthInfo();
			userHealthInfo.setUserInfo(userInfo);
			userHealthInfo.preUpdate();
			userHealthInfoDao.update(userHealthInfo);

			UserLoginAuthorization userLoginAuthorization = userInfo.getUserLoginAuthorization();
			userLoginAuthorization.setUserInfo(userInfo);
			userLoginAuthorization.preUpdate();
			userLoginAuthorizationDao.update(userInfo.getUserLoginAuthorization());
		}
		if (ifCreateUserAuth) {
			UserAuthUtils.insertPassword(userInfo, identityTypes);
		}
	}

	@Transactional(readOnly = false)
	public void updateUserBaseInfo(UserInfo userInfo) {
		saveUserInfo(userInfo, false);
	}

	/**
	 * 删除用户登录凭证，删除之后不能登录后台或前台
	 * 
	 * @param userInfo
	 */
	@Transactional(readOnly = false)
	public void deleteUserLoginAuthorization(UserInfo userInfo) {
		userLoginAuthorizationDao.deleteEntity(new UserLoginAuthorization(userInfo));
	}

	/**
	 * 删除用户登录凭证，删除之后不能登录后台或前台
	 * 
	 * @param userLoginAuthorization
	 */
	@Transactional(readOnly = false)
	public void deleteUserLoginAuthorization(UserLoginAuthorization userLoginAuthorization) {
		userLoginAuthorizationDao.deleteEntity(userLoginAuthorization);
	}

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public void updatePassword(UserInfo userInfo, IdentityType... identityTypes) {
		UserAuthUtils.updatePassword(userInfo, identityTypes);
	}

	@Transactional(readOnly = false)
	public void updateProfile(UserInfo userInfo) {
		userInfo.preUpdate();
		userInfoDao.updateProfile(userInfo);

		UserCorpInfo userCorpInfo = userInfo.getUserCorpInfo();
		userCorpInfo.setUserInfo(userInfo);
		if (userCorpInfoDao.count(userCorpInfo) == 0) {
			userCorpInfo.preInsert();
			userCorpInfoDao.insert(userCorpInfo);
		} else {
			userCorpInfo.preUpdate();
			userCorpInfoDao.update(userCorpInfo);
		}
	}

	/**
	 * 更新头像
	 * 
	 * @param userInfo
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateAvatar(UserInfo userInfo) {
		return userInfoDao.updateAvatarById(userInfo);
	}

	@Transactional(readOnly = false)
	public void updateUserLoginInfo(UserLoginAuthorization userLoginAuthorization) {
		// 保存上次登录信息
		userLoginAuthorization.setOldLoginIp(userLoginAuthorization.getLoginIp());
		userLoginAuthorization.setOldLoginDate(userLoginAuthorization.getLoginDate());
		// 更新本次登录信息
		userLoginAuthorization.setLoginIp(AccessUtils.getIpAddress());
		userLoginAuthorization.setLoginDate(new Date());
		userLoginAuthorizationDao.update(userLoginAuthorization);
	}
}