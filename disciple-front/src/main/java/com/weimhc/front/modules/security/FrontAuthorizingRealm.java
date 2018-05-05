/**
 * 
 */
package com.weimhc.front.modules.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.thinkgem.javamg.common.utils.Encodes;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.service.CaptchaService;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.base.utils.setting.AccountLockType;
import com.weimhc.modules.base.utils.setting.CaptchaType;
import com.weimhc.modules.base.utils.setting.MemberSettingUtils;
import com.weimhc.modules.base.utils.setting.SettingUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 系统安全认证实现类
 * 
 * @version 2016-02-25
 */
@Service
public class FrontAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemService systemService;

	@Resource
	private RSAService rsaService;

	@Resource
	private PictureCaptchaService picturecaptchaService;

	@Autowired
	private CaptchaService sMSCaptchaService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (StringUtils.isBlank(token.getUsername())) {
			throw new AuthenticationException("front.login.invalid");
		}

		// 校验登录验证码
		if (token.getIsNeedCaptcha() && !picturecaptchaService.isValid(CaptchaType.memberRegister,
				token.getCaptchaId(), token.getCaptcha())) {
			throw new AuthenticationException("captcha.invalid");
		}

		// 校验用户名密码
		List<UserAuth> userAuths = UserAuthUtils.getList(token.getUsername(), IdentityType.username,
				IdentityType.mobile);
		if (userAuths == null || userAuths.isEmpty()) {
			throw new UnknownAccountException();
		}
		UserAuth userAuth = userAuths.get(0);
		Member member = getSystemService().getMember(userAuth.getUserInfo().getId());
		if (member.getUserLoginAuthorization() == null) {
			throw new UnknownAccountException();
		}
		// 禁止登录
		if (!member.getUserLoginAuthorization().getIfEnabled()) {
			throw new AuthenticationException("error.login.disabledAccount");
		}
		if (member.getUserLoginAuthorization().getIfLocked()) {
			if (ArrayUtils.contains(SettingUtils.getAccountLockTypes(), AccountLockType.member)) {
				int loginFailureLockTime = MemberSettingUtils.getAccountLockTime();
				if (loginFailureLockTime == 0) {
					throw new AuthenticationException("error.login.lockedAccount");
				}
				Date lockedDate = member.getUserLoginAuthorization().getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
				if (new Date().after(unlockDate)) {
					member.getUserLoginAuthorization().setLoginFailureCount(0);
					member.getUserLoginAuthorization().setIfLocked(false);
					member.getUserLoginAuthorization().setLockedDate(null);
					systemService.updateMemberLoginFailure(member);
				} else {
					throw new AuthenticationException("error.login.lockedAccount");
				}
			} else {
				member.getUserLoginAuthorization().setLoginFailureCount(0);
				member.getUserLoginAuthorization().setIfLocked(false);
				member.getUserLoginAuthorization().setLockedDate(null);
				systemService.updateMemberLoginFailure(member);
			}
		}
		MemberPrincipal principal = new MemberPrincipal(member, token.isMobileLogin());
		int activeSessionSize = getSystemService().getSessionDao()
				.getActiveSessions(false, principal, null).size();
		if (logger.isDebugEnabled()) {
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize,
					token.getUsername());
		}

		// 密码验证
		byte[] salt = Encodes.decodeHex(userAuth.getCredential().substring(0, 16));
		return new SimpleAuthenticationInfo(principal, userAuth.getCredential().substring(16),
				ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		MemberPrincipal principal = (MemberPrincipal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))) {
			Collection<Session> sessions = getSystemService().getSessionDao()
					.getActiveSessions(true, principal, MemberUtils.getSession());
			if (sessions.size() > 0) {
				// 如果是登录进来的，则踢出已在线用户
				if (MemberUtils.getSubject().isAuthenticated()) {
					for (Session session : sessions) {
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else {
					MemberUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}

		Member member = getSystemService().getByIdentifier(principal.getUsername(),
				IdentityType.username);
		if (member != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			// 添加用户权限
			info.addStringPermission("user");
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(member.getUserLoginAuthorization());
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

}
