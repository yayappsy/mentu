/**
 * 
 */
package com.weimhc.modules.user.utils;

import java.util.List;

import com.thinkgem.javamg.common.utils.Reflections;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.modules.user.dao.UserAuthDao;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 用户授权信息工具类，用于获取用户授权信息
 * 
 * @version 2013-12-05
 */
public class UserAuthUtils {

	private static UserAuthDao userAuthDao = SpringContextHolder

			.getBean(UserAuthDao.class);

	/**
	 * 根据userInfo 获取用户授权信息
	 * 
	 * @param userInfo
	 *            登录标识
	 * @param ifThirdPart
	 *            是否第三方登录
	 * @return 取不到返回null
	 */
	public static UserAuth get(UserInfo userInfo, boolean ifThirdPart) {
		UserAuth searchUserAuth = new UserAuth();
		searchUserAuth.setUserInfo(userInfo);
		searchUserAuth.setIfThirdPart(ifThirdPart);
		List<UserAuth> authList = userAuthDao.findAllList(searchUserAuth);
		if (authList != null && !authList.isEmpty()) {
			return authList.get(0);
		}
		return null;
	}

	/**
	 * 根据标识和标识类型 获取用户授权信息
	 * 
	 * @param identifier
	 *            登录标识
	 * @param identityType
	 *            登录方式
	 * @return 取不到返回null
	 */
	public static UserAuth get(String identifier, IdentityType identityType) {
		UserAuth searchUserAuth = new UserAuth();
		searchUserAuth.setIdentifier(identifier);
		searchUserAuth.setIdentityType(identityType);
		return userAuthDao.getEntity(searchUserAuth);
	}

	/**
	 * 根据标识和标识类型 获取用户授权信息
	 * 
	 * @param identifier
	 *            登录标识
	 * @param identityType
	 *            登录方式
	 * @return 取不到返回null
	 */
	public static List<UserAuth> getList(String identifier, IdentityType... identityTypes) {
		UserAuth searchUserAuth = new UserAuth();
		searchUserAuth.setIdentifier(identifier);
		searchUserAuth.getSqlMap().put("searchType", "identityTypes");
		if (identityTypes != null) {
			String[] searchStr = new String[identityTypes.length];
			for (int i = 0; i < searchStr.length; i++) {
				searchStr[i] = identityTypes[i].name();
			}
			searchUserAuth.getSqlMap().put("searchStr", searchStr);
		}
		return userAuthDao.findList(searchUserAuth);
	}

	/**
	 * 如果登录凭证存在就更新，不存在就创建
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public static void savePassword(UserInfo userInfo, IdentityType... identityTypes) {
		UserAuth searchUserAuth = null;
		for (IdentityType identityType : identityTypes) {
			searchUserAuth = new UserAuth(userInfo);
			searchUserAuth.setIdentityType(identityType);
			searchUserAuth = userAuthDao.getEntity(searchUserAuth);
			if (searchUserAuth == null) {
				insertUserAuth(userInfo, identityType);
			} else {
				saveUserInfo(userInfo, identityType);
			}
		}

	}

	/*
	 * 更新用户登录凭证,如果新密码为空则不更新
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public static void updatePassword(UserInfo userInfo, IdentityType... identityTypes) {
		UserAuth searchUserAuth = null;
		for (IdentityType identityType : identityTypes) {
			searchUserAuth = new UserAuth(userInfo);
			searchUserAuth.setIdentityType(identityType);
			searchUserAuth = userAuthDao.getEntity(searchUserAuth);
			if (searchUserAuth == null) {
				insertUserAuth(userInfo, identityType);
			} else {
				if (StringUtils.isNotBlank(userInfo.getNewPassword())) {
					saveUserInfo(userInfo, identityType);
				}
			}
		}
	}

	/**
	 * 增加用户登录凭证
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public static void insertPassword(UserInfo userInfo, IdentityType... identityTypes) {
		for (IdentityType identityType : identityTypes) {
			insertUserAuth(userInfo, identityType);
		}
	}

	private static void saveUserInfo(UserInfo userInfo, IdentityType identityType) {
		UserAuth userAuth = new UserAuth(userInfo);
		if (identityType.getIfThirdPart()) {
			userAuth.setIdentifier(userInfo.getNewPassword());
		} else {
			userAuth.setIdentifier(
					Reflections.getFieldValue(userInfo, identityType.name()).toString());
		}
		if (StringUtils.isBlank(userAuth.getIdentifier())) {
			return;
		}
		userAuth.setIfThirdPart(identityType.getIfThirdPart());
		userAuth.setIdentityType(identityType);
		userAuth.setCredential(PasswordUtils.entryptPassword(userInfo.getNewPassword()));
		userAuth.preUpdate();
		userAuthDao.updatePassword(userAuth);
	}

	private static void insertUserAuth(UserInfo userInfo, IdentityType identityType) {
		UserAuth userAuth = new UserAuth(userInfo);
		if (identityType.getIfThirdPart()) {
			userAuth.setIdentifier(userInfo.getNewPassword());
		} else {
			userAuth.setIdentifier(
					Reflections.getFieldValue(userInfo, identityType.name()).toString());
		}
		if (StringUtils.isBlank(userAuth.getIdentifier())) {
			return;
		}
		userAuth.setIdentityType(identityType);
		userAuth.setIfThirdPart(identityType.getIfThirdPart());
		userAuth.setCredential(PasswordUtils.entryptPassword(userInfo.getNewPassword()));
		userAuth.preInsert();
		userAuthDao.insertPassword(userAuth);
	}

}
