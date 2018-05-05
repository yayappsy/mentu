/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Setting;

/**
 * 网站设置DAO接口
 * 
 * @author zsm
 * @version 2016-01-13
 */
@MyBatisDao
public interface SettingDao extends CrudDao<Setting> {

	/**
	 * 用于批量更改系统设置
	 * 
	 * @param settings
	 *            the settings to set
	 */
	void saveSettings(Setting setting);

}