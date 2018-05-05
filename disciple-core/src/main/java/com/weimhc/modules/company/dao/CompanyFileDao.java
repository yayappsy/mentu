/**
 * 
 */
package com.weimhc.modules.company.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.company.entity.CompanyFile;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 企业文件DAO接口
 * @author zsm
 * @version 2017-06-26
 */
@MyBatisDao
public interface CompanyFileDao extends SortableDao<CompanyFile> {

}