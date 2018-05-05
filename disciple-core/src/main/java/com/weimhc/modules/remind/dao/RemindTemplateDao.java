/**
 * 
 */
package com.weimhc.modules.remind.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.remind.entity.RemindTemplate;

/**
 * 提醒模板DAO接口
 * 
 * @author zsm
 * @version 2017-03-23
 */
@MyBatisDao
public interface RemindTemplateDao extends CrudDao<RemindTemplate> {

	/**
	 * 编辑模板内容
	 * 
	 * @param remindTemplate
	 * @return
	 */
	int updateRemindContent(RemindTemplate remindTemplate);

}