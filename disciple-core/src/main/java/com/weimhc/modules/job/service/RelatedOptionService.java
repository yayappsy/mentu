/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.RelatedOption;
import com.weimhc.modules.job.dao.RelatedOptionDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * 简历附件Service
 * @author cwl
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class RelatedOptionService extends CrudServiceImpl<RelatedOptionDao, RelatedOption> {


    public Integer getCountByResume(String resumeId) {
        RelatedOption relatedOption = new RelatedOption();
        relatedOption.setType("1");
        relatedOption.setResumeId(resumeId);
        List<RelatedOption> list = dao.findList(relatedOption);
        return list.size();
    }
}