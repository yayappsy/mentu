/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.CollectStore;
import com.weimhc.modules.job.dao.CollectStoreDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 收藏表Service
 * @author cwl
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class CollectStoreService extends CrudServiceImpl<CollectStoreDao, CollectStore> {


    @Transactional(readOnly = false)
    public void deleteByOther(CollectStore collectStore) {
        dao.deleteByOther(collectStore);
    }

    public String getCollectJobIdsByUserId(String mumberId) {
        return dao.getCollectJobIdsByUserId(mumberId);
    }

    public String getCollectCompanyIdsByUserId(String mumberId) {
        return dao.getCollectCompanyIdsByUserId(mumberId);
    }
}