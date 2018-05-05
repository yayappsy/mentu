package com.weimhc.api.modules.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.api.modules.dto.req.cadet.SendEmailQR;
import com.weimhc.api.modules.dto.req.cadet.resume.*;
import com.weimhc.api.modules.dto.resp.cadet.JobInResumeDto;
import com.weimhc.api.modules.dto.resp.cadet.PageJobInResumeDto;
import com.weimhc.api.modules.dto.resp.cadet.ResumeDetailDto;
import com.weimhc.modules.job.entity.*;
import com.weimhc.modules.job.service.*;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 应聘管理
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/8 22:11
 */
@Service
public class JobInManagerService {

    @Autowired
    private JobInService jobInService;

    @Autowired
    private CompanyContactService companyContactService;

    @Autowired
    private EducationBackgroundService educationBackgroundService;

    @Autowired
    private PracticeLiveService practiceLiveService;

    @Autowired
    private LearningLiveService learningLiveService;

    @Autowired
    private SchoolLiveService schoolLiveService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private OpusShowService opusShowService;

    @Autowired
    private ExpectPracticeService expectPracticeService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private RelatedOptionService relatedOptionService;

    @Autowired
    private JobService jobService;


    /**
     * 获取简历详情
     *
     * @param resumeId
     * @return
     */
    public ResumeDetailDto getResumeDetail(String resumeId) {
        ResumeDetailDto resumeDetailDto = new ResumeDetailDto();

        Resume resume = resumeService.get(resumeId);
        if (resume == null) {
            return resumeDetailDto;
        }
        UserInfo userInfo = userInfoService.get(resume.getUserInfoId());
        resumeDetailDto.setAvatar(userInfo.getAvatar());
        resumeDetailDto.setEmail(userInfo.getEmail());
        resumeDetailDto.setName(userInfo.getName());
        resumeDetailDto.setPhone(userInfo.getMobile());

        //教育背景
        List<EducationBackground> educationBackgrounds = educationBackgroundService.getByResumeId(resumeId);
        List<ResumeEducationRQ> resumeEducationRQS = new ArrayList<>();
        for (EducationBackground educationBackground : educationBackgrounds) {
            ResumeEducationRQ resumeEducationRQ = new ResumeEducationRQ();
            BeanUtils.copyProperties(educationBackground, resumeEducationRQ);
            resumeEducationRQS.add(resumeEducationRQ);
        }
        resumeDetailDto.setResumeEducationRQS(resumeEducationRQS);

        //实习经历
        List<PracticeLive> practiceLives = practiceLiveService.getByResumeId(resumeId);
        List<ResumePracticeExperienceRQ> resumePracticeExperienceRQS = new ArrayList<>();
        for (PracticeLive practiceLive : practiceLives) {
            ResumePracticeExperienceRQ resumePracticeExperienceRQ = new ResumePracticeExperienceRQ();
            BeanUtils.copyProperties(practiceLive, resumePracticeExperienceRQ);
            resumePracticeExperienceRQS.add(resumePracticeExperienceRQ);
        }
        resumeDetailDto.setResumePracticeExperienceRQS(resumePracticeExperienceRQS);

        //学术经历
        List<LearningLive> learningLives = learningLiveService.getByResumeId(resumeId);
        List<ResumeScienceExperienceRQ> resumeScienceExperienceRQS = new ArrayList<>();
        for (LearningLive learningLive : learningLives) {
            ResumeScienceExperienceRQ resumeScienceExperienceRQ = new ResumeScienceExperienceRQ();
            BeanUtils.copyProperties(learningLive, resumeScienceExperienceRQ);
            resumeScienceExperienceRQS.add(resumeScienceExperienceRQ);
        }
        resumeDetailDto.setResumeScienceExperienceRQS(resumeScienceExperienceRQS);

        //校园经历
        List<SchoolLive> schoolLives = schoolLiveService.getByResumeId(resumeId);
        List<ResumeSchoolExperienceRQ> resumeSchoolExperienceRQS = new ArrayList<>();
        for (SchoolLive schoolLive : schoolLives) {
            ResumeSchoolExperienceRQ resumeSchoolExperienceRQ = new ResumeSchoolExperienceRQ();
            BeanUtils.copyProperties(schoolLive, resumeSchoolExperienceRQ);
            resumeSchoolExperienceRQS.add(resumeSchoolExperienceRQ);
        }
        resumeDetailDto.setResumeSchoolExperienceRQS(resumeSchoolExperienceRQS);

        //技能爱好
        List<Skill> skills = skillService.getByResumeId(resumeId);
        List<ResumeSkillHobbyRQ> resumeSkillHobbyRQS = new ArrayList<>();
        for (Skill skill : skills) {
            ResumeSkillHobbyRQ resumeSkillHobbyRQ = new ResumeSkillHobbyRQ();
            BeanUtils.copyProperties(skill, resumeSkillHobbyRQ);
            resumeSkillHobbyRQS.add(resumeSkillHobbyRQ);
        }
        resumeDetailDto.setResumeSkillHobbyRQS(resumeSkillHobbyRQS);
        //个人评价
        Access assess = accessService.getByResumeId(resumeId);
        if (assess != null) {
            ResumeAssessRQ resumeAssessRQ = new ResumeAssessRQ();
            BeanUtils.copyProperties(assess, resumeAssessRQ);
            resumeDetailDto.setResumeAssessRQ(resumeAssessRQ);
        }
        return resumeDetailDto;
    }

