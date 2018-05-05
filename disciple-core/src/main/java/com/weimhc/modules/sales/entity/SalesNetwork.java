/**
 * 
 */
package com.weimhc.modules.sales.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.javamg.common.persistence.TreeEntity;
import com.weimhc.modules.base.entity.Region;

/**
 * 营业网点Entity
 * 
 * @author zsm
 * @version 2017-04-28
 */
public class SalesNetwork extends TreeEntity<SalesNetwork> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name; // 名称
	/**
	 * 父级编号
	 * 
	 */
	private SalesNetwork parent; // 父级编号

	/**
	 * 网点类型
	 * 
	 */
	private SalesNetworkType salesNetworkType; // 网点类型

	/**
	 * 链接
	 * 
	 */
	private String url; // 链接
	/**
	 * 负责人
	 * 
	 */
	private String manager; // 负责人
	/**
	 * 联系电话
	 * 
	 */
	private String phone; // 联系电话
	/**
	 * 联系邮箱
	 * 
	 */
	private String email; // 联系邮箱
	/**
	 * 所在地
	 * 
	 */
	private Region location;
	/**
	 * 详细地址
	 * 
	 */
	private String detailedAddress; // 详细地址
	/**
	 * 位置经度
	 * 
	 */
	private String longitude; // 位置经度
	/**
	 * 位置纬度
	 * 
	 */
	private String latitude; // 位置纬度

	public SalesNetwork() {
		super();
	}

	public SalesNetwork(String id) {
		super(id);
	}

	/**
	 * 获取id
	 * 
	 * @return id
	 */
	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Override
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
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public SalesNetwork getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(SalesNetwork parent) {
		this.parent = parent;
	}

	/**
	 * 获取所有父级编号
	 * 
	 * @return 所有父级编号
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置所有父级编号
	 * 
	 * @param parentIds
	 *            所有父级编号
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 获取网点类型
	 * 
	 * @return 网点类型
	 */
	@NotNull
	public SalesNetworkType getSalesNetworkType() {
		return salesNetworkType;
	}

	/**
	 * 设置网点类型
	 * 
	 * @param salesNetworkType
	 *            网点类型
	 */
	public void setSalesNetworkType(SalesNetworkType salesNetworkType) {
		this.salesNetworkType = salesNetworkType;
	}

	/**
	 * 获取链接
	 * 
	 * @return 链接
	 */
	@Length(min = 0, max = 2000)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接
	 * 
	 * @param url
	 *            链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取负责人
	 * 
	 * @return 负责人
	 */
	@Length(min = 0, max = 100)
	public String getManager() {
		return manager;
	}

	/**
	 * 设置负责人
	 * 
	 * @param manager
	 *            负责人
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	@Length(min = 0, max = 20)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取联系邮箱
	 * 
	 * @return 联系邮箱
	 */
	@Length(min = 0, max = 20)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置联系邮箱
	 * 
	 * @param email
	 *            联系邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取所在地
	 * 
	 * @return 所在地
	 */
	public Region getLocation() {
		return location;
	}

	/**
	 * 设置所在地
	 * 
	 * @param location
	 *            所在地
	 */
	public void setLocation(Region location) {
		this.location = location;
	}

	/**
	 * 获取详细地址
	 * 
	 * @return 详细地址
	 */
	@Length(min = 0, max = 255)
	public String getDetailedAddress() {
		return detailedAddress;
	}

	/**
	 * 设置详细地址
	 * 
	 * @param detailedAddress
	 *            详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	/**
	 * 获取位置经度
	 * 
	 * @return 位置经度
	 */
	@Length(min = 0, max = 64)
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 设置位置经度
	 * 
	 * @param longitude
	 *            位置经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取位置纬度
	 * 
	 * @return 位置纬度
	 */
	@Length(min = 0, max = 64)
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 设置位置纬度
	 * 
	 * @param latitude
	 *            位置纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取父id
	 * 
	 * @return 获取父id
	 */
	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}