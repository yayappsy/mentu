/**
 * 
 */
package com.weimhc.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.sys.dao.DictDao;
import com.weimhc.modules.sys.entity.Dict;
import com.weimhc.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * 
 * @author zsm
 * @version 2016-02-18
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudServiceImpl<DictDao, Dict> {

	/**
	 * 查询字段类型列表
	 * 
	 * @return
	 */
	public List<String> findTypeList() {
		return dao.findTypeList(new Dict());
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Dict dict) {
		super.deleteEntity(dict);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Dict dict = null;
			for (String id : ids) {
				dict = new Dict(id);
				deleteEntity(dict);
			}
		}
	}

}
