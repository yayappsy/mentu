package com.weimhc.api.modules.dto.req.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/01/04 09:05
 */
public class CompanyRQ {
    private String id;

    private String userInfoId;

    private String name;        // 企业名称

    private String shortName;        // 企业简称

    private String site;        // 公司网址

    private String phone;   //联系电话

    private String email;   //企业邮箱

    private String areaId;    //区域

    private String industryId;  //行业id

    private String size;        // 公司规模

    private String introduce;        // 企业简介

    private String mainBusinessDescription;        // 一句话介绍自己

    private String companyLabels;   //企业标签

    private String logo;    //企业Logo


    @ApiModelProperty(value = "企业名称", example = "企业名称")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "企业简称", example = "企业简称")
    public String getShortName() {
        return shortName;
    }


    @ApiModelProperty(value = "公司网址", example = "公司网址")
    public String getSite() {
        return site;
    }

    @ApiModelProperty(value = "企业登录用户id", example = "153126500969881600")
    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    @ApiModelProperty(value = "企业简介", example = "企业简介")
    public String getIntroduce() {
        return introduce;
    }

    @ApiModelProperty(value = "公司规模", example = "500-1000")
    public String getSize() {
        return size;
    }

    @ApiModelProperty(value = "一句话介绍自己", example = "人力外包 | 人才培养")
    public String getMainBusinessDescription() {
        return mainBusinessDescription;
    }

    @ApiModelProperty(value = "区域Id", example = "000001")
    public String getAreaId() {
        return areaId;
    }

    @ApiModelProperty(value = "联系电话", example = "1111111111")
    public String getPhone() {
        return phone;
    }

    @ApiModelProperty(value = "企业邮箱", example = "1@c.com")
    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "行业id", example = "ac5")
    public String getIndustryId() {
        return industryId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @ApiModelProperty(value = "公司标签（中间用空格隔开）", example = "年假 五险一金 员工福利")
    public String getCompanyLabels() {
        return companyLabels;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public void setCompanyLabels(String companyLabels) {
        this.companyLabels = companyLabels;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public void setSite(String site) {
        this.site = site;
    }


    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMainBusinessDescription(String mainBusinessDescription) {
        this.mainBusinessDescription = mainBusinessDescription;
    }
}
