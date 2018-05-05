/**
 * 
 */
package com.weimhc.admin.core.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.security.shiro.session.SessionDAO;
import com.thinkgem.javamg.common.service.ServiceException;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.admin.core.utils.UserLogUtils;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.sys.dao.MenuDao;
import com.weimhc.modules.sys.dao.RoleDao;
import com.weimhc.modules.sys.dao.UserDao;
import com.weimhc.modules.sys.entity.Menu;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.entity.User;
import com.weimhc.modules.sys.utils.UserUtils;
import com.weimhc.modules.user.entity.BusinessSystem;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.service.UserInfoCommonService;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * 
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends UserInfoCommonService implements InitializingBean {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private SessionDAO sessionDao;

	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	// -- User Service --//

	/**
	 * 获取用户
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return UserUtils.get(id);
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return
	 */
	public User getUserByUsername(String loginName) {
		return UserUtils.getByUsername(loginName);
	}

	public Page<User> findUser(Page<User> page, User user) {

		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		return page;
	}

	/**
	 * 无分页查询人员列表
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user) {
		List<User> list = userDao.findList(user);
		return list;
	}

	/**
	 * 更新用户信息，并创建登录凭证（用户名和手机号）
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		user.getUserLoginAuthorization().setBusinessSystem(BusinessSystem.admin);
		saveUserInfo(user.getLatestUserInfo(), true, IdentityType.username, IdentityType.mobile);
		user.refreshUserInfo();
		if (StringUtils.isNotBlank(user.getId())) {
			// 更新用户与角色关联
			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0) {
				userDao.insertUserRole(user);
			} else {
				throw new ServiceException(user.getUsername() + "没有设置角色！");
			}

			// 清除用户缓存
			UserUtils.clearCache(user);
		}
	}

	/**
	 * 获得活动会话
	 * 
	 * @return
	 */
	public Collection<Session> getActiveSessions() {
		return sessionDao.getActiveSessions(false);
	}

	// -- Role Service --//

	public Role getRole(String id) {
		return roleDao.get(id);
	}

	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDao.getByName(r);
	}

	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDao.getByEnname(r);
	}

	public List<Role> findRole(Role role) {
		return roleDao.findList(role);
	}

	public List<Role> findAllRole() {
		return AdminUserUtils.getRoleList();
	}

	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())) {
			role.preInsert();
			roleDao.insert(role);

		} else {
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0) {
			roleDao.insertRoleMenu(role);
		}

		// 清除用户角色缓存
		AdminUserUtils.removeCache(AdminUserUtils.CACHE_ROLE_LIST);

	}

	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);

		// 清除用户角色缓存
		AdminUserUtils.removeCache(AdminUserUtils.CACHE_ROLE_LIST);

	}

	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		List<Role> roles = user.getRoleList();
		for (Role e : roles) {
			if (e.getId().equals(role.getId())) {
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null) {
			return null;
		}
		List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);
		return user;
	}

	// -- Menu Service --//

	public Menu getMenu(String id) {
		return menuDao.get(id);
	}

	public List<Menu> findAllMenu() {
		return AdminUserUtils.getMenuList();
	}

	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {

		// 获取父节点实体
		menu.setParent(this.getMenu(menu.getParent().getId()));

		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds();

		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId()
				+ ConstantUtils.SEPARATOR_COMMA);

		String permissionStr = menu.getPermission();
		if (menu.getIfSeparate()) {
			menu.setPermission("");
		}

		// 保存或更新实体
		if (StringUtils.isBlank(menu.getId())) {
			menu.preInsert();
			menuDao.insert(menu);
		} else {
			menu.preUpdate();
			menuDao.update(menu);
		}
		if (menu.getIfSeparate() && StringUtils.isNotBlank(permissionStr)) {
			String[] permissions = StringUtils.split(permissionStr, ConstantUtils.SEPARATOR_COMMA);
			String keyword = null;
			for (String permission : permissions) {
				keyword = StringUtils.substringAfterLast(permission, ConstantUtils.SEPARATOR_COLON);
				Menu subMenu = new Menu();
				subMenu.setParent(menu);
				subMenu.setName(MessageSourceUtils.getMessage("Menu.permission." + keyword));
				subMenu.setPermission(permission);
				subMenu.setIsShow(false);
				subMenu.setParentIds(
						menu.getParentIds() + menu.getId() + ConstantUtils.SEPARATOR_COMMA);
				subMenu.preInsert();
				menuDao.insert(subMenu);
			}
		}

		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%," + menu.getId() + ",%");
		List<Menu> list = menuDao.findByParentIdsLike(m);
		for (Menu e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}
		// 清除用户菜单缓存
		AdminUserUtils.removeCache(AdminUserUtils.CACHE_MENU_LIST);

		// 清除日志相关缓存
		CacheUtils.remove(UserLogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);
		// 清除用户菜单缓存
		AdminUserUtils.removeCache(AdminUserUtils.CACHE_MENU_LIST);

		// 清除日志相关缓存
		CacheUtils.remove(UserLogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menu.setParent(this.getMenu(menu.getParent().getId()));
		// 获取修改前的parentIds，用于更新子节点的parentIds
		// String oldParentIds = menu.getParentIds();

		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId()
				+ ConstantUtils.SEPARATOR_COMMA);
		menuDao.deleteEntity(menu);
		// 清除用户菜单缓存
		AdminUserUtils.removeCache(AdminUserUtils.CACHE_MENU_LIST);

		// 清除日志相关缓存
		CacheUtils.remove(UserLogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	/***
	 * 注销清除缓存
	 * 
	 * @param user
	 */
	public void logout(User user) {
		UserUtils.clearCache(user);
		AdminUserUtils.getSubject().logout();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
