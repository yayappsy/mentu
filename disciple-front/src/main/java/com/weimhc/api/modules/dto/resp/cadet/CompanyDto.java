package com.weimhc.api.modules.dto.resp.cadet;

import com.weimhc.modules.base.entity.BaseIndustry;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/21 17:27
 */
public class CompanyDto {
    private String id;          //企业id

    private String name;		// 企业名称

    private String shortName;		// 企业简称

    private String address;     //公司地址

    private Area area;    //区域

    private String site;		// 公司网址

    private String introduce;		// 企业简介

    private String size;		// 公司规模

    private String mainBusinessDescription;		// 主营业务描述

    private String logo;		// 企业logo

    private Industry industry;     //行业信息

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "企业id", example = "企业id")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "企业名称", example = "企业名称")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "企业简称", example = "企业简称")
    public String getShortName() {
        return shortName;
    }

    @ApiModelProperty(value = "公司地址", example = "公司地址")
    public String getAddress() {
        return address;
    }

    @ApiModelProperty(value = "公司网址", example = "公司网址")
    public String getSite() {
        return site;
    }

    @ApiModelProperty(value = "企业简介", example = "企业简介")
    public String getIntroduce() {
        return introduce;
    }

    @ApiModelProperty(value = "公司规模", example = "500-1000")
    public String getSize() {
        return size;
    }

    @ApiModelProperty(value = "主营业务描述", example = "人力外包 | 人才培养")
    public String getMainBusinessDescription() {
        return mainBusinessDescription;
    }
    @ApiModelProperty(value = "企业LOGO", example = "url://...")
    public String getLogo() {
        return logo;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setAddress(String address) {
        this.address = address;
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
