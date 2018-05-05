/**
 * 
 */
package com.weimhc.modules.industry.dao;

import com.thinkgem.javamg.common.persistence.CrudDao;
import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.industry.entity.Industry;

import java.util.List;

/**
 * 行业分类DAO接口
 * @author lc
 * @version 2017-11-13
 */
@MyBatisDao
public interface IndustryDao extends TreeDao<Industry> {
    List<Industry> findAllListByParentIds(String parentIds);

    String getIdsByParentIds(String parentIds);

    List<Industry> findAllList(Industry industry);
}