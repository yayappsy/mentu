/**
 * 
 */
package com.weimhc.modules.user.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.user.entity.UserHealthInfo;
import com.weimhc.modules.user.dao.UserHealthInfoDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 用户健康信息Service
 * @author zsm
 * @version 2017-09-07
 */
@Service
@Transactional(readOnly = true)
public class UserHealthInfoService extends CrudServiceImpl<UserHealthInfoDao, UserHealthInfo> {

	
}