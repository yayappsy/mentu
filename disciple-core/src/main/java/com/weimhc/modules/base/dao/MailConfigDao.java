/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.base.entity.MailConfig;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 邮件发送配置信息DAO接口
 * @author zsm
 * @version 2017-06-14
 */
@MyBatisDao
public interface MailConfigDao extends CrudDao<MailConfig> {

}