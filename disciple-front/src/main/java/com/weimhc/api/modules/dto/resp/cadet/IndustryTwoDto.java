package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分类
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/20 11:46
 */
public class IndustryTwoDto {
    /**
     * 行业id
     */
    private String id;
    /**
     * 行业名称
     */
    private String name;

    /**
     * 三级分类list
     *
     * @return
     */
    private List<IndustryThreeDto> industryThreeDtos;

    @ApiModelProperty(value = "二级行业id", example = "2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "二级行业名称",example = "软件")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "三级行业标签集合")
    public List<IndustryThreeDto> getIndustryThreeDtos() {
        return industryThreeDtos;
    }

    public void setIndustryThreeDtos(List<IndustryThreeDto> industryThreeDtos) {
        this.industryThreeDtos = industryThreeDtos;
    }
}
