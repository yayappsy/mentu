package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * 热门城市
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/24 21:29
 */
public class HitCityDto {
    private String id;
    private String name;

    @ApiModelProperty(value = "热门城市Id", example = "1")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "热门城市名字", example = "北京")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
