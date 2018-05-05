package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/3/19 20:52
 */
public class StudentDto {
    private String id;
    private String name;
    private String userName;
    private Date createDate;

    @ApiModelProperty(value = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "电话号码")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ApiModelProperty(value = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
