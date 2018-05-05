/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.javamg.common.persistence.DataEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程培训表Entity
 * @author cwl
 * @version 2017-12-31
 */
public class Course extends DataEntity<Course>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 行业标签id
	 * 
	 */		
	private String courseLabelId;
	/**
	 * 课程名称
	 *
	 */
	private String name;
	/**
	 * 0小班，1中班，2大班
	 *
	 */
	private Integer size;

	private CourseLabel courseLabel;
	/**
	 * 开始时间
	 *
	 */
	private Date startDate;
	/**
	 * 结束时间
	 *
	 */
	private Date endDate;
	/**
	 * 内容
	 *
	 */
	private String content;
	/**
	 * 学到内容
	 *
	 */
	private String learn;
	/**
	 * 行业名称
	 *
	 */
	private String industryName;
	/**
	 * 二维码
	 *
	 */
	private String qrCode;
	/**
	 * 适合人群
	 *
	 */
	private String fitPeople;
	/**
	 * 简介
	 *
	 */
	private String intro;
	/**
	 * 授课导师
	 *
	 */
	private String teacher;
	/**
	 * 价格
	 *
	 */
	private String price;
	/**
	 * email
	 *
	 */
	private String email;
	/**
	 * 地址
	 *
	 */
	private String address;
	/**
	 * 是否滚动（0，不滚动 1，滚动）
	 *
	 */
	private String isRoll;
	/**
	 * 背景图片
	 *
	 */
	private String backImage;
	private String beginPrice;		// 开始 价格
	private String endPrice;		// 结束 价格

	private MultipartFile qrCodeFile;
	private MultipartFile backImageFile;


	public Course() {
		super();
	}

	public Course(String id){
		super(id);
	}

	/**
	 * 获取课程名称
	 * 
	 * @return 课程名称
	 */	
	@Length(min=0, max=64)
	public String getName() {
		return name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFitPeople() {
		return fitPeople;
	}

	public void setFitPeople(String fitPeople) {
		this.fitPeople = fitPeople;
	}

	public CourseLabel getCourseLabel() {
		return courseLabel;
	}

	public void setCourseLabel(CourseLabel courseLabel) {
		this.courseLabel = courseLabel;
	}

	/**
	 * 设置课程名称

	 * 
	 * @param name
	 *            课程名称

	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取行业标签id
	 * 
	 * @return 行业标签id
	 */	
	@Length(min=0, max=64)
	public String getCourseLabelId() {
		return courseLabelId;
	}
	/**
	 * 设置行业标签id
	 * 
	 * @param courseLabelId
	 *            行业标签id
	 */
	public void setCourseLabelId(String courseLabelId) {
		this.courseLabelId = courseLabelId;
	}
	
	/**
	 * 获取0小班，1中班，2大班
	 * 
	 * @return 0小班，1中班，2大班
	 */	
	public Integer getSize() {
		return size;
	}
	/**
	 * 设置0小班，1中班，2大班
	 * 
	 * @param size
	 *            0小班，1中班，2大班
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	
	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置开始时间
	 * 
	 * @param startDate
	 *            开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置结束时间
	 * 
	 * @param endDate
	 *            结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */	
	public String getContent() {
		return content;
	}
	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取学到内容
	 * 
	 * @return 学到内容
	 */	
	public String getLearn() {
		return learn;
	}
	/**
	 * 设置学到内容
	 * 
	 * @param learn
	 *            学到内容
	 */
	public void setLearn(String learn) {
		this.learn = learn;
	}
	
	/**
	 * 获取二维码
	 * 
	 * @return 二维码
	 */	
	public String getQrCode() {
		return qrCode;
	}
	/**
	 * 设置二维码
	 * 
	 * @param qrCode
	 *            二维码
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	/**
	 * 获取价格
	 * 
	 * @return 价格
	 */	
	@Length(min=0, max=64)
	public String getPrice() {
		return price;
	}
	/**
	 * 设置价格
	 * 
	 * @param price
	 *            价格
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 获取email
	 * 
	 * @return email
	 */	
	@Length(min=0, max=64)
	public String getEmail() {
		return email;
	}
	/**
	 * 设置email
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */	
	@Length(min=1, max=64)
	public String getAddress() {
		return address;
	}
	/**
	 * 设置地址
	 * 
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取是否滚动（0，不滚动 1，滚动）
	 * 
	 * @return 是否滚动（0，不滚动 1，滚动）
	 */	
	@Length(min=0, max=1)
	public String getIsRoll() {
		return isRoll;
	}
	/**
	 * 设置是否滚动（0，不滚动 1，滚动）
	 * 
	 * @param isRoll
	 *            是否滚动（0，不滚动 1，滚动）
	 */
	public void setIsRoll(String isRoll) {
		this.isRoll = isRoll;
	}
	
	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 */	
	public String getBackImage() {
		return backImage;
	}
	/**
	 * 设置背景图片
	 * 
	 * @param backImage
	 *            背景图片
	 */
	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(String beginPrice) {
		this.beginPrice = beginPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public MultipartFile getQrCodeFile() {
		return qrCodeFile;
	}

	public void setQrCodeFile(MultipartFile qrCodeFile) {
		this.qrCodeFile = qrCodeFile;
	}

	public MultipartFile getBackImageFile() {
		return backImageFile;
	}

	public void setBackImageFile(MultipartFile backImageFile) {
		this.backImageFile = backImageFile;
	}
}