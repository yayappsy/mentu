package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 应聘简历
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/10 9:15
 */
public class JobInResumeDto {

    private String id;//应聘管理id
    private String resumeId;//简历id

    private String avatar;//头像

    private String position;//期望职位

    private String name;//姓名

    private String gender;//姓别

    private String residenceName;//居住地

    private Integer days;//每周天数

    private Double  months;//实习时长

    private Date arrivalDate;//最晚入职时间

    private String school;//学校

    private String major;//专业

    private String education;//学历

    private Date endDate;//毕业时间

    private Date createDate;//投递时间

    private String jobName;//职位名称

    private String email;//邮箱

    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "头像")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @ApiModelProperty(value = "期望职位")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ApiModelProperty(value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "姓别")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ApiModelProperty(value = "居住地")
    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName;
    }

    @ApiModelProperty(value = "每周天数")
    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @ApiModelProperty(value = "实习时长")
    public Double getMonths() {
        return months;
    }

    public void setMonths(Double months) {
        this.months = months;
    }

    @ApiModelProperty(value = "最晚入职时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @ApiModelProperty(value = "应聘管理id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "学校")

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @ApiModelProperty(value = "专业")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @ApiModelProperty(value = "学历")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @ApiModelProperty(value = "毕业时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ApiModelProperty(value = "投递时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    @ApiModelProperty(value = "职位名称")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
