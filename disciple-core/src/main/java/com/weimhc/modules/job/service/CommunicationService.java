/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.Communication;
import com.weimhc.modules.job.dao.CommunicationDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 收发信息Service
 * @author cwl
 * @version 2018-01-22
 */
@Service
@Transactional
public class CommunicationService extends CrudServiceImpl<CommunicationDao, Communication> {


    public void isRead(String sendId, String receiveId) {
        dao.isRead(sendId, receiveId);
    }
}