    /**
     * @param companyId
     * @return
     */
    public Long handleNum(String companyId) {
        ResumeSearchRQ resumeSearchRQ = new ResumeSearchRQ();
        resumeSearchRQ.setCompanyId(companyId);
        resumeSearchRQ.setStatus("0");
        PageJobInResumeDto pageJobInResumeDto = searchResume(resumeSearchRQ, 1, 10);
        return pageJobInResumeDto.getPageable().getCount();
    }


    /**
     * 查看简历
     *
     * @param companyId
     * @param resumeId
     * @return
     */
    public Boolean lookResume(String companyId, String resumeId) {
        jobInService.lookResume(companyId, resumeId);
        return true;
    }

    /**
     * 保存邀请模板
     *
     * @param companyContactRQ
     */
    public void saveCompanyContact(CompanyContactRQ companyContactRQ) {
        CompanyContact companyContact = new CompanyContact();
        BeanUtils.copyProperties(companyContactRQ, companyContact);
        companyContactService.save(companyContact);
    }

    /**
     * 获取邀请模板
     *
     * @param id
     * @return
     */
    public List<CompanyContactRQ> getCompanyContacts(String id) {
        List<CompanyContactRQ> companyContactRQS = new ArrayList<>();

        CompanyContact companyContact = new CompanyContact();
        companyContact.setCompanyId(id);
        List<CompanyContact> allList = companyContactService.findAllList(companyContact);
        for (CompanyContact contact : allList) {
            CompanyContactRQ companyContactRQ = new CompanyContactRQ();
            BeanUtils.copyProperties(contact, companyContactRQ);
            companyContactRQS.add(companyContactRQ);
        }
        return companyContactRQS;
    }

    /**
     * 删除邀请模板
     *
     * @param id
     */
    public void delCompanyContact(String id) {

        companyContactService.delete(new CompanyContact(id));

    }

    /**
     * 处理简历
     *
     * @param id
     * @param result
     * @return
     */
    public Boolean handResume(String id, String result) {
        JobIn jobIn = jobInService.get(id);
        if (jobIn == null) {
            return false;
        }
        jobIn.setStatus(result);
        jobIn.setIsLook("1");
        jobInService.save(jobIn);
        return true;
    }

    /**
     * 简历搜索
     *
     * @param resumeSearchRQ
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageJobInResumeDto searchResume(ResumeSearchRQ resumeSearchRQ, Integer pageNo, Integer pageSize) {
        PageJobInResumeDto pageJobInResumeDto = new PageJobInResumeDto();
        List<JobInResumeDto> jobInResumeDtos = new ArrayList<>();
        JobIn jobIn = changeJobIn(resumeSearchRQ);
        Page<JobIn> page = jobInService.findPage(new Page<>(pageNo, pageSize), jobIn);
        List<JobIn> list = page.getList();
        for (JobIn temp : list) {
            JobInResumeDto jobInResumeDto = changeJobInResumeDto(temp);
            jobInResumeDtos.add(jobInResumeDto);
        }
        pageJobInResumeDto.setJobInResumeDtos(jobInResumeDtos);
        pageJobInResumeDto.setPageable(page.getPageable());
        return pageJobInResumeDto;
    }

    /**
     * 转化为 简历dto
     *
     * @param jobIn
     * @return
     */
    private JobInResumeDto changeJobInResumeDto(JobIn jobIn) {
        JobInResumeDto jobInResumeDto = new JobInResumeDto();
        //简历id
        jobInResumeDto.setResumeId(jobIn.getResumeId());
        jobInResumeDto.setId(jobIn.getId());
        //期望职位
        if (jobIn.getExpectPractice() != null) {
            jobInResumeDto.setPosition(jobIn.getExpectPractice().getPosition());
            //每周天数
            jobInResumeDto.setDays(jobIn.getExpectPractice().getDays());
            //实习时长
            jobInResumeDto.setMonths(jobIn.getExpectPractice().getMonths());
            //最晚入职时间
            jobInResumeDto.setArrivalDate(jobIn.getExpectPractice().getArrivalDate());
        }
        if (jobIn.getUserInfo() != null) {
            //头像
            jobInResumeDto.setAvatar(jobIn.getUserInfo().getAvatar());
            //姓名
            jobInResumeDto.setName(jobIn.getUserInfo().getName());
            //姓别
            jobInResumeDto.setGender(jobIn.getUserInfo().getGender());
            //居住地
            jobInResumeDto.setResidenceName(jobIn.getUserInfo().getResidenceName());
        }
        if (jobIn.getEducationBackground() != null) {
            //学校
            jobInResumeDto.setSchool(jobIn.getEducationBackground().getSchool());
            //专业
            jobInResumeDto.setSchool(jobIn.getEducationBackground().getMajorId());
            //学历
            jobInResumeDto.setEducation(jobIn.getEducationBackground().getEducationId());
            //毕业时间
            jobInResumeDto.setEndDate(jobIn.getEducationBackground().getEndDate());
        }
        //投递时间
        jobInResumeDto.setCreateDate(jobIn.getCreateDate());
        jobInResumeDto.setJobName(jobIn.getJobName());

        return jobInResumeDto;
    }

