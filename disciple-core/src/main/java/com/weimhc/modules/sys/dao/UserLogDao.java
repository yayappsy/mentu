/**
 * 
 */
package com.weimhc.modules.sys.dao;

import com.thinkgem.javamg.common.persistence.CrudDao;
import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.sys.entity.UserLog;

/**
 * 日志DAO接口
 * 
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserLogDao extends CrudDao<UserLog> {

}
