package com.weimhc.api.modules.dto.req.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 11:37
 */
public class UserInfoRQ {
    private String id;//用户id

    private String resumeId;//  简历id

    private String name;//真实姓名

    private String gender;//性别

    private Date birth;//出生日期

    private Integer age;//年龄

    private String avatar;//头像

    private Date endStudy;//毕业时间

    private Region residence;//所在城市

    private String education;//最高学历

    private String school;//学校名称

    private String majorId;//专业

    private String mobile;//手机号

    private String email;//邮箱

    @ApiModelProperty(value = "用户id(不可为空)")
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "真实姓名")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "性别")
    public String getGender() {
        return gender;
    }

    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBirth() {
        return birth;
    }

    @ApiModelProperty(value = "简历id")
    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "毕业时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndStudy() {
        return endStudy;
    }

    @ApiModelProperty(value = "所在城市")
    public Region getResidence() {
        return residence;
    }

    @ApiModelProperty(value = "学历(0，大专 1，本科 2，硕士 3，博士)")
    public String getEducation() {
        return education;
    }


    @ApiModelProperty(value = "学校名称")
    public String getSchool() {
        return school;
    }

    @ApiModelProperty(value = "专业")
    public String getMajorId() {
        return majorId;
    }

    @ApiModelProperty(value = "手机号")
    public String getMobile() {
        return mobile;
    }

    @ApiModelProperty(value = "邮箱")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "年龄")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ApiModelProperty(value = "头像")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setEndStudy(Date endStudy) {
        this.endStudy = endStudy;
    }

    public void setResidence(Region residence) {
        this.residence = residence;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
