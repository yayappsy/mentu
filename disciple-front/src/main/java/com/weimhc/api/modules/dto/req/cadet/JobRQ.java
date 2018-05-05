package com.weimhc.api.modules.dto.req.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/24 21:00
 */
public class JobRQ {

    private String companyId;        // 企业Id
    private String qrCode;        // 企业Id
    private String companyPushId;        // 企业Id
    private String industryId;        // 职位类别Id
    private String name;        // 职位名称
    private Area area;        // 工作城市Id
    private Integer practiceNumber;        // 实习人数
    private String jobAttract;        // 职位诱惑
    private String description;        // 职位描述
    private String address;        // 工作地址
    private String lowest;        // 最低日薪
    private String highest;        // 最高日薪
    private Integer dayNumber;        // 每周天数
    private String language;        // 简历语言
    private String education;        // 学历要求
    private Date beginDate;        // 实习开始月份
    private Date endDate;        // 实习结束月份
    private Date expiryDate;        // 截止日期
    private String chance;        // 转正机会
    private String isOverseas;        // 是否倾向海外留学生
    private String isResume;        // 是否接受投递简历
    private String email;        // 接收邮箱

    private String labels;       //职业标签
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "企业Id", example = "1")
    public String getCompanyId() {
        return companyId;
    }

    @ApiModelProperty(value = "职位类别Id", example = "1")
    public String getIndustryId() {
        return industryId;
    }

    @ApiModelProperty(value = "职位名称", example = "java")
    public String getName() {
        return name;
    }



    @ApiModelProperty(value = "实习人数", example = "1")
    public Integer getPracticeNumber() {
        return practiceNumber;
    }

    @ApiModelProperty(value = "职位诱惑", example = "1")
    public String getJobAttract() {
        return jobAttract;
    }

    @ApiModelProperty(value = "职位描述", example = "1")
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "工作地址", example = "1")
    public String getAddress() {
        return address;
    }

    @ApiModelProperty(value = "最低日薪", example = "1")
    public String getLowest() {
        return lowest;
    }

    @ApiModelProperty(value = "最高日薪", example = "1")
    public String getHighest() {
        return highest;
    }

    @ApiModelProperty(value = "每周天数", example = "1")
    public Integer getDayNumber() {
        return dayNumber;
    }

    @ApiModelProperty(value = "简历语言\n中文\t\tchinese,\n" +
            "\t\t/**\n" +
            "\t\t * 英文\n" +
            "\t\t */\n" +
            "\t\tenglish;", example = "chinese")
    public String getLanguage() {
        return language;
    }

    @ApiModelProperty(value = "二维码", example = "fesfefsefefeflsflei")
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @ApiModelProperty(value = "学历要求(0，大专 1，本科 2，硕士 3，博士)", example = "1")
    public String getEducation() {
        return education;
    }

    @ApiModelProperty(value = "实习开始月份", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    @ApiModelProperty(value = "实习结束月份", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "截止日期", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getExpiryDate() {
        return expiryDate;
    }

    @ApiModelProperty(value = "转正机会（提供转正 yes,不提供转正 no,面议 negotiate）", example = "yes")
    public String getChance() {
        return chance;
    }

    @ApiModelProperty(value = "是否倾向海外留学生(0不是，1是)", example = "1")
    public String getIsOverseas() {
        return isOverseas;
    }

    @ApiModelProperty(value = "是否接受投递简历(0不是，1是)", example = "1")
    public String getIsResume() {
        return isResume;
    }

    @ApiModelProperty(value = "接收邮箱", example = "1@q.com")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "职业标签", example = "软件,软件开发,系统开发,后台开发")
    public String getLabels() {
        return labels;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPracticeNumber(Integer practiceNumber) {
        this.practiceNumber = practiceNumber;
    }

    public void setJobAttract(String jobAttract) {
        this.jobAttract = jobAttract;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public void setIsOverseas(String isOverseas) {
        this.isOverseas = isOverseas;
    }

    public void setIsResume(String isResume) {
        this.isResume = isResume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCompanyPushId() {
        return companyPushId;
    }

    @ApiModelProperty(value = "内推企业id", example = "00000000000001")
    public void setCompanyPushId(String companyPushId) {
        this.companyPushId = companyPushId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
