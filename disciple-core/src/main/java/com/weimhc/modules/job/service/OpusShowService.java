/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.OpusShow;
import com.weimhc.modules.job.dao.OpusShowDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 作品展示Service
 * @author cwl
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class OpusShowService extends CrudServiceImpl<OpusShowDao, OpusShow> {

	
}