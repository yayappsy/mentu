package com.weimhc.api.modules.dto.resp.cadet;

import com.weimhc.api.modules.dto.req.cadet.resume.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 简历详情
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/9 19:31
 */
public class ResumeDetailDto {
    private String name;

    private String phone;

    private String email;

    private String avatar;//头像

    private List<ResumeEducationRQ> resumeEducationRQS;//教育背景

    private List<ResumePracticeExperienceRQ> resumePracticeExperienceRQS;//实习经历

    private List<ResumeScienceExperienceRQ> resumeScienceExperienceRQS;//学术经历

    private List<ResumeSchoolExperienceRQ> resumeSchoolExperienceRQS;//校园经历

    private List<ResumeSkillHobbyRQ> resumeSkillHobbyRQS;//技能爱好

    private ResumeAssessRQ resumeAssessRQ;//个人评价

    @ApiModelProperty(value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "电话")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ApiModelProperty(value = "邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "头像")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ResumeEducationRQ> getResumeEducationRQS() {
        return resumeEducationRQS;
    }

    public void setResumeEducationRQS(List<ResumeEducationRQ> resumeEducationRQS) {
        this.resumeEducationRQS = resumeEducationRQS;
    }

    public List<ResumePracticeExperienceRQ> getResumePracticeExperienceRQS() {
        return resumePracticeExperienceRQS;
    }

    public void setResumePracticeExperienceRQS(List<ResumePracticeExperienceRQ> resumePracticeExperienceRQS) {
        this.resumePracticeExperienceRQS = resumePracticeExperienceRQS;
    }

    public List<ResumeScienceExperienceRQ> getResumeScienceExperienceRQS() {
        return resumeScienceExperienceRQS;
    }

    public void setResumeScienceExperienceRQS(List<ResumeScienceExperienceRQ> resumeScienceExperienceRQS) {
        this.resumeScienceExperienceRQS = resumeScienceExperienceRQS;
    }

    public List<ResumeSchoolExperienceRQ> getResumeSchoolExperienceRQS() {
        return resumeSchoolExperienceRQS;
    }

    public void setResumeSchoolExperienceRQS(List<ResumeSchoolExperienceRQ> resumeSchoolExperienceRQS) {
        this.resumeSchoolExperienceRQS = resumeSchoolExperienceRQS;
    }

    public List<ResumeSkillHobbyRQ> getResumeSkillHobbyRQS() {
        return resumeSkillHobbyRQS;
    }

    public void setResumeSkillHobbyRQS(List<ResumeSkillHobbyRQ> resumeSkillHobbyRQS) {
        this.resumeSkillHobbyRQS = resumeSkillHobbyRQS;
    }

    public ResumeAssessRQ getResumeAssessRQ() {
        return resumeAssessRQ;
    }

    public void setResumeAssessRQ(ResumeAssessRQ resumeAssessRQ) {
        this.resumeAssessRQ = resumeAssessRQ;
    }
}
