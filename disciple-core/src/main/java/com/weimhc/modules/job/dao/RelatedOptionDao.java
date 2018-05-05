/**
 * 
 */
package com.weimhc.modules.job.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.job.entity.RelatedOption;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 简历附件DAO接口
 * @author cwl
 * @version 2018-01-07
 */
@MyBatisDao
public interface RelatedOptionDao extends CrudDao<RelatedOption> {

}