/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 在线客服Entity
 * 
 * @author lc
 * @version 2017-07-03
 */
public class OnlineCustomerService
		extends SortableEntity<OnlineCustomerService> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 客服类型
	 * 
	 */
	private CustomerType type;
	/**
	 * 账号
	 * 
	 */
	private String account;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 是否显示
	 * 
	 */
	private Boolean isShow;

	public OnlineCustomerService() {
		super();
	}

	public OnlineCustomerService(String id) {
		super(id);
	}

	public enum CustomerType {
		/**
		 * QQ在线客服
		 */
		qq,
		/**
		 * QQ群
		 */
		qqgroup;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取客服类型
	 * 
	 * @return 客服类型
	 */
	@Length(min = 1, max = 100)
	public CustomerType getType() {
		return type;
	}

	/**
	 * 设置客服类型
	 * 
	 * @param type
	 *            客服类型
	 */
	public void setType(CustomerType type) {
		this.type = type;
	}

	/**
	 * 获取账号
	 * 
	 * @return 账号
	 */
	@Length(min = 1, max = 100)
	public String getAccount() {
		return account;
	}

	/**
	 * 设置账号
	 * 
	 * @param account
	 *            账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取是否显示
	 * 
	 * @return the isShow
	 */
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 * 
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}