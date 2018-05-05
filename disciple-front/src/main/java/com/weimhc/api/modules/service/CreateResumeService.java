package com.weimhc.api.modules.service;

import com.weimhc.api.modules.dto.req.cadet.UserInfoRQ;
import com.weimhc.api.modules.dto.req.cadet.resume.*;
import com.weimhc.api.modules.dto.resp.cadet.ResumeDto;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.industry.service.IndustryService;
import com.weimhc.modules.job.entity.*;
import com.weimhc.modules.job.service.*;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.service.AreaService;
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
 * @Date 2017/12/27 14:42
 */
@Service
public class CreateResumeService {

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
    private AreaService areaService;

    @Autowired
    private EducationHighestService educationHighestService;

    @Autowired
    private IndustryService industryService;

    /**
     * 保存期望实习
     *
     * @param resumeHopePracticeRQ
     */
    public void saveHopePractice(ResumeHopePracticeRQ resumeHopePracticeRQ) {
        ExpectPractice expectPractice = new ExpectPractice();
        BeanUtils.copyProperties(resumeHopePracticeRQ, expectPractice);
        expectPractice.setCity(resumeHopePracticeRQ.getCity());
        expectPractice.setPosition(resumeHopePracticeRQ.getIndustry().getId());
        expectPracticeService.save(expectPractice);
    }

    /**
     * 获取期望实习
     *
     * @param id
     * @return
     */
    public ResumeHopePracticeRQ getHopePractice(String id) {
        ResumeHopePracticeRQ resumeHopePracticeRQ = new ResumeHopePracticeRQ();
        ExpectPractice expectPractice = new ExpectPractice();
        expectPractice.setResumeId(id);
        List<ExpectPractice> list = expectPracticeService.findAllList(expectPractice);
        if (list.size() > 0) {
            ExpectPractice temp = list.get(0);
            BeanUtils.copyProperties(temp, resumeHopePracticeRQ);
            Industry industry = industryService.get(temp.getPosition());
            resumeHopePracticeRQ.setIndustry(industry);
            resumeHopePracticeRQ.setCity(temp.getCity());
            return resumeHopePracticeRQ;
        }
        return null;
    }

    /**
     * 获取教育背景
     *
     * @param id
     * @return
     */
    public List<ResumeEducationRQ> getEducations(String id) {
        List<ResumeEducationRQ> resumeEducationRQS = new ArrayList<>();
        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setResumeId(id);
        educationBackground.setEduHighest("0");//不是最高学历
        List<EducationBackground> list = educationBackgroundService.findAllList(educationBackground);
        for (EducationBackground background : list) {
            ResumeEducationRQ resumeEducationRQ = new ResumeEducationRQ();
            resumeEducationRQ.setEducation(background.getEducationId());
            BeanUtils.copyProperties(background, resumeEducationRQ);
            resumeEducationRQ.setCity(background.getCity());
            resumeEducationRQS.add(resumeEducationRQ);
        }
        return resumeEducationRQS;
    }

    /**
     * 保存教育背景
     *
     * @param resumeEducationRQ
     * @return
     */
    public void saveEducation(ResumeEducationRQ resumeEducationRQ) {

        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setEducationId(resumeEducationRQ.getEducation());
        BeanUtils.copyProperties(resumeEducationRQ, educationBackground);
        educationBackground.setCity(resumeEducationRQ.getCity());
        educationBackground.setEduHighest("0");//不是最高学历
        educationBackgroundService.save(educationBackground);
    }

    /**
     * 删除教育背景
     *
     * @param id
     */
    public void delEducation(String id) {
        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setId(id);
        educationBackgroundService.delete(educationBackground);
    }

    /**
     * 获取实习经历
     *
     * @param id
     * @return
     */
    public List<ResumePracticeExperienceRQ> getPracticeExperiences(String id) {
        List<ResumePracticeExperienceRQ> resumePracticeExperienceRQS = new ArrayList<>();
        PracticeLive practiceLive = new PracticeLive();
        practiceLive.setResumeId(id);
        List<PracticeLive> list = practiceLiveService.findAllList(practiceLive);
        for (PracticeLive live : list) {
            ResumePracticeExperienceRQ resumePracticeExperienceRQ = new ResumePracticeExperienceRQ();
            BeanUtils.copyProperties(live, resumePracticeExperienceRQ);
            resumePracticeExperienceRQ.setCity(live.getCity());
            resumePracticeExperienceRQS.add(resumePracticeExperienceRQ);
        }
        return resumePracticeExperienceRQS;
    }

