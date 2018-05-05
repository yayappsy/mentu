package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/23 18:32
 */
public class MyFileDto {
    private String fileName;
    private String realPath;
    private String originalFilename;

    @ApiModelProperty(value = "文件名")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @ApiModelProperty(value = "访问路径")
    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    @ApiModelProperty(value = "原始文件名(仅展示用)")
    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

}
