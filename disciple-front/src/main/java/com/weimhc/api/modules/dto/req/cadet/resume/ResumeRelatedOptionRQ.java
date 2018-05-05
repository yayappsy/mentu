package com.weimhc.api.modules.dto.req.cadet.resume;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 附件
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/7 20:01
 */
public class ResumeRelatedOptionRQ {
    private String id;

    private String name;

    private String type;
    /**
     * 简历
     *
     */
    private String resumeId;
    /**
     * 会员
     *
     */
    private String memberId;
    /**
     * 附件
     *
     */
    private String options;


    /**
     * 获取简历
     *
     * @return 简历
     */
    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }
    /**
     * 设置简历
     *
     * @param resumeId
     *            简历
     */
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "类型(0相关附件，1附件简历)")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取会员

     *
     * @return 会员
     */
    @ApiModelProperty(value = "会员id")
    public String getMemberId() {
        return memberId;
    }
    /**
     * 设置会员
     *
     * @param memberId
     *            会员
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取附件
     *
     * @return 附件
     */
    @ApiModelProperty(value = "附件名称")
    public String getOptions() {
        return options;
    }
    /**
     * 设置附件
     *
     * @param options
     *            附件
     */
    public void setOptions(String options) {
        this.options = options;
    }

    @ApiModelProperty(value = "id有值做修改，id为空做新增")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "附件原始名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
