package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/22 14:39
 */
public class CommunicationDto {
    /**
     * 发送id
     *
     */
    private String sendId;
    /**
     * 接收id
     *
     */
    private String receiveId;
    /**
     * 内容
     *
     */
    private String content;

    /**
     * @return
     */
    private Date createDate;

    /**
     * 是否已读（0未读，1已读）
     *
     */
    private String isRead;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ApiModelProperty(value = "是否已读（0未读，1已读）", example = "1")
    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
