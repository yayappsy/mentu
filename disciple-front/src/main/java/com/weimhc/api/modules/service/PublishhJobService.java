package com.weimhc.api.modules.service;

import com.thinkgem.javamg.common.utils.IdGen;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.JobRQ;
import com.weimhc.api.modules.dto.resp.cadet.CompanyDto;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.job.dao.JobDao;
import com.weimhc.modules.job.dao.JobLabelDao;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.entity.JobLabel;
import com.weimhc.modules.job.service.JobService;
import com.weimhc.modules.sys.entity.Area;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布职位service
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/4 10:01
 */
@Service
public class PublishhJobService {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobLabelDao jobLabelDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CompanyService companyService;

    /**
     * 当前发布职位次数
     *
     * @param companyId
     * @return
     */
    public Integer getPublishCount(String companyId) {
        return jobService.getPublishCount(companyId);
    }

    /**
     * 发布职位
     *
     * @param jobRQ
     * @return
     */
    public String publishJob(JobRQ jobRQ) {
        Job job = new Job();
        BeanUtils.copyProperties(jobRQ, job);
        job.setState("1");
        job.setIsHit("0");
        job.setIsPush("1");
        job.setCompany(new Company(jobRQ.getCompanyId()));
        job.setIndustry(new Industry(jobRQ.getIndustryId()));
        job.setCity(new Area(jobRQ.getArea().getId()));
        job.setCompanyPushId(jobRQ.getCompanyPushId());

        jobService.save(job);
        String labels = jobRQ.getLabels();
        if (StringUtils.isEmpty(labels)) {
            return null;
        }
        jobDao.deleteJobLabel(job);
        String[] split = labels.split(" ");
        for (String s : split) {
            s = s.trim();
            JobLabel jobLabel = new JobLabel();
            jobLabel.setName(s);
            jobLabel.preInsert();
            jobLabelDao.insert(jobLabel);
            jobLabelDao.insertJobLabel(job.getId(), jobLabel.getId(), IdGen.uuidByIdWorker());
        }
        return job.getId();
    }

    public List<CompanyDto> searchCompanyByName(String name) {
        List<CompanyDto> companyDtos = new ArrayList<>();
        List<Company> companys = companyService.getByName(name);
        for (Company company : companys) {
            CompanyDto companyDto = new CompanyDto();
            BeanUtils.copyProperties(company, companyDto);
            companyDto.setIndustry(company.getIndustry());
            companyDto.setArea(company.getArea());
            companyDtos.add(companyDto);
        }
        return companyDtos;
    }
}
