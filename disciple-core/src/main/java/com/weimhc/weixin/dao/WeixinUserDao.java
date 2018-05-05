/**
 * 
 */
package com.weimhc.weixin.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.weixin.entity.WeixinUser;

/**
 * 微信用户DAO接口
 * 
 * @author zsm
 * @version 2016-07-06
 */
@MyBatisDao
public interface WeixinUserDao extends CrudDao<WeixinUser> {

}