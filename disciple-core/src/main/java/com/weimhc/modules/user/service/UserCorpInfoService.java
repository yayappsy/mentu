/**
 * 
 */
package com.weimhc.modules.user.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.user.entity.UserCorpInfo;
import com.weimhc.modules.user.dao.UserCorpInfoDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 用户公司信息Service
 * @author zsm
 * @version 2017-09-07
 */
@Service
@Transactional(readOnly = true)
public class UserCorpInfoService extends CrudServiceImpl<UserCorpInfoDao, UserCorpInfo> {

	
}