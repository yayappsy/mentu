/**
 * 
 */
package com.weimhc.modules.inquiry.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.inquiry.dao.InquiryProductDao;
import com.weimhc.modules.inquiry.entity.InquiryProduct;

/**
 * 询价产品Service
 * 
 * @author zsm
 * @version 2017-04-10
 */
@Service
@Transactional(readOnly = true)
public class InquiryProductService
		extends CrudServiceImpl<InquiryProductDao, InquiryProduct> {

}