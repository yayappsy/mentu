/**
 * 
 */
package com.weimhc.modules.company.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.company.entity.CompanyFile;
import com.weimhc.modules.company.dao.CompanyFileDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 企业文件Service
 * @author zsm
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class CompanyFileService extends SortableServiceImpl<CompanyFileDao, CompanyFile> {

	
}