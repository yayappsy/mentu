/**
 * 
 */
package com.weimhc.modules.product.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.product.entity.ProductExtra;
import com.weimhc.modules.product.dao.ProductExtraDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 商品附加信息Service
 * @author lc
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class ProductExtraService extends CrudServiceImpl<ProductExtraDao, ProductExtra> {

	
}