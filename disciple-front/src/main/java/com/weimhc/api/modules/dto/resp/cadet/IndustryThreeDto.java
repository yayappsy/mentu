package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/20 11:46
 */
public class IndustryThreeDto {
    /**
     * 行业id
     */
    private String id;
    /**
     * 行业名称
     */
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "三级行业名称", example ="C++")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "三级行业id", example = "3")
    public void setName(String name) {
        this.name = name;
    }

}
