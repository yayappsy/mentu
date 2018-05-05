package com.weimhc.api.modules.dto.req.cadet.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.util.Date;

/**
 * 简历搜索
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/10 9:10
 */
public class ResumeSearchRQ {

    private String searchName;//搜索内容（姓名或学校）

    private String jobName;//职位名称

    private String isLook;//是否查看（0未查看，1已查看）

    private String status;//简历状态（0未处理，1通知面试，2不合适，3已录用，4待定）

    private Area city;//城市id

    private Integer days;//每周天数

    private Double months;//实习时长

    private Date arrivalDate;//最晚入职时间

    private String education;//学历

    private Date endDate;//毕业时间

    private String gender;//性别

    private String companyId;//企业id

    @ApiModelProperty(value = "搜索内容（姓名或学校）")
    public String getSearchName() {
        return searchName;
    }

    @ApiModelProperty(value = "是否查看（0未查看，1已查看）")
    public String getIsLook() {
        return isLook;
    }

    @ApiModelProperty(value = "简历状态（0未处理，1通知面试，2不合适，3已录用，4待定）")
    public String getStatus() {
        return status;
    }

    @ApiModelProperty(value = "城市id")
    public Area getCity() {
        return city;
    }

    @ApiModelProperty(value = "每周天数(后台已经处理<=),只需要传数字即可")
    public Integer getDays() {
        return days;
    }

    @ApiModelProperty(value = "实习时长(后台已经处理>=),只需要传数字即可")
    public Double getMonths() {
        return months;
    }

    @ApiModelProperty(value = "最晚入职时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    @ApiModelProperty(value = "学历(0，大专 1，本科 2，硕士 3，博士)")
    public String getEducation() {
        return education;
    }

    @ApiModelProperty(value = "毕业时间")
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "性别")
    public String getGender() {
        return gender;
    }

    @ApiModelProperty(value = "职位名称",example = "java")
    public String getJobName() {
        return jobName;
    }

    @ApiModelProperty(value = "职位名称",example = "java")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setIsLook(String isLook) {
        this.isLook = isLook;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setMonths(Double months) {
        this.months = months;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
