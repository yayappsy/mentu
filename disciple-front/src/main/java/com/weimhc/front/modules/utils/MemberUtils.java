/**
 * 
 */
package com.weimhc.front.modules.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.security.BasePrincipal;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.modules.member.dao.MemberDao;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.AbstractUserInfo;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 用户工具类
 * 
 * @version 2013-12-05
 */
public class MemberUtils {

	private static Logger logger = LoggerFactory.getLogger("MemberUtils.class");

	private static MemberDao memberDao = SpringContextHolder.getBean(MemberDao.class);

	public static final String MEMBER_CACHE = "memberCache";
	public static final String MEMBER_CACHE_ID_ = "id_";
	public static final String MEMBER_CACHE_USERNAME_ = "ln";
	public static final String MEMBER_CACHE_MOBILE_ = "lm";
	public static final String MEMBER_CACHE_EMAIL_ = "le";

	public static final String CACHE_ROLE_LIST = "roleList";

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static Member get(String id) {
		Member member = (Member) CacheUtils.get(MEMBER_CACHE, MEMBER_CACHE_ID_ + id);
		if (member == null) {
			member = memberDao.get(id);
			if (member == null) {
				return null;
			}
			putMemberCache(member);
		}
		// 如果获取到的数据中，id为空，则重新查询数据
		if (StringUtils.isBlank(member.getId())) {
			member.setId(id);
			clearCache(member);
			return get(id);
		}
		return member;
	}

	/**
	 * 设置缓存
	 * 
	 * @param member
	 */
	private static void putMemberCache(Member member) {
		CacheUtils.put(MEMBER_CACHE, MEMBER_CACHE_ID_ + member.getId(), member);
		if (StringUtils.isNotBlank(member.getUsername())) {
			CacheUtils.put(MEMBER_CACHE, MEMBER_CACHE_USERNAME_ + member.getUsername(), member);
		}
		if (StringUtils.isNotBlank(member.getMobile())) {
			CacheUtils.put(MEMBER_CACHE, MEMBER_CACHE_MOBILE_ + member.getMobile(), member);
		}
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param username
	 * @return 取不到返回null
	 */
	public static Member getByUsername(String username) {
		Member member = (Member) CacheUtils.get(MEMBER_CACHE, MEMBER_CACHE_USERNAME_ + username);
		if (member == null) {
			member = new Member();
			member.setUsername(username);
			member = memberDao.getEntity(member);
			if (member == null) {
				return null;
			}
			putMemberCache(member);
		}
		return member;
	}

	/**
	 * 根据邮箱获取用户
	 * 
	 * @param username
	 * @return 取不到返回null
	 */
	public static Member getByEmail(String email) {
		Member member = (Member) CacheUtils.get(MEMBER_CACHE, MEMBER_CACHE_EMAIL_ + email);
		if (member == null) {
			member = new Member();
			member.setEmail(email);
			member = memberDao.getEntity(member);
			if (member == null) {
				return null;
			}
			putMemberCache(member);
		}
		return member;
	}

	/**
	 * 根据手机号获取用户
	 * 
	 * @param mobilePhone
	 * @return 取不到返回null
	 */
	public static Member getByMobile(String mobilePhone) {
		Member member = (Member) CacheUtils.get(MEMBER_CACHE, MEMBER_CACHE_MOBILE_ + mobilePhone);
		if (member == null) {
			member = new Member();
			member.setMobile(mobilePhone);
			member = memberDao.getEntity(member);
			if (member == null) {
				return null;
			}
			putMemberCache(member);
		}
		return member;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		MemberUtils.clearCache(getCurrent());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param memberId
	 */
	public static void clearCache(String memberId) {
		clearCache(get(memberId));
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param member
	 */
	public static void clearCache(AbstractUserInfo<?> member) {
		if (member == null) {
			return;
		}
		if (StringUtils.isNotBlank(member.getId())) {
			CacheUtils.remove(MEMBER_CACHE, MEMBER_CACHE_ID_ + member.getId());
		}
		if (StringUtils.isNotBlank(member.getMobile())) {
			CacheUtils.remove(MEMBER_CACHE, MEMBER_CACHE_MOBILE_ + member.getMobile());
		}
		if (StringUtils.isNotBlank(member.getUsername())) {
			CacheUtils.remove(MEMBER_CACHE, MEMBER_CACHE_USERNAME_ + member.getUsername());
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new Member()
	 */
	public static Member getCurrent() {
		MemberPrincipal principal = getPrincipal();
		if (principal != null) {
			Member member = get(principal.getId());
			if (member != null) {
				return member;
			}
			return new Member();
		}
		// 如果没有登录，则返回实例化空的Member对象。
		return new Member();
	}

	/**
	 * 根据token 获取member信息
	 * 
	 * @param token
	 * @return mebmer 如果查询不到返回一个新的Member 实体
	 */
	public static Member getMemberByToken(String token) {
		BasePrincipal basePrincipal = AppTokenHelper.getBasePrincipal(token);
		if (basePrincipal != null) {
			return get(basePrincipal.getId());
		}
		return new Member();
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static MemberPrincipal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			MemberPrincipal principal = (MemberPrincipal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	/**
	 * ajax方式登录
	 * 
	 * @param uPasswordToken
	 * @return 如果出错则返回错误提示信息
	 */
	public static String subjectLogin(UsernamePasswordToken uPasswordToken) {
		String message = null;
		Subject subject = MemberUtils.getSubject();
		try {
			subject.login(uPasswordToken);
		} catch (UnknownAccountException ex) {
			message = "error.login.incorrectCredentials";
		} catch (IncorrectCredentialsException ex) {
			message = "error.login.incorrectCredentials";
		} catch (LockedAccountException ex) {
			message = "error.login.lockedAccount";
		} catch (AuthenticationException ex) {
			message = ex.getMessage();
		} catch (Exception ex) {
			message = ex.getMessage();
			logger.error(ex.getMessage());
		} finally {
			if (StringUtils.isNotBlank(message)) {
				loginFailure(uPasswordToken);
			}
		}
		return message;
	}

	/**
	 * 登录失败，清除缓存
	 * 
	 * @param uPasswordToken
	 * @return
	 */
	public static void loginFailure(UsernamePasswordToken token) {
		List<UserAuth> userAuths = UserAuthUtils.getList(token.getUsername(), IdentityType.username,
				IdentityType.mobile);
		if (userAuths != null && !userAuths.isEmpty()) {
			UserAuth userAuth = userAuths.get(0);
			Member member = MemberUtils.get(userAuth.getUserInfo().getId());
			MemberUtils.clearCache(member);
		}
	}

	// ============== Member Cache ==============
	// 使用shiro的session存储内容

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}

}
