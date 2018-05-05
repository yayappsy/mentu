package com.weimhc.api.modules.dto.resp.cadet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.sys.entity.Area;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/21 15:59
 */
public class JobDto{

    private String id;
    private Integer number;
    private CompanyDto companyDto;		// 企业编号
    private String jobLabel;		// 职位类别
    private String name;		// 职位名称
    private Area area;		// 工作城市
    private Integer practiceNumber;		// 实习人数
    private String jobAttract;		// 职位诱惑
    private String description;		// 职位描述
    private String address;		// 工作地址
    private String lowest;		// 最低日薪
    private String highest;		// 最高日薪
    private String dayNumber;		// 每周天数
    private String education;		// 学历要求
    private Date beginDate;		// 实习开始月份
    private Date endDate;		// 实习结束月份
    private Date expiryDate;		// 截止日期
    private String email;		// 接收邮箱
    private String comment;        //平台评论
    private String practiceMonth;
    private Date updateTime;
    private String isPush;
    private String qrCode;
    private Industry industry;

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    @ApiModelProperty(value = "投递数量")
    public Integer getNumber() {
        return number;
    }

    @ApiModelProperty(value = "职位类别", example = "软件")
    public String getJobLabel() {
        return jobLabel;
    }

    @ApiModelProperty(value = "职位名称", example = "java开发工程师")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "城市", example = "北京")
    public Area getArea() {
        return area;
    }

    @ApiModelProperty(value = "实习人数", example = "4")
    public Integer getPracticeNumber() {
        return practiceNumber;
    }

    @ApiModelProperty(value = "职位诱惑", example = "平台大，环境好，五险一金，节日福利")
    public String getJobAttract() {
        return jobAttract;
    }

    @ApiModelProperty(value = "职位描述", example = "    任职要求：\n" +
            "            1、计算机相关专业专科学历，2年以上工作经验；\n" +
            "            2、熟练掌握JavaEE技术：Struts2，Spring，hibernate/Ibatis；\n" +
            "            3、熟悉Java应用服务器开发，配置，管理工作，如：tomcat，jboss；\n" +
            "            4、熟练掌握Oracle，MySQL等数据库产品；\n" +
            "            5、熟练使用eclipse，SVN等开发环境。\n" +
            "\n" +
            "    岗位职责：\n" +
            "            1、参与各产品的需求分析、规划、系统设计；\n" +
            "            2、负责各应用模块的编码工作和文档编写工作；\n" +
            "            3、完成目标产品的调试和测试工作。")
    public String getDescription() {
        return description;
    }


    @ApiModelProperty(value = "工作地址", example = "北京海淀区中关村软件园")
    public String getAddress() {
        return address;
    }

    @ApiModelProperty(value = "最低日薪", example = "150.00")
    public String getLowest() {
        return lowest;
    }

    @ApiModelProperty(value = "是否内推（0是 1不是）", example = "1")
    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }

    @ApiModelProperty(value = "二维码名称", example = "c2368bc4-eba4-4d4c-b8b2-b7d6da20db79.png")
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @ApiModelProperty(value = "最高日薪", example = "300.00")
    public String getHighest() {
        return highest;
    }

    @ApiModelProperty(value = "每周天数", example = "4")
    public String getDayNumber() {
        return dayNumber;
    }

    @ApiModelProperty(value = "学历要求", example = "本科")
    public String getEducation() {
        return education;
    }

    @ApiModelProperty(value = "实习开始月份", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    @ApiModelProperty(value = "更新时间", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @ApiModelProperty(value = "实习结束月份", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    @ApiModelProperty(value = "截止日期", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getExpiryDate() {
        return expiryDate;
    }

    @ApiModelProperty(value = "接收邮箱", example = "gongzuo@sina.com")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "公司信息")
    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    @ApiModelProperty(value = "平台评论")
    public String getComment() {
        return comment;
    }

    @ApiModelProperty(value = "实习月数")
    public String getPracticeMonth() {
        return practiceMonth;
    }

    public void setPracticeMonth(String practiceMonth) {
        this.practiceMonth = practiceMonth;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }



    public void setJobLabel(String jobLabel) {
        this.jobLabel = jobLabel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setPracticeNumber(Integer practiceNumber) {
        this.practiceNumber = practiceNumber;
    }

    public void setJobAttract(String jobAttract) {
        this.jobAttract = jobAttract;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
