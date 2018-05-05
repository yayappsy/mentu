package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 11:08
 */
public class CourseLabelDto {
    private String id;

    private String name;

    @ApiModelProperty(value = "课程标签id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @ApiModelProperty(value = "课程标签名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
