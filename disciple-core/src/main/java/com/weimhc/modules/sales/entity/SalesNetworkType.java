/**
 * 
 */
package com.weimhc.modules.sales.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 营业网点类型Entity
 * @author zsm
 * @version 2017-04-28
 */
public class SalesNetworkType extends SortableEntity<SalesNetworkType>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */		
	private String name;	
	/**
	 * 描述
	 * 
	 */		
	private String description;	
	
	public SalesNetworkType() {
		super();
	}

	public SalesNetworkType(String id){
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */	
	@Length(min=1, max=100)
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
	 * 获取描述
	 * 
	 * @return 描述
	 */	
	@Length(min=1, max=255)
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
	
}