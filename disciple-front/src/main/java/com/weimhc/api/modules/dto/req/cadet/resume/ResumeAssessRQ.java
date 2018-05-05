package com.weimhc.api.modules.dto.req.cadet.resume;

import io.swagger.annotations.ApiModelProperty;

/**
 * 个人评价
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/7 18:28
 */
public class ResumeAssessRQ {
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
     * 个人评价
     */
    private String access;

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    @ApiModelProperty(value = "用户id")
    public String getMemberId() {
        return memberId;
    }

    @ApiModelProperty(value = "个人评价")
    public String getAccess() {
        return access;
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

    public void setAccess(String access) {
        this.access = access;
    }
}
