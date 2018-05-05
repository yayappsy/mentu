package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/6 15:01
 */
public class ResumeDto {

    private String userId;

    private String resumeId;

    private String type;//类型

    private Date newDate;//最新修改时间

    private Integer moduleNum;//已完成的模块数

    private String name;//简历名称

    private String language;//语言

    @ApiModelProperty(value = "用户id", example = "1")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @ApiModelProperty(value = "简历id", example = "axde-05fvdg-fsvsfkoj")
    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "简历类型（0:默认简历,1:附件简历）",example = "0")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(value = "最新修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    @ApiModelProperty(value = "已经完成法模块数量")
    public Integer getModuleNum() {
        return moduleNum;
    }

    public void setModuleNum(Integer moduleNum) {
        this.moduleNum = moduleNum;
    }

    @ApiModelProperty(value = "简历名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "简历语言（english:英文,chinese:中文）")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