    /**
     * 保存实习经历
     *
     * @param resumePracticeExperienceRQ
     */
    public void savePracticeExperience(ResumePracticeExperienceRQ resumePracticeExperienceRQ) {
        PracticeLive practiceLive = new PracticeLive();
        BeanUtils.copyProperties(resumePracticeExperienceRQ, practiceLive);
        practiceLive.setCity(resumePracticeExperienceRQ.getCity());
        practiceLiveService.save(practiceLive);
    }

    /**
     * 删除实习经历
     *
     * @param id
     */
    public void delPracticeExperience(String id) {
        PracticeLive practiceLive = new PracticeLive();
        practiceLive.setId(id);
        practiceLiveService.delete(practiceLive);
    }

    /**
     * 获取学术经历
     *
     * @param id
     * @return
     */
    public List<ResumeScienceExperienceRQ> getScienceExperiences(String id) {
        List<ResumeScienceExperienceRQ> resumeScienceExperienceRQS = new ArrayList<>();
        LearningLive learningLive = new LearningLive();
        learningLive.setResumeId(id);
        List<LearningLive> list = learningLiveService.findAllList(learningLive);
        for (LearningLive live : list) {
            ResumeScienceExperienceRQ resumePracticeExperienceRQ = new ResumeScienceExperienceRQ();
            BeanUtils.copyProperties(live, resumePracticeExperienceRQ);
            resumePracticeExperienceRQ.setCity(live.getCity());
            resumeScienceExperienceRQS.add(resumePracticeExperienceRQ);
        }
        return resumeScienceExperienceRQS;
    }

    /**
     * 保存学术经历
     *
     * @param resumeScienceExperienceRQ
     */
    public void saveScienceExperience(ResumeScienceExperienceRQ resumeScienceExperienceRQ) {
        LearningLive learningLive = new LearningLive();
        BeanUtils.copyProperties(resumeScienceExperienceRQ, learningLive);
        learningLive.setCity(resumeScienceExperienceRQ.getCity());
        learningLiveService.save(learningLive);
    }

    /**
     * 删除学术经历
     *
     * @param id
     */
    public void delScienceExperience(String id) {
        LearningLive learningLive = new LearningLive();
        learningLive.setId(id);
        learningLiveService.delete(learningLive);
    }

    /**
     * 获取校园经历
     *
     * @param id
     * @return
     */
    public List<ResumeSchoolExperienceRQ> getSchoolExperiences(String id) {
        List<ResumeSchoolExperienceRQ> resumeSchoolExperienceRQS = new ArrayList<>();

        SchoolLive schoolLive = new SchoolLive();
        schoolLive.setResumeId(id);
        List<SchoolLive> list = schoolLiveService.findAllList(schoolLive);
        for (SchoolLive live : list) {
            ResumeSchoolExperienceRQ resumeSchoolExperienceRQ = new ResumeSchoolExperienceRQ();
            BeanUtils.copyProperties(live, resumeSchoolExperienceRQ);
            resumeSchoolExperienceRQ.setCity(live.getCity());
            resumeSchoolExperienceRQS.add(resumeSchoolExperienceRQ);
        }
        return resumeSchoolExperienceRQS;
    }

    /**
     * 删除校园经历
     *
     * @param id
     */
    public void delSchoolExperience(String id) {
        SchoolLive schoolLive = new SchoolLive();
        schoolLive.setId(id);
        schoolLiveService.delete(schoolLive);
    }

    /**
     * 保存校园经历
     *
     * @param resumeSchoolExperienceRQ
     */
    public void saveSchoolExperience(ResumeSchoolExperienceRQ resumeSchoolExperienceRQ) {
        SchoolLive schoolLive = new SchoolLive();
        BeanUtils.copyProperties(resumeSchoolExperienceRQ, schoolLive);
        schoolLive.setCity(resumeSchoolExperienceRQ.getCity());
        schoolLiveService.save(schoolLive);
    }

    /**
     * 获取技能爱好
     *
     * @param id
     * @return
     */
    public List<ResumeSkillHobbyRQ> getSkillHobbys(String id) {
        List<ResumeSkillHobbyRQ> resumeSkillHobbyRQS = new ArrayList<>();

        Skill skill = new Skill();
        skill.setResumeId(id);
        List<Skill> list = skillService.findAllList(skill);
        for (Skill skill1 : list) {
            ResumeSkillHobbyRQ resumeSkillHobbyRQ = new ResumeSkillHobbyRQ();
            BeanUtils.copyProperties(skill1, resumeSkillHobbyRQ);
            resumeSkillHobbyRQS.add(resumeSkillHobbyRQ);
        }
        return resumeSkillHobbyRQS;
    }

