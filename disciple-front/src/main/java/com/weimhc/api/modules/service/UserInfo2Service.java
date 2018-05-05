package com.weimhc.api.modules.service;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.resp.cadet.*;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.job.entity.CollectStore;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.entity.JobIn;
import com.weimhc.modules.job.service.CollectStoreService;
import com.weimhc.modules.job.service.JobInService;
import com.weimhc.modules.job.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户中心
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/7 20:56
 */
@Service
public class UserInfo2Service {


    @Autowired
    private CollectStoreService collectStoreService;

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CreateResumeService createResumeService;

    @Autowired
    private JobInService jobInService;

    @Autowired
    private CompanyDetailService companyDetailService;

    /**
     * 添加收藏
     *
     * @param numberId
     * @param jobId
     * @param type     1职业 2公司
     * @return
     */
    public Boolean collect(String numberId, String jobId, String type) {
        CollectStore collectStore = new CollectStore();
        collectStore.setCategory(type);
        collectStore.setUserId(numberId);
        collectStore.setCollectorId(jobId);
        collectStoreService.save(collectStore);
        return true;
    }

    /**
     * 取消收藏
     *
     * @param numberId
     * @param jobId
     * @param type     1职业 2公司
     * @return
     */
    public Boolean cancelCollect(String numberId, String jobId, String type) {
        CollectStore collectStore = new CollectStore();
        collectStore.setCategory(type);
        collectStore.setUserId(numberId);
        collectStore.setCollectorId(jobId);
        collectStoreService.deleteByOther(collectStore);
        return true;
    }

    /**
     * 我的收藏-职位
     *
     * @param mumberId
     * @return
     */
    public PageJobDto myCollectJob(String mumberId, Integer pageNo, Integer pageSize) {
        String ids = collectStoreService.getCollectJobIdsByUserId(mumberId);

        PageJobDto pageJobDto = new PageJobDto();
        List<JobDto> jobDtos = new ArrayList<>();
        Job job = new Job();
        job.setIds(ids);
        Page<Job> page = jobService.findPage(new Page<>(pageNo, pageSize), job);
        List<Job> list = page.getList();
        for (Job temp : list) {
            JobDto jobDto = companyDetailService.changeJobDto(temp);
            jobDtos.add(jobDto);
        }
        pageJobDto.setJobDtos(jobDtos);
        pageJobDto.setPageable(page.getPageable());

        return pageJobDto;
    }

    /**
     * 我的收藏-企业
     *
     * @param memberId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageCompanyDto myCoolectComany(String memberId, Integer pageNo, Integer pageSize) {
        String ids = collectStoreService.getCollectCompanyIdsByUserId(memberId);
        return getPageCompanyDto(pageNo, pageSize, ids);
    }

    public PageCompanyDto getPageCompanyDto(Integer pageNo, Integer pageSize, String ids) {
        PageCompanyDto pageCompanyDto = new PageCompanyDto();
        if (StringUtils.isEmpty(ids)) {
            return pageCompanyDto;
        }
        List<CompanyDto> companyDtos = new ArrayList<>();
        Company company = new Company();
        company.setIds(ids);

        Page<Company> page = companyService.findPage(new Page<>(pageNo, pageSize), company);
        List<Company> list = page.getList();
        for (Company temp : list) {
            CompanyDto companyDto = new CompanyDto();
            BeanUtils.copyProperties(temp, companyDto);
            companyDto.setArea(company.getArea());
            companyDtos.add(companyDto);
        }
        pageCompanyDto.setCompanyDtos(companyDtos);
        pageCompanyDto.setPageable(page.getPageable());

        return pageCompanyDto;
    }

    /**
     * 我的简历list
     *
     * @param memberId
     * @return
     */
    public List<ResumeDto> myResumeList(String memberId) {
        List<ResumeDto> resumeDtos = new ArrayList<>();
        resumeDtos.addAll(createResumeService.getResume(memberId, "0"));
        resumeDtos.addAll(createResumeService.getResume(memberId, "1"));
        return resumeDtos;
    }

    public List<ReDeliverDto> reJobInList(String memberId, String type) {
        List<ReDeliverDto> reDeliverDtos = new ArrayList<>();
        List<JobIn> jobIns = jobInService.reJobInList(memberId, type);
        for (JobIn jobIn : jobIns) {
            ReDeliverDto reDeliverDto = new ReDeliverDto();
            BeanUtils.copyProperties(jobIn, reDeliverDto);
            reDeliverDtos.add(reDeliverDto);
        }
        return reDeliverDtos;
    }

}
