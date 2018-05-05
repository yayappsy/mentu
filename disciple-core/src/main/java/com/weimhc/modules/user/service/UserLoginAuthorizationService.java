/**
 * 
 */
package com.weimhc.modules.user.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.user.entity.UserLoginAuthorization;
import com.weimhc.modules.user.dao.UserLoginAuthorizationDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 平台用户登录授权表Service
 * @author zsm
 * @version 2017-09-05
 */
@Service
@Transactional(readOnly = true)
public class UserLoginAuthorizationService extends CrudServiceImpl<UserLoginAuthorizationDao, UserLoginAuthorization> {

	
}