    /**
     * 删除技能爱好
     *
     * @param id
     */
    public void delSkillHobby(String id) {
        Skill skill = new Skill();
        skill.setId(id);
        skillService.delete(skill);
    }

    /**
     * 保存技能爱好
     *
     * @param resumeSkillHobbyRQ
     */
    public void saveSkillHobby(ResumeSkillHobbyRQ resumeSkillHobbyRQ) {
        Skill skill = new Skill();
        BeanUtils.copyProperties(resumeSkillHobbyRQ, skill);
        skillService.save(skill);
    }

    /**
     * 获取作品展示
     *
     * @param id
     * @return
     */
    public List<ResumeOpusShowRQ> getOpusShows(String id) {
        List<ResumeOpusShowRQ> resumeOpusShowRQS = new ArrayList<>();

        OpusShow opusShow = new OpusShow();
        opusShow.setResumeId(id);
        List<OpusShow> list = opusShowService.findAllList(opusShow);
        for (OpusShow show : list) {
            ResumeOpusShowRQ resumeOpusShowRQ = new ResumeOpusShowRQ();
            BeanUtils.copyProperties(show, resumeOpusShowRQ);
            resumeOpusShowRQS.add(resumeOpusShowRQ);
        }
        return resumeOpusShowRQS;
    }

    /**
     * 删除作品展示
     *
     * @param id
     */
    public void delOpusShow(String id) {
        OpusShow opusShow = new OpusShow();
        opusShow.setId(id);
        opusShowService.delete(opusShow);
    }

    /**
     * 保存作品展示
     *
     * @param resumeOpusShowRQ
     */
    public void saveOpusShow(ResumeOpusShowRQ resumeOpusShowRQ) {
        OpusShow opusShow = new OpusShow();
        BeanUtils.copyProperties(resumeOpusShowRQ, opusShow);
        opusShowService.save(opusShow);
    }

    /**
     * 创建简历
     *
     * @param userId
     * @param type
     * @return
     */
    public Boolean create(String userId, String language, String type, String name) {
        Resume resume = new Resume();
        resume.setLanguage(language);
        resume.setUserInfoId(userId);
        resume.setResumeType(type);
        resume.setName(name);
        resumeService.save(resume);
        return true;
    }

    /**
     * 获取简历
     *
     * @param userId
     * @param type
     * @return
     */
    public List<ResumeDto> getResume(String userId, String type) {
        List<ResumeDto> resumeDtos = new ArrayList<>();
        Resume resume = new Resume();
        resume.setUserInfoId(userId);
        resume.setResumeType(type);
        List<Resume> list = resumeService.findAllList(resume);
        for (Resume temp : list) {
            ResumeDto resumeDto = new ResumeDto();
            resumeDto.setUserId(temp.getUserInfoId());
            resumeDto.setName(temp.getName());
            resumeDto.setType(type);
            resumeDto.setLanguage(temp.getLanguage());
            String resumeId = temp.getId();
            resumeDto.setResumeId(resumeId);
            Integer moduleNum = resumeService.getModuleNum(resumeId);
            resumeDto.setModuleNum(moduleNum);
            Date newDate = resumeService.getNewDate(resumeId);
            resumeDto.setNewDate(newDate);
            resumeDtos.add(resumeDto);
        }
        return resumeDtos;
    }

    /**
     * 保存个人信息
     *
     * @param userInfoRQ
     * @return
     */
    public Boolean editUser(UserInfoRQ userInfoRQ) {
        UserInfo userInfo = userInfoService.getByResume(userInfoRQ.getId());
        if (userInfo == null) {
            return false;
        }
        EducationHighest background = educationHighestService.getByMemberId(userInfoRQ.getId());
        if (background == null) {
            background = new EducationHighest();
        }
        background.setEndDate(userInfoRQ.getEndStudy());
        background.setEducationId(userInfoRQ.getEducation());
        background.setSchool(userInfoRQ.getSchool());
        background.setMajorId(userInfoRQ.getMajorId());
        background.setMemberId(userInfoRQ.getId());
        educationHighestService.save(background);

        userInfo.setName(userInfoRQ.getName());
        userInfo.setGender(userInfoRQ.getGender());
        userInfo.setBirth(userInfoRQ.getBirth());
        Region residence = userInfoRQ.getResidence();
        userInfo.setResidence(residence);
        if (residence != null) {
            Area area = areaService.get(residence.getId());
            if (area != null) {
                userInfo.setResidenceName(area.getName());
            }
        }
        userInfo.setMobile(userInfoRQ.getMobile());
        userInfo.setEmail(userInfoRQ.getEmail());
        userInfo.setAvatar(userInfoRQ.getAvatar());
        userInfoService.save(userInfo);

        return true;
    }