    /**
     * 转化为期望实习（连userInfo,连educationBackground,连jobIn）
     *
     * @param resumeSearchRQ
     * @return
     */
    private JobIn changeJobIn(ResumeSearchRQ resumeSearchRQ) {
        JobIn jobIn = new JobIn();
        UserInfo userInfo = new UserInfo();
        userInfo.setName(resumeSearchRQ.getSearchName());
        userInfo.setGender(resumeSearchRQ.getGender());
        jobIn.setUserInfo(userInfo);

        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setSchool(resumeSearchRQ.getSearchName());
        educationBackground.setEducationId(resumeSearchRQ.getEducation());
        educationBackground.setEndDate(resumeSearchRQ.getEndDate());
        jobIn.setEducationBackground(educationBackground);

        jobIn.setIsLook(resumeSearchRQ.getIsLook());
        jobIn.setStatus(resumeSearchRQ.getStatus());
        jobIn.setCompanyId(resumeSearchRQ.getCompanyId());
        if (resumeSearchRQ.getCity() != null) {
            jobIn.setCityId(resumeSearchRQ.getCity().getId());
        }

        ExpectPractice expectPractice = new ExpectPractice();
        expectPractice.setCity(resumeSearchRQ.getCity());
        expectPractice.setDays(resumeSearchRQ.getDays());
        expectPractice.setMonths(resumeSearchRQ.getMonths());
        expectPractice.setArrivalDate(resumeSearchRQ.getArrivalDate());
        expectPractice.setPosition(resumeSearchRQ.getJobName());
        jobIn.setExpectPractice(expectPractice);
        return jobIn;
    }

    /**
     * 面试通知数量
     *
     * @param companyId
     * @return
     */
    public Integer getJobInNum(String companyId, String status) {
        if (status.equals("9")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String format = simpleDateFormat.format(date);
            return jobInService.getJobInNum(companyId, "1", format);
        }
        if (status.equals("8")) {
            jobInService.handleNum(companyId);
        }
        return jobInService.getJobInNum(companyId, status, null);
    }

    /**
     * 全部职位分类
     *
     * @param id
     * @return
     */
    public List<String> getJobNames(String id) {
        List<String> jobNames = new ArrayList<>();
        List<Job> jobs = jobService.getJobNames(id);
        for (Job job : jobs) {
            jobNames.add(job.getName());
        }
        return jobNames;
    }

    public Integer getJobInNumTotal(String companyId) {
        return jobService.getJobInNumTotal(companyId);
    }

    /**
     * 发送邮件
     *
     * @param sendEmailQR
     * @return
     */
    public Boolean sendEmail(SendEmailQR sendEmailQR) throws Exception {
        String accessKeyId = Global.getConfig("email.accessKeyId");
        String secret = Global.getConfig("email.secret");
        String accountName = Global.getConfig("email.accountName");
        String tagName = Global.getConfig("email.tagName");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName(accountName);
            request.setFromAlias(sendEmailQR.getFromAlias());
            request.setAddressType(1);
            request.setTagName(tagName);
            request.setReplyToAddress(true);
            request.setToAddress(sendEmailQR.getEmail());
            request.setSubject(sendEmailQR.getSubject());
            request.setHtmlBody(sendEmailQR.getHtmlBody());
            client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Integer getJobNumTotal(String companyId) {
        return jobService.getJobNumTotal(companyId);
    }

    public Integer getJobInFaceTotal(String companyId) {
        return jobService.getJobInFaceTotal(companyId);
    }
}
