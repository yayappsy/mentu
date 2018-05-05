/**
 * 
 */
package com.weimhc.modules.remind.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 提醒模板Entity
 * 
 * @author zsm
 * @version 2017-03-23
 */
public class RemindTemplate extends DataEntity<RemindTemplate> {

	private static final long serialVersionUID = 1L;
	/**
	 * 提醒
	 * 
	 */
	private Remind remind;
	/**
	 * 提醒方式
	 * 
	 */
	private RemindWay remindWay;
	/**
	 * 收件人
	 * 
	 */
	private String addressee;
	/**
	 * 展示标题
	 * 
	 */
	private String displayTitle;
	/**
	 * 实际使用标题
	 * 
	 */
	private String actualTitle;
	/**
	 * 展示标题
	 * 
	 */
	private String displayContent;
	/**
	 * 实际使用的内容
	 * 
	 */
	private String actualContent;

	/**
	 * 是否启用
	 */
	private Boolean ifEnabled;

	public RemindTemplate() {
		super();
	}

	public RemindTemplate(String id) {
		super(id);
	}

	public RemindTemplate(Remind remind) {
		this.remind = remind;
	}

	/**
	 * 获取提醒
	 * 
	 * @return 提醒
	 */
	@NotNull
	public Remind getRemind() {
		return remind;
	}

	/**
	 * 设置提醒
	 * 
	 * @param remind
	 *            提醒
	 */
	public void setRemind(Remind remind) {
		this.remind = remind;
	}

	/**
	 * 获取提醒方式
	 * 
	 * @return 提醒方式
	 */
	public RemindWay getRemindWay() {
		return remindWay;
	}

	/**
	 * 设置提醒方式
	 * 
	 * @param remindWay
	 *            提醒方式
	 */
	public void setRemindWay(RemindWay remindWay) {
		this.remindWay = remindWay;
	}

	/**
	 * 获取收件人
	 * 
	 * @return 收件人
	 */
	@Length(min = 0, max = 255)
	public String getAddressee() {
		return addressee;
	}

	/**
	 * 设置收件人
	 * 
	 * @param addressee
	 *            收件人
	 */
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	/**
	 * 获取展示标题
	 * 
	 * @return 展示标题
	 */
	@Length(min = 0, max = 255)
	public String getDisplayTitle() {
		return displayTitle;
	}

	/**
	 * 设置展示标题
	 * 
	 * @param displayTitle
	 *            展示标题
	 */
	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

	/**
	 * 获取实际使用标题
	 * 
	 * @return 实际使用标题
	 */
	@Length(min = 0, max = 255)
	public String getActualTitle() {
		return actualTitle;
	}

	/**
	 * 设置实际使用标题
	 * 
	 * @param actualTitle
	 *            实际使用标题
	 */
	public void setActualTitle(String actualTitle) {
		this.actualTitle = actualTitle;
	}

	/**
	 * 获取展示标题
	 * 
	 * @return 展示标题
	 */
	public String getDisplayContent() {
		return displayContent;
	}

	/**
	 * 设置展示标题
	 * 
	 * @param displayContent
	 *            展示标题
	 */
	public void setDisplayContent(String displayContent) {
		this.displayContent = displayContent;
	}

	/**
	 * 获取实际使用的内容
	 * 
	 * @return 实际使用的内容
	 */
	public String getActualContent() {
		return actualContent;
	}

	/**
	 * 设置实际使用的内容
	 * 
	 * @param actualContent
	 *            实际使用的内容
	 */
	public void setActualContent(String actualContent) {
		this.actualContent = actualContent;
	}

	/**
	 * 返回 是否启用
	 * 
	 * @return the ifEnabled
	 */
	public Boolean getIfEnabled() {
		return ifEnabled;
	}

	/**
	 * 设置 是否启用
	 * 
	 * @param ifEnabled
	 *            the ifEnabled to set
	 */
	public void setIfEnabled(Boolean ifEnabled) {
		this.ifEnabled = ifEnabled;
	}

}