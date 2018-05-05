/**
 * 
 */
package com.weimhc.modules.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.sales.entity.SalesNetwork;
import com.weimhc.modules.sales.dao.SalesNetworkDao;

/**
 * 营业网点Service
 * @author zsm
 * @version 2017-04-28
 */
@Service
@Transactional(readOnly = true)
public class SalesNetworkService extends TreeServiceImpl<SalesNetworkDao, SalesNetwork> {

	
	public List<SalesNetwork> findList(SalesNetwork salesNetwork) {
		if (StringUtils.isNotBlank(salesNetwork.getParentIds())){
			salesNetwork.setParentIds(","+salesNetwork.getParentIds()+",");
		}
		return super.findList(salesNetwork);
	}
		
	
}