package com.weimhc.api.modules.service;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.JobSearchRQ;
import com.weimhc.api.modules.dto.resp.cadet.*;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.industry.service.IndustryService;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.service.CommunicationService;
import com.weimhc.modules.job.service.JobService;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/2 10:42
 */
@Service
public class HomePageService {

    @Autowired
    private IndustryService industryService;

    @Autowired
    private JobService jobService;

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private CompanyDetailService companyDetailService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 行业标签列表
     *
     * @return
     */
    public List<IndustryOneDto> getIndustryTags() {
        Industry industry = new Industry();
        industry.setParent(new Industry("0"));
        List<Industry> allList1 = industryService.findAllList(industry);

        List<IndustryOneDto> industryOneDtos = new ArrayList<>();

        for (Industry oneIndustry : allList1) {//一级
            List<IndustryTwoDto> industryTwoDtos = new ArrayList<>();

            IndustryOneDto industryOneDto = new IndustryOneDto();
            BeanUtils.copyProperties(oneIndustry, industryOneDto);

            industry.setParent(new Industry(oneIndustry.getId()));
            List<Industry> allList2 = industryService.findAllList(industry);
            for (Industry twoIndustry : allList2) {//二级
                List<IndustryThreeDto> industryThreeDtos = new ArrayList<>();

                IndustryTwoDto industryTwoDto = new IndustryTwoDto();
                BeanUtils.copyProperties(twoIndustry, industryTwoDto);

                industry.setParent(new Industry(twoIndustry.getId()));
                List<Industry> allList3 = industryService.findAllList(industry);
                for (Industry threeIndustry : allList3) {//三级
                    IndustryThreeDto industryThreeDto = new IndustryThreeDto();
                    BeanUtils.copyProperties(threeIndustry, industryThreeDto);
                    industryThreeDtos.add(industryThreeDto);
                }
                industryTwoDto.setIndustryThreeDtos(industryThreeDtos);
                industryTwoDtos.add(industryTwoDto);
            }
            industryOneDto.setIndustryTwoDtos(industryTwoDtos);
            industryOneDtos.add(industryOneDto);
        }
        return industryOneDtos;
    }

    /**
     * 获取职位列表
     *
     * @param jobSearchRQ
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageJobDto getJobBySearch(JobSearchRQ jobSearchRQ, Integer pageNo, Integer pageSize) {
        PageJobDto pageJobDto = new PageJobDto();
        List<JobDto> jobDtos = new ArrayList<>();
        if (StringUtils.isNotEmpty(jobSearchRQ.getState()) && jobSearchRQ.getState().equals("3")) {
            jobService.overdueJob();
        }
        Job jobSearch = changeJob(jobSearchRQ);
        Page<Job> pageJob = new Page<>(pageNo, pageSize);
        Integer sortWay = jobSearchRQ.getSortWay();
        if (sortWay != null && sortWay == 1) {
            // 最新发布的排序方式，先按照“年-月-日”格式的更新时间倒序排序，再按创建时间倒序排序
            pageJob.getPageable().setOrderBy("DATE_FORMAT(a.update_date,\"%Y-%m-%d\") DESC,a.create_date DESC");
        }
        Page<Job> page = jobService.findPage(pageJob, jobSearch);
        List<Job> list = page.getList();
        for (Job job : list) {
            JobDto jobDto = companyDetailService.changeJobDto(job);
            jobDtos.add(jobDto);
        }
        pageJobDto.setJobDtos(jobDtos);
        pageJobDto.setPageable(page.getPageable());

        return pageJobDto;
    }

    /**
     * 转化为Job
     *
     * @param jobSearchRQ
     * @return
     */
    private Job changeJob(JobSearchRQ jobSearchRQ) {
        Job job = new Job();
        job.setCompany(new Company(jobSearchRQ.getCompanyId()));
        job.setSearchName(jobSearchRQ.getSearchName());
        job.setIndustry(new Industry(jobSearchRQ.getIndustryId()));
        job.setCity(new Area(jobSearchRQ.getAreaId()));
        job.setLowest(jobSearchRQ.getMinDayMoney() != null ? jobSearchRQ.getMinDayMoney() + "" : "");
        job.setHighest(jobSearchRQ.getMaxDayMoney() != null ? jobSearchRQ.getMaxDayMoney() + "" : "");
        job.setDayNumber(jobSearchRQ.getPracticeDay());
        job.setEducation(jobSearchRQ.getMixEducational() != null ? jobSearchRQ.getMixEducational() + "" : "");
        job.setChance(jobSearchRQ.getIsWorker());
        job.setIsPush(jobSearchRQ.getIsPush());
        job.setMinPracticeMonth(jobSearchRQ.getMinPracticeMonth());
        job.setMaxPracticeMonth(jobSearchRQ.getMaxPracticeMonth());
        job.setHomeShow(jobSearchRQ.getHomeShow());
        job.setState(jobSearchRQ.getState());
        return job;
    }

    public Integer getHitNum() {
        return jobService.getHitNum();
    }


    public List<StudentDto> listAllStudent(Date startDate, Date endDate) {
        List<StudentDto> studentDtos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setStartCreateDate(startDate);
        userInfo.setEndCreateDate(endDate);
        userInfo.setType("1");
        List<UserInfo> list = userInfoService.findList(userInfo);
        for (UserInfo info : list) {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(info, studentDto);
            studentDto.setUserName(info.getUsername());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }
}
