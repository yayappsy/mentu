/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 收藏表Entity
 * @author cwl
 * @version 2018-01-07
 */
public class CollectStore extends DataEntity<CollectStore>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 类型
	 * 
	 */		
	private String category;	
	/**
	 * 收藏品id
	 * 
	 */
	private String userId;

	private String collectorId;	
	/**
	 * 收藏品名称
	 * 
	 */		
	private String collectorName;	
	
	public CollectStore() {
		super();
	}

	public CollectStore(String id){
		super(id);
	}

	/**
	 * 获取category
	 *
	 * @return category
	 */
	@Length(min=0, max=64)
	public String getCategory() {
		return category;
	}
	/**
	 * 设置类型
	 *
	 * @param category
	 *            category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * 获取收藏品id
	 * 
	 * @return 收藏品id
	 */	
	@Length(min=0, max=64)
	public String getCollectorId() {
		return collectorId;
	}
	/**
	 * 设置收藏品id
	 * 
	 * @param collectorId
	 *            收藏品id
	 */
	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}
	
	/**
	 * 获取收藏品名称
	 * 
	 * @return 收藏品名称
	 */	
	@Length(min=1, max=255)
	public String getCollectorName() {
		return collectorName;
	}
	/**
	 * 设置收藏品名称
	 * 
	 * @param collectorName
	 *            收藏品名称
	 */
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}