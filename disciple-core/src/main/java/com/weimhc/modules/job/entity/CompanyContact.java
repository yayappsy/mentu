/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 公司面试邀请模板Entity
 * @author cwl
 * @version 2018-01-08
 */
public class CompanyContact extends DataEntity<CompanyContact>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业id
	 * 
	 */		
	private String companyId;	
	/**
	 * 主题
	 * 
	 */		
	private String title;	
	/**
	 * 面试地点
	 * 
	 */		
	private String place;	
	/**
	 * 联系人
	 * 
	 */		
	private String linkman;	
	/**
	 * 联系电话
	 * 
	 */		
	private String linkphone;	
	/**
	 * 补充
	 * 
	 */		
	private String supply;	
	
	public CompanyContact() {
		super();
	}

	public CompanyContact(String id){
		super(id);
	}

	/**
	 * 获取企业id
	 * 
	 * @return 企业id
	 */	
	@Length(min=0, max=64)
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置企业id
	 * 
	 * @param companyId
	 *            企业id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	/**
	 * 获取主题
	 * 
	 * @return 主题
	 */	
	@Length(min=0, max=64)
	public String getTitle() {
		return title;
	}
	/**
	 * 设置主题
	 * 
	 * @param title
	 *            主题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取面试地点
	 * 
	 * @return 面试地点
	 */	
	@Length(min=0, max=64)
	public String getPlace() {
		return place;
	}
	/**
	 * 设置面试地点
	 * 
	 * @param place
	 *            面试地点
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * 获取联系人
	 * 
	 * @return 联系人
	 */	
	@Length(min=1, max=255)
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置联系人
	 * 
	 * @param linkman
	 *            联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */	
	@Length(min=0, max=255)
	public String getLinkphone() {
		return linkphone;
	}
	/**
	 * 设置联系电话
	 * 
	 * @param linkphone
	 *            联系电话
	 */
	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}
	
	/**
	 * 获取补充
	 * 
	 * @return 补充
	 */	
	@Length(min=0, max=255)
	public String getSupply() {
		return supply;
	}
	/**
	 * 设置补充
	 * 
	 * @param supply
	 *            补充
	 */
	public void setSupply(String supply) {
		this.supply = supply;
	}
	
}