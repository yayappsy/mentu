/**
 * 
 */
package com.weimhc.modules.user.entity;

import com.weimhc.modules.job.entity.EducationBackground;
import com.weimhc.modules.sys.entity.Area;

import java.util.Date;

/**
 * 用户信息Entity-用于实际更新
 * 
 * @author lc
 * @version 2017-01-04
 */
public class UserInfo extends AbstractUserInfo<UserInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5548734366145066825L;

	private EducationBackground educationBackground;

	private String type;//用户类型：（1，普通用户，2企业用户）

	private Integer age;//年龄

	private Date startCreateDate;

	private Date endCreateDate;

	public EducationBackground getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(EducationBackground educationBackground) {
		this.educationBackground = educationBackground;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserInfo() {
		super();
	}

	public UserInfo(String id) {
		super(id);
	}

	public String getType() {
		return type;
	}

	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserInfo(String id, String username) {
		super(id, username);
	}

}