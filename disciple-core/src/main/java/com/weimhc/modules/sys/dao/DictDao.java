/**
 * 
 */
package com.weimhc.modules.sys.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author zsm
 * @version 2016-02-18
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);

}