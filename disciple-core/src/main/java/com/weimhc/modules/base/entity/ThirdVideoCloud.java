/**
 * 
 */
package com.weimhc.modules.base.entity;

import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 第三方视频云 Entity
 * 
 * @author zsm
 * @version 2017-06-21
 */
public class ThirdVideoCloud extends BaseNameEntity<ThirdVideoCloud> {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码
	 * 
	 */
	private String code;

	/**
	 * 用户id
	 */
	private String clientId;

	public ThirdVideoCloud() {
		super();
	}

	public ThirdVideoCloud(String id) {
		super(id);
	}

	/**
	 * 返回 代码
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 代码
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回 用户id
	 * 
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * 设置 用户id
	 * 
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}