    /**
     * 获取个人信息
     *
     * @param userId
     * @return
     */
    public UserInfoRQ getUserInfo(String userId) {
        UserInfoRQ userInfoRQ = new UserInfoRQ();
        UserInfo userInfo = userInfoService.getByResume(userId);
        if (userInfo == null) {
            return userInfoRQ;
        }
        BeanUtils.copyProperties(userInfo, userInfoRQ);
        EducationHighest educationHighest = educationHighestService.getByMemberId(userId);
        if (educationHighest != null) {
            userInfoRQ.setEndStudy(educationHighest.getEndDate());
            userInfoRQ.setEducation(educationHighest.getEducationId());
            userInfoRQ.setSchool(educationHighest.getSchool());
            userInfoRQ.setMajorId(educationHighest.getMajorId());
        }
        userInfoRQ.setResidence(userInfo.getResidence());
        return userInfoRQ;
    }

    /**
     * 保存个人评价
     *
     * @param resumeAssessRQ
     */
    public void saveOwnAssess(ResumeAssessRQ resumeAssessRQ) {
        Access access = new Access();
        BeanUtils.copyProperties(resumeAssessRQ, access);
        accessService.save(access);
    }

    /**
     * 获取个人评价
     *
     * @param id
     * @return
     */
    public ResumeAssessRQ getOwnAssess(String id) {
        ResumeAssessRQ resumeAssessRQ = new ResumeAssessRQ();
        Access access = new Access();
        access.setResumeId(id);
        List<Access> allList = accessService.findAllList(access);
        if (allList.size() > 0) {
            Access temp = allList.get(0);
            BeanUtils.copyProperties(temp, resumeAssessRQ);
        }
        return resumeAssessRQ;
    }


    /**
     * 保存附件
     *
     * @param resumeRelatedOptionRQ
     */
    public void saveRelatedOption(ResumeRelatedOptionRQ resumeRelatedOptionRQ) {
        RelatedOption relatedOption = new RelatedOption();
        BeanUtils.copyProperties(resumeRelatedOptionRQ, relatedOption);
        relatedOptionService.save(relatedOption);
    }

    /**
     * 获取附件
     *
     * @param id
     * @return
     */
    public ResumeRelatedOptionRQ getRelatedOption(String id, String type) {
        ResumeRelatedOptionRQ resumeRelatedOptionRQ = new ResumeRelatedOptionRQ();
        RelatedOption relatedOption = new RelatedOption();
        relatedOption.setResumeId(id);
        relatedOption.setType(type);
        List<RelatedOption> allList = relatedOptionService.findAllList(relatedOption);
        if (allList.size() > 0) {
            RelatedOption temp = allList.get(0);
            BeanUtils.copyProperties(temp, resumeRelatedOptionRQ);
        }
        return resumeRelatedOptionRQ;
    }

    public Boolean exitResumeId(String resumeId) {
        Resume resume = resumeService.get(resumeId);
        if (resume == null) {
            return false;
        }
        return true;
    }

    public String getTest(String id) {
        List<Skill> skills = skillService.getByResumeId(id);
        List<ResumeSkillHobbyRQ> resumeSkillHobbyRQS = new ArrayList<>();
        for (Skill skill : skills) {
            ResumeSkillHobbyRQ resumeSkillHobbyRQ = new ResumeSkillHobbyRQ();
            BeanUtils.copyProperties(skill, resumeSkillHobbyRQ);
            resumeSkillHobbyRQS.add(resumeSkillHobbyRQ);
        }
        System.out.println(resumeSkillHobbyRQS);

        return null;
    }

    public void delRelatedOption(String id) {
        RelatedOption relatedOption = new RelatedOption();
        relatedOption.setId(id);
        relatedOptionService.delete(relatedOption);
        //没有删除文件物理位置
    }

}
