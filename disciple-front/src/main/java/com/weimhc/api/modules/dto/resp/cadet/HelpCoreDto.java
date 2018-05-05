package com.weimhc.api.modules.dto.resp.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * 帮助中心
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 11:16
 */
public class HelpCoreDto {

    private String question;

    private String answer;

    @ApiModelProperty(value = "问题")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @ApiModelProperty(value = "答案")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
