package com.weimhc.api.modules.dto.req.cadet.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 教育背景
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 14:59
 */
public class ResumeEducationRQ {
    private String id;
    /**
     * 简历
     */
    private String resumeId;
    /**
     * 会员
     */
    private String memberId;
    /**
     * 学校
     */
    private String school;
    /**
     * 专业
     */
    private String majorId;
    /**
     * 学历
     */
    private String education;
    /**
     * 城市
     */
    private Area city;
    /**
     * 开始在校时间
     */
    private Date beginDate;
    /**
     * 结束在校时间
     */
    private Date endDate;
    /**
     * 成绩排名
     */
    private String classRank;
    /**
     * 主修课程
     */
    private String majorCourse;
    /**
     * 荣誉奖励
     */
    private String honor;


    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    @ApiModelProperty(value = "会员id")
    public String getMemberId() {
        return memberId;
    }

    @ApiModelProperty(value = "学校")
    public String getSchool() {
        return school;
    }

    @ApiModelProperty(value = "专业")
    public String getMajorId() {
        return majorId;
    }

    @ApiModelProperty(value = "学历(0，大专 1，本科 2，硕士 3，博士)")
    public String getEducation() {
        return education;
    }

    @ApiModelProperty(value = "城市")
    public Area getCity() {
        return city;
    }

    @ApiModelProperty(value = "开始在校时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束在校时间")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "成绩排名")
    public String getClassRank() {
        return classRank;
    }

    @ApiModelProperty(value = "主修课程")
    public String getMajorCourse() {
        return majorCourse;
    }

    @ApiModelProperty(value = "荣誉奖励")
    public String getHonor() {
        return honor;
    }

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setClassRank(String classRank) {
        this.classRank = classRank;
    }

    public void setMajorCourse(String majorCourse) {
        this.majorCourse = majorCourse;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }
}
