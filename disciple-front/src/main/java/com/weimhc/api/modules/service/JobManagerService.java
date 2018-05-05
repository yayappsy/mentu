package com.weimhc.api.modules.service;

import com.weimhc.api.modules.dto.resp.cadet.JobDto;
import com.weimhc.modules.job.dao.JobDao;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/4 10:59
 */
@Service
public class JobManagerService {


    @Autowired
    private JobService jobService;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CompanyDetailService companyDetailService;

    /**
     * 获取职位
     *
     * @param jobId
     * @return
     */
    public JobDto getJobById(String jobId, String type) {
        Job job;
        if (type.equals("0")) {
            job = jobService.getPush(jobId);
        } else {
            job = jobService.get(jobId);
        }
        JobDto jobDto = companyDetailService.changeJobDto(job);
        return jobDto;
    }

    /**
     * 下线处理，这里把删除状态修改为一
     *
     * @param ids
     * @return
     */
    public Boolean lineOff(List<String> ids) {
        for (String id : ids) {
            jobDao.lineOff(id);
        }
        return true;
    }

    /**
     * 上线
     *
     * @param ids
     * @param days
     * @return
     */
    public Boolean lineOn(List<String> ids, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date time = calendar.getTime();
        for (String id : ids) {
            Job job = jobDao.get(id);
            if (job != null) {
                job.setState("1");
                job.setExpiryDate(time);
                job.setUpdateDate(new Date());
                jobDao.update(job);
            }
        }
        return true;
    }
}
