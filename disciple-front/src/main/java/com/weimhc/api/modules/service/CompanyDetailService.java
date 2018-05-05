package com.weimhc.api.modules.service;

import com.thinkgem.javamg.common.utils.IdGen;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.CompanyRQ;
import com.weimhc.api.modules.dto.resp.cadet.CompanyDto;
import com.weimhc.api.modules.dto.resp.cadet.JobDto;
import com.weimhc.api.modules.dto.resp.cadet.PageCompanyDto;
import com.weimhc.modules.company.dao.CompanyDao;
import com.weimhc.modules.company.dao.CompanyLabelDao;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.entity.CompanyLabel;
import com.weimhc.modules.company.service.CompanyLabelService;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.entity.JobIn;
import com.weimhc.modules.job.service.JobInService;
import com.weimhc.modules.job.service.JobService;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.user.entity.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/29 14:56
 */
@Service
public class CompanyDetailService {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyLabelDao companyLabelDao;
    @Autowired
    private CompanyLabelService companyLabelService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobInService jobInService;

    @Autowired
    private UserInfo2Service userInfo2Service;

    /**
     * 企业信息
     *
     * @param companyId
     * @return
     */
    public CompanyDto getCompanyInfo(String companyId) {
        CompanyDto companyDto = new CompanyDto();
        Company company = companyService.get(companyId);
        if (company != null) {
            BeanUtils.copyProperties(company, companyDto);
            companyDto.setIndustry(company.getIndustry());
            companyDto.setArea(company.getArea());
            return companyDto;
        }
        return null;
    }

    /**
     * 企业职位
     *
     * @param companyId
     * @param labelId
     * @param isHitJob
     * @return
     */
    public List<JobDto> getCompanyJobs(String companyId, String labelId, String isHitJob) {
        List<JobDto> jobDtos = new ArrayList<>();
        List<Job> jobs = jobService.getCompanyJobs(companyId, labelId, isHitJob);
        for (Job job : jobs) {
            JobDto jobDto = changeJobDto(job);
            jobDtos.add(jobDto);
        }
        return jobDtos;
    }

    /**
     * 转化为职位Dto
     *
     * @param job
     * @return
     */
    public JobDto changeJobDto(Job job) {
        JobDto jobDto = new JobDto();
        if (job == null) {
            return jobDto;
        }
        CompanyDto companyDto = new CompanyDto();
        Company company = companyDao.get(job.getCompany().getId());
        if (company != null) {
            BeanUtils.copyProperties(company, companyDto);
            companyDto.setArea(company.getArea());
        }
        BeanUtils.copyProperties(job, jobDto);
        jobDto.setDayNumber(job.getDayNumber() + "");
        jobDto.setJobLabel(job.getIndustry().getName());
        jobDto.setArea(job.getCity());
        jobDto.setEducation(job.getEducation());
        jobDto.setUpdateTime(job.getUpdateDate());
        jobDto.setIndustry(job.getIndustry());
        jobDto.setCompanyDto(companyDto);

        return jobDto;
    }

    /**
     * 注册时保存企业信息
     *
     * @param companyRQ
     * @return
     */
    public String saveCompany(CompanyRQ companyRQ) {
        Company company = changeCompany(companyRQ);
        company.preInsert();
        company.setIsPass("-1");
        companyDao.insert(company);
        updateCompanyLabel(companyRQ, company);
        return company.getId();
    }

    private Company changeCompany(CompanyRQ companyRQ) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRQ, company);
        company.setArea(new Area(companyRQ.getAreaId()));
        company.setUserInfo(new UserInfo(companyRQ.getUserInfoId()));
        company.setIndustry(new Industry(companyRQ.getIndustryId()));
        return company;
    }

    public Boolean updateCompany(CompanyRQ companyRQ) {
        Company company = companyDao.get(companyRQ.getId());
        if (company == null) {
            return false;
        }
        // 企业名称
        company.setName(companyRQ.getName());

        company.setLogo(companyRQ.getLogo());

        // 企业简称
        company.setShortName(companyRQ.getShortName());
        // 公司网址
        company.setSite(companyRQ.getSite());
        //联系电话
        company.setPhone(companyRQ.getPhone());
        //企业邮箱
        company.setEmail(companyRQ.getEmail());
        //区域
        company.setArea(new Area(companyRQ.getAreaId()));
        //行业id
        company.setIndustry(new Industry(companyRQ.getIndustryId()));
        // 公司规模
        company.setSize(companyRQ.getSize());
        // 企业简介
        company.setIntroduce(companyRQ.getIntroduce());

        company.setMainBusinessDescription(companyRQ.getMainBusinessDescription());

        companyDao.update(company);

        updateCompanyLabel(companyRQ, company);

        return true;
    }

    /**
     * 删除公司标签并添加新的公司标签
     *
     * @param companyRQ
     * @param company
     */
    private void updateCompanyLabel(CompanyRQ companyRQ, Company company) {
        companyDao.deleteCompanyLabel(company);
        if (StringUtils.isEmpty(companyRQ.getCompanyLabels())) {
            return;
        }
        String[] split = companyRQ.getCompanyLabels().split(" ");
        for (String s : split) {
            s = s.trim();
            CompanyLabel companyLabel = new CompanyLabel();
            companyLabel.preInsert();
            companyLabel.setName(s);
            companyLabelDao.insert(companyLabel);
            companyLabelDao.saveLabelList(company.getId(), companyLabel.getId(), IdGen.uuidByIdWorker());
        }
    }

    /**
     * -1等待审核，0不通过，1通过
     *
     * @param id
     * @return
     */
    public String isPass(String id) {
        String ispass = companyDao.isPass(id);
        return ispass;
    }

    /**
     * 通过当前登录id获取企业id
     *
     * @param id
     * @return
     */
    public String getCompanyId(String id) {
        return companyDao.getCompanyId(id);
    }

    public Boolean deliver(String companyId, String jobId, String resumeId, String userId) {
        jobService.addNumber(jobId);
        JobIn jobIn = new JobIn();
        jobIn.setCompanyId(companyId);
        jobIn.setResumeId(resumeId);
        jobIn.setUserId(userId);
        jobIn.setIsLook("0");
        jobIn.setStatus("0");
        jobIn.setJobId(jobId);
        jobInService.save(jobIn);
        return true;
    }

    public PageCompanyDto getPushCompanyList(Integer pageNo, Integer pageSize) {
        String ids = jobService.getPushIds();
        return userInfo2Service.getPageCompanyDto(pageNo, pageSize, ids);
    }

    public Boolean insertCompany(CompanyRQ companyRQ) {
        return null;
    }

    /**
     * 获取首页展示的企业信息
     *
     * @return
     */
    public List<CompanyDto> getCompanyInfoByHomeShow() {
        List<CompanyDto> companyDtos = new ArrayList<>();
        Company company = new Company();
        company.setHomeShow("1");
        List<Company> companyList = companyService.findList(company);
        for (Company temp : companyList) {
            CompanyDto companyDto = new CompanyDto();
            BeanUtils.copyProperties(temp, companyDto);
            companyDto.setIndustry(temp.getIndustry());
            companyDto.setArea(temp.getArea());
            companyDtos.add(companyDto);
        }
        return companyDtos;
    }
}

