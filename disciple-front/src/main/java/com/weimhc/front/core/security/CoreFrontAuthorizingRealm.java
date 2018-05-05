/**
 * 
 */
package com.weimhc.front.core.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.service.RSAService;

/**
 * 系统安全认证实现类,如果要实现可以继承该类
 * 
 * @version 2016-02-25
 */
@Service
public class CoreFrontAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private RSAService rsaService;

	@Resource
	private PictureCaptchaService pictureCaptchaService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) {
		logger.debug("core开始认证");
		return super.getAuthenticationInfo(authcToken);
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		logger.debug("开始授权查询");
		logger.debug("core开始认证");
		return super.getAuthorizationInfo(principals);
	}

	@Override
	protected void checkPermission(Permission permission,
			AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions,
			AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals,
			Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions,
			AuthorizationInfo info) {
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
				PasswordUtils.HASH_ALGORITHM);
		matcher.setHashIterations(PasswordUtils.HASH_INTERATIONS);
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

}
