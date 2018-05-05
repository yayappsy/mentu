/**
 * 
 */
package com.weimhc.modules.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.user.dao.UserAuthDao;
import com.weimhc.modules.user.entity.UserAuth;

/**
 * 用户授权信息Service
 * 
 * @author zsm
 * @version 2017-02-15
 */
@Service
@Transactional(readOnly = true)
public class UserAuthService extends CrudServiceImpl<UserAuthDao, UserAuth> {

	public boolean countIdentity(UserAuth userAuth) {
		return dao.countIdentity(userAuth);
	}
}