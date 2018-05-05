/**
 *
 */
package com.weimhc.modules.job.entity;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.utils.Collections3;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.industry.entity.Industry;
import com.weimhc.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 职位Entity
 *
 * @author lc
 * @version 2017-11-15
 */
public class Job extends DataEntity<Job> {

    private static final long serialVersionUID = 1L;
    private Company company;            // 企业编号
    private Industry industry;            // 职位类别
    private String name;                // 职位名称
    private Area city;                    // 工作城市
    private Integer practiceNumber;        // 实习人数
    private String jobAttract;            // 职位诱惑
    private String description;            // 职位描述
    private String address;                // 工作地址
    private String lowest;                // 最低日薪
    private String highest;                // 最高日薪
    private Integer dayNumber;            // 每周天数
    private String language;            // 简历语言
    private String education;            // 学历要求
    private Date beginDate;                // 实习开始月份
    private Date endDate;                // 实习结束月份
    private Date expiryDate;            // 截止日期
    private String chance;                // 转正机会
    private String isOverseas;            // 是否倾向海外留学生
    private String isResume;            // 是否接受投递简历
    private String email;                // 接收邮箱
    private Integer number;                // 申请情况
    private String state;                // 职位状态
    private String isPush;                // 是否内推
    private String isHit;                // 是否热销
    private String comment;                // 平台评论
    private String practiceMonth;        //实习月份
    private String searchName;            //搜索名称
    private String ids;        //职业ids
    private Integer minPracticeMonth;//最小实习月数
    private Integer maxPracticeMonth;//最大实习月数
    private String qrCode;
    private String companyPushId;
    private String homeShow;

    private MultipartFile qrCodeFile;

    private List<JobLabel> jobLabelList = Lists.newArrayList(); //拥有的职位标签列表
    private JobLabel jobLabel; //职业标签

    public Job() {
        super();
    }

    public Job(String id) {
        super(id);
    }

    public String getHomeShow() {
        return homeShow;
    }

    public void setHomeShow(String homeShow) {
        this.homeShow = homeShow;
    }

    @NotNull()
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @NotNull()
    public Industry getIndustry() {
        return industry;
    }

    public String getCompanyPushId() {
        return companyPushId;
    }

    public void setCompanyPushId(String companyPushId) {
        this.companyPushId = companyPushId;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    @Length(min = 1, max = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull()
    public Area getCity() {
        return city;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public String getPracticeMonth() {
        return practiceMonth;
    }

    public void setPracticeMonth(String practiceMonth) {
        this.practiceMonth = practiceMonth;
    }

    public MultipartFile getQrCodeFile() {
        return qrCodeFile;
    }

    public void setQrCodeFile(MultipartFile qrCodeFile) {
        this.qrCodeFile = qrCodeFile;
    }

    @NotNull()

    public Integer getPracticeNumber() {
        return practiceNumber;
    }

    public void setPracticeNumber(Integer practiceNumber) {
        this.practiceNumber = practiceNumber;
    }

    @Length(min = 1, max = 60)
    public String getJobAttract() {
        return jobAttract;
    }

    public void setJobAttract(String jobAttract) {
        this.jobAttract = jobAttract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 0, max = 64)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @JsonFormat(pattern = "yyyy-MM")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonFormat(pattern = "yyyy-MM")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Length(min = 0, max = 100)
    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    @Length(min = 1, max = 1)
    public String getIsOverseas() {
        return isOverseas;
    }

    public void setIsOverseas(String isOverseas) {
        this.isOverseas = isOverseas;
    }

    @Length(min = 1, max = 1)
    public String getIsResume() {
        return isResume;
    }

    public void setIsResume(String isResume) {
        this.isResume = isResume;
    }

    @Length(min = 1, max = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Length(min = 0, max = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Length(min = 0, max = 1)
    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }

    public Integer getMinPracticeMonth() {
        return minPracticeMonth;
    }

    public void setMinPracticeMonth(Integer minPracticeMonth) {
        this.minPracticeMonth = minPracticeMonth;
    }

    public Integer getMaxPracticeMonth() {
        return maxPracticeMonth;
    }

    public void setMaxPracticeMonth(Integer maxPracticeMonth) {
        this.maxPracticeMonth = maxPracticeMonth;
    }

    @Length(min = 0, max = 255)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取拥有的职位标签列表
     *
     * @return the jobLabelList
     */
    public List<JobLabel> getJobLabelList() {
        return jobLabelList;
    }

    /**
     * 设置拥有的职位标签列表
     *
     * @param jobLabelList the jobLabelList to set
     */
    public void setJobLabelList(List<JobLabel> jobLabelList) {
        this.jobLabelList = jobLabelList;
    }

    /**
     * 获取职位标签
     *
     * @return the jobLabel
     */
    public JobLabel getJobLabel() {
        return jobLabel;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 设置职位标签
     *
     * @param jobLabel the jobLabel to set
     */
    public void setJobLabel(JobLabel jobLabel) {
        this.jobLabel = jobLabel;
    }

    @JsonIgnore
    public List<String> getJobLabelIdList() {
        List<String> jobLabelIdList = Lists.newArrayList();
        for (JobLabel jobLabel : jobLabelList) {
            jobLabelIdList.add(jobLabel.getId());
        }
        return jobLabelIdList;
    }

    public void setJobLabelIdList(List<String> jobLabelIdList) {
        jobLabelList = Lists.newArrayList();
        for (String jobLabelId : jobLabelIdList) {
            JobLabel jobLabel = new JobLabel();
            jobLabel.setId(jobLabelId);
            jobLabelList.add(jobLabel);
        }
    }

    /**
     * 用户拥有的企业标签名称字符串, 多个角色名称用','分隔.
     */
    public String getJobLabelNames() {
        return Collections3.extractToString(jobLabelList, "name", ",");
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}