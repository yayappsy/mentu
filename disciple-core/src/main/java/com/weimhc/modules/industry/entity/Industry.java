/**
 * 
 */
package com.weimhc.modules.industry.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 行业分类Entity
 * @author lc
 * @version 2017-11-13
 */
public class Industry extends TreeEntity<Industry> {
	
	private static final long serialVersionUID = 1L;
	private Boolean inMenu;		// 是否在导航中显示
	
	public Industry() {
		super();
	}

	public Industry(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull()
	public Industry getParent() {
		return parent;
	}

	public void setParent(Industry parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=255)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull()
	public Boolean getInMenu() {
		return inMenu;
	}

	public void setInMenu(Boolean inMenu) {
		this.inMenu = inMenu;
	}
	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}