package com.weimhc.api.modules.dto.req.cadet.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 实习经历
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 15:00
 */
public class ResumePracticeExperienceRQ {
    private String id;
    /**
     * 简历id
     */
    private String resumeId;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 职位名称
     */
    private String jobName;
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
     * 实习描述
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

    @ApiModelProperty(value = "公司名称")
    public String getCompanyName() {
        return companyName;
    }

    @ApiModelProperty(value = "职位名称")
    public String getJobName() {
        return jobName;
    }

    @ApiModelProperty(value = "城市id")
    public Area getCity() {
        return city;
    }

    @ApiModelProperty(value = "开始在校时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    @ApiModelProperty(value = "结束在校时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "实习描述")
    public String getDescription() {
        return description;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
