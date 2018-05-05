/**
 * 
 */
package com.weimhc.modules.company.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * 企业标签Entity
 * @author lc
 * @version 2017-11-15
 */
public class CompanyLabel extends SortableEntity<CompanyLabel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标签名称
	
	private Company company; //根据企业查询标签
	
	public CompanyLabel() {
		super();
	}

	public CompanyLabel(String id){
		super(id);
	}

	@Length(min=1, max=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
}