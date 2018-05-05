/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.SettingDao;
import com.weimhc.modules.base.entity.Setting;
import com.weimhc.modules.base.utils.setting.SettingUtils;

/**
 * 网站设置Service
 * 
 * @author zsm
 * @version 2016-01-13
 */
@Service
@Transactional(readOnly = true)
public class SettingService extends CrudServiceImpl<SettingDao, Setting> {

	@Autowired
	private SettingDao settingDao;

	@Override
	public Setting get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Setting setting) {
		return super.count(setting);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Setting> findList(Setting setting) {
		return super.findList(setting);
	}

	@Override
	public Page<Setting> findPage(Page<Setting> page, Setting setting) {
		return super.findPage(page, setting);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Setting setting) {
		CacheUtils.remove(SettingUtils.CACHE_SETTING_MAP);
		super.save(setting);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Setting setting) {
		CacheUtils.remove(SettingUtils.CACHE_SETTING_MAP);
		super.deleteEntity(setting);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		CacheUtils.remove(SettingUtils.CACHE_SETTING_MAP);
		if (ids != null) {
			Setting setting = null;
			for (String id : ids) {
				setting = new Setting(id);
				super.deleteEntity(setting);
			}
		}
	}

	/**
	 * 批量更新系统设置
	 * 
	 * @param settings
	 */
	public void saveSettings(Setting setting) {
		CacheUtils.remove(SettingUtils.CACHE_SETTING_MAP);
		settingDao.saveSettings(setting);
	}

}