/**
 * 
 */
package com.weimhc.modules.remind.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.remind.entity.Remind;

/**
 * 业务提醒DAO接口
 * 
 * @author zsm
 * @version 2017-03-23
 */
@MyBatisDao
public interface RemindDao extends CrudDao<Remind> {
	/**
	 * 删除可选参数
	 * 
	 * @param remind
	 * @return
	 */
	int deleteSelectableParameter(Remind remind);

	/**
	 * 插入可选参数
	 * 
	 * @param remind
	 * @return
	 */
	int insertSelectableParameter(Remind remind);

}