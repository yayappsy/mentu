package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 10:51
 */
public class CourseDto {
    private String name;//课程名称
    private String size;//0小班，1中班，2大班
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private String content;//内容
    private String learn;//学到内容
    private String qrCode;//二维码
    private String price;//价格
    private String email;//
    private String address;//地址
    private String isRoll;//是否滚动（0，不滚动 1，滚动）
    private String backImage;//背景图片
    private String teacher;//授课导师
    private String intro;//简介
    private String fitPeople;//适合人群
    private String industryName;//行业名称

    @ApiModelProperty(value = "课程名称")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "0小班，1中班，2大班", example = "0")
    public String getSize() {
        return size;
    }

    @ApiModelProperty(value = "开始时间", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getStartDate() {
        return startDate;
    }

    @ApiModelProperty(value = "结束时间", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "内容", example = "")
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "学到内容", example = "")
    public String getLearn() {
        return learn;
    }

    @ApiModelProperty(value = "二维码", example = "")
    public String getQrCode() {
        return qrCode;
    }

    @ApiModelProperty(value = "价格", example = "")
    public String getPrice() {
        return price;
    }

    @ApiModelProperty(value = "email", example = "")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "地址", example = "")
    public String getAddress() {
        return address;
    }

    @ApiModelProperty(value = "是否滚动（0，不滚动 1，滚动）", example = "1")
    public String getIsRoll() {
        return isRoll;
    }

    @ApiModelProperty(value = "背景图片", example = "")
    public String getBackImage() {
        return backImage;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getFitPeople() {
        return fitPeople;

    }

    public void setFitPeople(String fitPeople) {
        this.fitPeople = fitPeople;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLearn(String learn) {
        this.learn = learn;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsRoll(String isRoll) {
        this.isRoll = isRoll;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }
}
