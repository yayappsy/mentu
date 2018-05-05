package com.weimhc.api.modules.dto.req.cadet.resume;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 期望实习
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 14:58
 */
public class ResumeHopePracticeRQ {

    private String id;
    /**
     * 简历
     */
    private String resumeId;
    /**
     * 会员
     */
    private String memberId;

    private Industry industry;
    /**
     * 地点
     */
    private Area city;
    /**
     * 天数/周
     */
    private Integer days;
    /**
     * 实习开始月份
     */
    private Date beginMonth;
    /**
     * 实习结束月份
     */
    private Date endMonth;
    /**
     * 期望日薪
     */
    private Integer dailyWage;
    /**
     * 到岗日期
     */
    private Date arrivalDate;

    private Double months;

    @ApiModelProperty(value = "简历Id")
    public String getResumeId() {
        return resumeId;
    }

    @ApiModelProperty(value = "会员id")
    public String getMemberId() {
        return memberId;
    }

    @ApiModelProperty(value = "地点Id")
    public Area getCity() {
        return city;
    }

    @ApiModelProperty(value = "天数/周")
    public Integer getDays() {
        return days;
    }

    @ApiModelProperty(value = "实习开始月份")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginMonth() {
        return beginMonth;
    }

    @ApiModelProperty(value = "实习结束月份")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndMonth() {
        return endMonth;
    }

    @ApiModelProperty(value = "期望日薪")
    public Integer getDailyWage() {
        return dailyWage;
    }

    @ApiModelProperty(value = "到岗日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "实习月数")
    public Double getMonths() {
        return months;
    }

    public void setMonths(Double months) {
        this.months = months;
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

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setBeginMonth(Date beginMonth) {
        this.beginMonth = beginMonth;
    }

    public void setEndMonth(Date endMonth) {
        this.endMonth = endMonth;
    }

    public void setDailyWage(Integer dailyWage) {
        this.dailyWage = dailyWage;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
}
