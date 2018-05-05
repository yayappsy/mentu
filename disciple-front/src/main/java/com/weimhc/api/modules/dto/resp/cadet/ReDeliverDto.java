package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 投递反馈
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/28 15:46
 */
public class ReDeliverDto {
    private String jobId;

    private String status;//状态

    private String jobName;//

    private String companyName;//

    private String cityName;//工作城市

    private String resumeType;//简历类型

    private Date updateDate;//时间

    @ApiModelProperty(value = "职业id")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @ApiModelProperty(value = "状态（0投递成功，1通知面试，2不合适，9被查看）传空字符为全部")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ApiModelProperty(value = "城市")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @ApiModelProperty(value = "职位名称")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @ApiModelProperty("公司名称")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    @ApiModelProperty(value = "简历类型（0:默认简历,1:简历）")
    public String getResumeType() {
        return resumeType;
    }

    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
