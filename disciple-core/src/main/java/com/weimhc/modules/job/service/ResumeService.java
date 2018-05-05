/**
 * 
 */
package com.weimhc.modules.job.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.job.entity.Resume;
import com.weimhc.modules.job.dao.ResumeDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 简历Service
 * @author cwl
 * @version 2018-01-06
 */
@Service
@Transactional(readOnly = true)
public class ResumeService extends CrudServiceImpl<ResumeDao, Resume> {


    @Transactional(readOnly = false)
    public Integer getModuleNum(String resumeId) {
        Integer moduleNum = 0;
        List<String> moduleTableNames = new ArrayList<>();
        moduleTableNames.add("expect_practice");//期望实习
        moduleTableNames.add("education_background");//教育背景
        moduleTableNames.add("expect_practice");//实习经历
        moduleTableNames.add("learning_live");//学术经历
        moduleTableNames.add("school_live");//校园经历
        moduleTableNames.add("skill");//技能爱好
        moduleTableNames.add("opus_show");//作品展示
        moduleTableNames.add("access");//个人评价
        moduleTableNames.add("related_option");//相关附件
        for (String moduleTableName : moduleTableNames) {
            Integer num = dao.moduleIsExist(resumeId, moduleTableName);
            if (num == 0) {
                break;
            }
            moduleNum += 1;
        }
        return moduleNum;
    }

    @Transactional(readOnly = false)
    public Date getNewDate(String resumeId) {
        Resume resume = dao.get(resumeId);
        if (resume != null) {
            return resume.getUpdateDate();
        }
        return null;
    }

    @Transactional(readOnly = false)
    public void updateUpdateDate(String resumeId) {
        dao.updateUpdateDate(resumeId);
    }
}