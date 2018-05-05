/**
 * 
 */
package com.weimhc.modules.sys.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author zsm
 * @version 2016-02-18
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);

	public int updateParentIds(Menu menu);

	public int updateSort(Menu menu);

}