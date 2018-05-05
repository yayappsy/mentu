package com.weimhc.api.modules.dto.req.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/9 21:50
 */
public class SendEmailQR {

    private String fromAlias;//发件人别名

    private String subject;//邮件标题

    private String email;//收件人邮箱

    private String htmlBody;//内容

    @ApiModelProperty(value = "发件人别名")
    public String getFromAlias() {
        return fromAlias;
    }

    @ApiModelProperty(value = "邮件标题")
    public String getSubject() {
        return subject;
    }

    @ApiModelProperty(value = "收件人邮箱")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "发信内容（富文本）")
    public String getHtmlBody() {
        return htmlBody;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFromAlias(String fromAlias) {
        this.fromAlias = fromAlias;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
