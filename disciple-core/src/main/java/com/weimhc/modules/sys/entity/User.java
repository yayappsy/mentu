/**
 * 
 */
package com.weimhc.modules.sys.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.Collections3;
import com.thinkgem.javamg.common.utils.excel.annotation.ExcelField;
import com.weimhc.modules.user.entity.AbstractUserInfo;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 后台用户Entity
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class User extends AbstractUserInfo<User> {

	private static final long serialVersionUID = 1L;

	private Role role; // 根据角色查询用户条件

	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	/**
	 * 当前用户
	 */
	protected User currentUser;

	protected User createBy; // 创建者
	protected User updateBy; // 更新者

	public User() {
		super();
	}

	public User(String id) {
		super(id);
	}

	public User(String id, String username) {
		super(id, username);
	}

	public User(UserInfo userInfo) {
		super(userInfo.getId());
		this.setUserInfo(userInfo);
	}

	public User(Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	@ExcelField(title = "拥有角色", align = 1, sort = 800)
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}

	public boolean isAdmin() {
		return isAdmin(this.id);
	}

	public static boolean isAdmin(String id) {
		return id != null && "1".equals(id);
	}

	@Override
	public String toString() {
		return id;
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