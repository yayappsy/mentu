/**
 * 
 */
package com.weimhc.modules.sales.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.sales.entity.SalesNetworkType;
import com.weimhc.modules.sales.dao.SalesNetworkTypeDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 营业网点类型Service
 * @author zsm
 * @version 2017-04-28
 */
@Service
@Transactional(readOnly = true)
public class SalesNetworkTypeService extends SortableServiceImpl<SalesNetworkTypeDao, SalesNetworkType> {

	
}