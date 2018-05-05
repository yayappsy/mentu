package com.weimhc.api.modules.dto.req.cadet.resume;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/8 23:04
 */
public class CompanyContactRQ {
    private String id;
    /**
     * 企业id
     *
     */
    private String companyId;
    /**
     * 主题
     *
     */
    private String title;
    /**
     * 面试地点
     *
     */
    private String place;
    /**
     * 联系人
     *
     */
    private String linkman;
    /**
     * 联系电话
     *
     */
    private String linkphone;
    /**
     * 补充
     *
     */
    private String supply;

    @ApiModelProperty(value = "有值有修改，无值为添加")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "企业id")
    public String getCompanyId() {
        return companyId;
    }

    @ApiModelProperty(value = "主题")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "面试地点")
    public String getPlace() {
        return place;
    }

    @ApiModelProperty(value = "联系人")
    public String getLinkman() {
        return linkman;
    }

    @ApiModelProperty(value = "联系电话")
    public String getLinkphone() {
        return linkphone;
    }

    @ApiModelProperty(value = "补充")
    public String getSupply() {
        return supply;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }
}
