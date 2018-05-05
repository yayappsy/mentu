package com.weimhc.api.modules.service;

import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Hr中心
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/19 10:50
 */
@Service
public class HrCenterService {


    @Autowired
    private JobService jobService;

    /**
     * 职位状态数量(1，在招 2，即将过期 3，已过期)
     *
     * @param companyId
     * @param status
     * @return
     */
    public Integer getJobNumByStatus(String companyId, String status) {
        Job job = new Job();
        if ("2".equals(status)) {
            //即将过期：为截止时间前一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            status = "1";
            job.setExpiryDate(time);
        }
        job.setCompany(new Company(companyId));
        job.setState(status);
        return jobService.getJobNum(job);
    }
}
