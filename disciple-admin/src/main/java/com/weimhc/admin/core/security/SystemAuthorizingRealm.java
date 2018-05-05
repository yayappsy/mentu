/**
 * 
 */
package com.weimhc.admin.core.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.security.BasePrincipal;
import com.thinkgem.javamg.common.servlet.ValidateCodeServlet;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.Encodes;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.web.Servlets;
import com.weimhc.admin.core.service.SystemService;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.admin.core.utils.UserLogUtils;
import com.weimhc.modules.sys.entity.Menu;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.entity.User;
import com.weimhc.modules.sys.utils.UserUtils;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 系统安全认证实现类
 * 
 * @version 2014-7-5
 */
@Service
// @DependsOn({"userDao","roleDao","menuDao"})
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private SystemService systemService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		int activeSessionSize = getSystemService().getSessionDao().getActiveSessions(false).size();
		if (logger.isDebugEnabled()) {
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize,
					token.getUsername());
		}

		// 校验登录验证码
		if (isValidateCodeLogin(token.getUsername(), false, false)) {
			Session session = AdminUserUtils.getSession();
			String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)) {
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}

		// 校验用户名密码
		List<UserAuth> userAuths = UserAuthUtils.getList(token.getUsername(), IdentityType.username,
				IdentityType.mobile);
		if (userAuths == null || userAuths.isEmpty()) {
			throw new UnknownAccountException();
		}
		UserAuth userAuth = userAuths.get(0);
		User user = getSystemService().getUser(userAuth.getUserInfo().getId());
		if (user == null) {
			throw new AuthenticationException("msg:您没有登录后台的权限.");
		}

		if (user.getUserLoginAuthorization().getIfEnabled() != null
				&& !user.getUserLoginAuthorization().getIfEnabled()) {
			UserUtils.clearCache(user);
			throw new AuthenticationException("msg:该已帐号禁止登录.");
		}
		byte[] salt = Encodes.decodeHex(userAuth.getCredential().substring(0, 16));
		return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()),
				userAuth.getCredential().substring(16), ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))) {
			Collection<Session> sessions = getSystemService().getSessionDao()
					.getActiveSessions(true, principal, AdminUserUtils.getSession());
			if (sessions.size() > 0) {
				// 如果是登录进来的，则踢出已在线用户
				if (AdminUserUtils.getSubject().isAuthenticated()) {
					for (Session session : sessions) {
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else {
					AdminUserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUser(principal.getId());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = AdminUserUtils.getMenuList();
			for (Menu menu : list) {
				if (StringUtils.isNotBlank(menu.getPermission())) {
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()) {
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user.getUserLoginAuthorization());
			// 记录登录日志
			UserLogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * 
	 * @param permission
	 */
	private void authorizationValidate(Permission permission) {
		// 模块授权预留接口
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	// /**
	// * 清空用户关联权限认证，待下次使用时重新加载
	// */
	// public void clearCachedAuthorizationInfo(Principal principal) {
	// SimplePrincipalCollection principals = new
	// SimplePrincipalCollection(principal, getName());
	// clearCachedAuthorizationInfo(principals);
	// }

	/**
	 * 清空所有关联认证
	 * 
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
		// Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		// if (cache != null) {
		// for (Object key : cache.keys()) {
		// cache.remove(key);
		// }
		// }
	}

	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	/**
	 * 是否是验证码登录
	 * 
	 * @param useruame
	 *            用户名
	 * @param isFail
	 *            计数加1
	 * @param clean
	 *            计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal extends BasePrincipal implements Serializable {

		private static final long serialVersionUID = 1L;

		public Principal(User user, boolean mobileLogin) {
			super.setId(user.getId());
			super.setUsername(user.getUsername());
			super.setName(user.getName());
			super.setMobileLogin(mobileLogin);
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try {
				return (String) AdminUserUtils.getSession().getId();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return super.getId();
		}

	}
}
