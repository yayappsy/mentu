package com.weimhc.api.modules.dto.req.cadet;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/21 15:36
 */
public class JobSearchRQ{
    private String companyId;
    /**
     * 搜索名称
     */
    private String searchName;
    /**
     * 行业分类
     */
    private String industryId;
    /**
     * 工作地点
     */
    private String areaId;
    /**
     * 日薪最小值
     */
    private Double minDayMoney;
    /**
     * 日薪最大值
     */
    private Double maxDayMoney;
    /**
     * 实习天数
     */
    private Integer practiceDay;
    /**
     * 最小实习月数
     */
    private Integer minPracticeMonth;
    /**
     * 最大实习月数
     */
    private Integer maxPracticeMonth;
    /**
     * 最低学历
     */
    private Integer mixEducational;
    /**
     * 转正机会
     */
    private String isWorker;
    /**
     * 排序方式
     */
    private Integer sortWay;

    /**
     * 是否内推
     * @return
     */
    private String isPush;

    /**
     * 职位状态
     * @return
     */
    private String state;

    private String homeShow;

    @ApiModelProperty(value = "搜索名称", example = "java开发")
    public String getSearchName() {
        return searchName;
    }

    @ApiModelProperty(value = "行业分类", example = "软件")
    public String getIndustryId() {
        return industryId;
    }

    @ApiModelProperty(value = "工作地点", example = "北京")
    public String getAreaId() {
        return areaId;
    }

    @ApiModelProperty(value = "日薪最小值", example = "150.00")
    public Double getMinDayMoney() {
        return minDayMoney;
    }

    @ApiModelProperty(value = "日薪最大值", example = "300.00")
    public Double getMaxDayMoney() {
        return maxDayMoney;
    }

    @ApiModelProperty(value = "实习天数", example = "4")
    public Integer getPracticeDay() {
        return practiceDay;
    }

    @ApiModelProperty(value = "最低学历（0，大专 1，本科 2，硕士 3，博士）", example = "0")
    public Integer getMixEducational() {
        return mixEducational;
    }

    @ApiModelProperty(value = "转正机会（提供转正 yes,不提供转正 no,面议 negotiate）", example = "yes")
    public String getIsWorker() {
        return isWorker;
    }

    @ApiModelProperty(value = "排序方式（0，综合排序 1，最新发布 2，最具人气）", example = "0")
    public Integer getSortWay() {
        return sortWay;
    }

    @ApiModelProperty(value = "是否内推，0是 1不是", example = "0")
    public String getIsPush() {
        return isPush;
    }

    @ApiModelProperty(value = "公司id", example = "1")
    public String getCompanyId() {
        return companyId;
    }

    @ApiModelProperty(value = "职位状态（1，招聘中 2，已经下线 3，已过期 4审核中）", example = "1")
    public String getState() {
        return state;
    }

    @ApiModelProperty(value = "最小实习月数", example = "1")
    public Integer getMinPracticeMonth() {
        return minPracticeMonth;
    }

    @ApiModelProperty(value = "首页展示（1，热门，2，急招，3，推荐）", example = "1")
    public String getHomeShow() {
        return homeShow;
    }

    public void setHomeShow(String homeShow) {
        this.homeShow = homeShow;
    }

    public void setMinPracticeMonth(Integer minPracticeMonth) {
        this.minPracticeMonth = minPracticeMonth;
    }

    @ApiModelProperty(value = "最大实习月数", example = "1")
    public Integer getMaxPracticeMonth() {
        return maxPracticeMonth;
    }

    public void setMaxPracticeMonth(Integer maxPracticeMonth) {
        this.maxPracticeMonth = maxPracticeMonth;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setMinDayMoney(Double minDayMoney) {
        this.minDayMoney = minDayMoney;
    }

    public void setMaxDayMoney(Double maxDayMoney) {
        this.maxDayMoney = maxDayMoney;
    }

    public void setPracticeDay(Integer practiceDay) {
        this.practiceDay = practiceDay;
    }

    public void setMixEducational(Integer mixEducational) {
        this.mixEducational = mixEducational;
    }

    public void setIsWorker(String isWorker) {
        this.isWorker = isWorker;
    }

    public void setSortWay(Integer sortWay) {
        this.sortWay = sortWay;
    }
}
