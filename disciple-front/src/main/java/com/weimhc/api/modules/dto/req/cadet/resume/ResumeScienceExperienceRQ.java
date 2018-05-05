package com.weimhc.api.modules.dto.req.cadet.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 学术经历
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 15:00
 */
public class ResumeScienceExperienceRQ {
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
     * 城市
     */
    private Area city;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 担任职务
     */
    private String assumeOffice;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 学术描述
     */
    private String description;

    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    @ApiModelProperty(value = "会员id")
    public String getMemberId() {
        return memberId;
    }

    @ApiModelProperty(value = "城市id")
    public Area getCity() {
        return city;
    }

    @ApiModelProperty(value = "项目名称")
    public String getProjectName() {
        return projectName;
    }

    @ApiModelProperty(value = "担任职务")
    public String getAssumeOffice() {
        return assumeOffice;
    }

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "学术描述")
    public String getDescription() {
        return description;
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

    public void setCity(Area city) {
        this.city = city;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setAssumeOffice(String assumeOffice) {
        this.assumeOffice = assumeOffice;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
