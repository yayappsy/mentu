package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分类
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/20 11:46
 */
public class IndustryOneDto {
    /**
     * 行业id
     */
    private String id;
    /**
     * 行业名称
     */
    private String name;

    /**
     * 二级分类list
     *
     * @return
     */
    private List<IndustryTwoDto> industryTwoDtos;

    @ApiModelProperty(value = "一级行业id", example = "1")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "一级行业名称",example = "IT互联网")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "二级行业标签集合")
    public List<IndustryTwoDto> getIndustryTwoDtos() {
        return industryTwoDtos;
    }

    public void setIndustryTwoDtos(List<IndustryTwoDto> industryTwoDtos) {
        this.industryTwoDtos = industryTwoDtos;
    }
}
