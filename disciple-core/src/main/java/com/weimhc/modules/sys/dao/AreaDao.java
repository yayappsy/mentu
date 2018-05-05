/**
 * 
 */
package com.weimhc.modules.sys.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.sys.entity.Area;
import org.apache.ibatis.annotations.Param;

/**
 * 地区DAO接口
 * @author zsm
 * @version 2016-02-18
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	/**
	 * 通过上级地区获取地区列表。
	 * 
	 * @param area
	 * @return
	 */
	List<Area> findListByParent(Area area);

    List<Area> getHitCity();

	List<Area> getByParentId(@Param(value = "parentId") String parentId);
}