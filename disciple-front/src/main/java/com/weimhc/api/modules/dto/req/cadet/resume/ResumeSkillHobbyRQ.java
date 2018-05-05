package com.weimhc.api.modules.dto.req.cadet.resume;

import io.swagger.annotations.ApiModelProperty;

/**
 * 技能爱好
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 15:04
 */
public class ResumeSkillHobbyRQ {
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
     * 技能类型
     */
    private String type;
    /**
     * 技能说明
     */
    private String explain;

    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    @ApiModelProperty(value = "会员id")
    public String getMemberId() {
        return memberId;
    }

    @ApiModelProperty(value = "技能类型")
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "技能说明")
    public String getExplain() {
        return explain;
    }

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
