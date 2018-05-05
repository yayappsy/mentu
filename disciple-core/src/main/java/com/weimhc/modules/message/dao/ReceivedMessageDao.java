/**
 * 
 */
package com.weimhc.modules.message.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.message.entity.ReceivedMessage;

/**
 * 接收到的消息DAO接口
 * @author zsm
 * @version 2017-03-23
 */
@MyBatisDao
public interface ReceivedMessageDao extends CrudDao<ReceivedMessage> {
	
}