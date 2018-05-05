package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/26 10:20
 */
public class PathDto {
    private String existPath;
    private String urlPath;

    @ApiModelProperty(value = "物理存在路径")
    public String getExistPath() {
        return existPath;
    }

    public void setExistPath(String existPath) {
        this.existPath = existPath;
    }

    @ApiModelProperty(value = "请求URL")
    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
