/**
 * 
 */
package com.weimhc.modules.industry.service;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.industry.dao.IndustryDao;
import com.weimhc.modules.industry.entity.Industry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 行业分类Service
 * @author lc
 * @version 2017-11-13
 */
@Service
@Transactional(readOnly = true)
public class IndustryService extends TreeServiceImpl<IndustryDao, Industry> {

	@Override
	public Industry get(String id) {
		return super.get(id);
	}

	@Override
	public List<Industry> findList(Industry industry) {
		if (StringUtils.isNotBlank(industry.getParentIds())) {
			industry.setParentIds("," + industry.getParentIds() + ",");
		}
		return super.findList(industry);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Industry industry) {
		super.save(industry);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Industry industry) {
		super.deleteEntity(industry);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Industry industry = null;
			for (String id : ids) {
				industry = new Industry(id);
				super.deleteEntity(industry);
			}
		}
	}

	/**
	 * 批量更新，排序
	 * 
	 * @param list
	 */
	public void updateSort(List<Industry> list) {
		dao.updateSort(list);
	}

	/**
	 * 通过父级id查询所有的行业标签
	 * @param parentIds
	 * @return
	 */
	public List<Industry> findAllListByParentIds(String parentIds) {
		return dao.findAllListByParentIds(parentIds);
	}


	public List<Industry> findAllList(Industry industry) {
		return dao.findAllList(industry);
	}
	/**
	 * 通过父级id查询所有的行业标签的ids
	 * @param parentIds
	 * @return
	 */
	public String getIdsByParentIds(String parentIds) {
		return dao.getIdsByParentIds(parentIds);
	}
}