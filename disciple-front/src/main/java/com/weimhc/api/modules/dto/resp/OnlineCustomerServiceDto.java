/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.modules.base.entity.OnlineCustomerService.CustomerType;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在线客服DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class OnlineCustomerServiceDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;
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
	 * 获取名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取客服类型
	 * 
	 * @return the type
	 */
	public CustomerType getType() {
		return type;
	}

	/**
	 * 设置客服类型
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(CustomerType type) {
		this.type = type;
	}

	/**
	 * 获取客服账号
	 * 
	 * @return the account
	 */
	@ApiModelProperty(value = "客服账号")
	public String getAccount() {
		return account;
	}

	/**
	 * 设置客服账号
	 * 
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取描述
	 * 
	 * @return the description
	 */
	@ApiModelProperty(value = "描述")
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}