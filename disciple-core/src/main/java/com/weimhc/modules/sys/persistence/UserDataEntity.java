/**
 * 
 */
package com.weimhc.modules.sys.persistence;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.sys.entity.User;

/**
 * Entity支持类
 * 
 * @version 2014-05-16
 */
public abstract class UserDataEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前用户
	 */
	protected User currentUser;

	protected User createBy; // 创建者
	protected User updateBy; // 更新者

	public UserDataEntity() {
		super();
	}

	public UserDataEntity(String id) {
		super(id);
	}

	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		super.preInsert();
		if (getCurrentUser() != null
				&& StringUtils.isNotBlank(getCurrentUser().getId())) {
			this.updateBy = getCurrentUser();
			this.createBy = getCurrentUser();
		}
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
		super.preUpdate();
		if (getCurrentUser() != null
				&& StringUtils.isNotBlank(getCurrentUser().getId())) {
			this.updateBy = getCurrentUser();
		}
	}

	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonIgnore
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

}
