/**
 * 
 */
package com.weimhc.modules.sys.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 角色Entity
 * 
 * @version 2013-12-05
 */
public class Role extends DataEntity<Role> {

	private static final long serialVersionUID = 1L;
	private String name; // 角色名称
	private String enname; // 英文名称
	private String roleType;// 权限类型
	private String dataScope;// 数据范围

	private String oldName; // 原角色名称
	private String oldEnname; // 原英文名称
	private String sysData; //是否是系统数据
	private String useable; //是否是可用
	/**
	 * 该角色是否可被分配
	 */
	private Boolean canBeAssigned;

	private User user; // 根据用户ID查询角色列表

	//	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	public static final String DATA_SCOPE_COMPANY = "3";
	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	public static final String DATA_SCOPE_OFFICE = "5";
	public static final String DATA_SCOPE_SELF = "8";
	public static final String DATA_SCOPE_CUSTOM = "9";

	public Role() {
		super();
		this.dataScope = DATA_SCOPE_SELF;
		this.useable = Global.YES;
	}

	public Role(String id) {
		super(id);
	}

	public Role(User user) {
		this();
		this.user = user;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getSysData() {
		return sysData;
	}

	public void setSysData(String sysData) {
		this.sysData = sysData;
	}

	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 100)
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	@Length(min = 1, max = 100)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldEnname() {
		return oldEnname;
	}

	public void setOldEnname(String oldEnname) {
		this.oldEnname = oldEnname;
	}

	//	public List<User> getUserList() {
	//		return userList;
	//	}
	//
	//	public void setUserList(List<User> userList) {
	//		this.userList = userList;
	//	}
	//	
	//	public List<String> getUserIdList() {
	//		List<String> nameIdList = Lists.newArrayList();
	//		for (User user : userList) {
	//			nameIdList.add(user.getId());
	//		}
	//		return nameIdList;
	//	}
	//
	//	public String getUserIds() {
	//		return StringUtils.join(getUserIdList(), ",");
	//	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<String> getMenuIdList() {
		List<String> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		menuList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		return StringUtils.join(getMenuIdList(), ",");
	}

	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null) {
			String[] ids = StringUtils.split(menuIds, ",");
			setMenuIdList(Lists.newArrayList(ids));
		}
	}

	/**
	 * 获取权限字符串列表
	 */
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission() != null
					&& !"".equals(menu.getPermission())) {
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 返回 该角色是否可被分配（不可分配的角色，不能操作）
	 * 
	 * @return the canBeAssigned
	 */
	public Boolean getCanBeAssigned() {
		return canBeAssigned;
	}

	/**
	 * 设置 该角色是否可被分配
	 * 
	 * @param canBeAssigned
	 *            the canBeAssigned to set
	 */
	public void setCanBeAssigned(Boolean canBeAssigned) {
		this.canBeAssigned = canBeAssigned;
	}
	//		return StringUtils.join(menuNameList, ",");
	//	}
}
