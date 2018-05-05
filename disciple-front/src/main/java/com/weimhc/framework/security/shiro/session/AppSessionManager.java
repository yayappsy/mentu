/**
 * 
 */

package com.weimhc.framework.security.shiro.session;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thinkgem.javamg.common.security.shiro.session.SessionManager;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;

/**
 * 自定义WEB会话管理类 兼容app和微信，实现自动登录
 * 
 * @author laozh
 * @version 2016-07-27
 */
public class AppSessionManager extends SessionManager {

	private static final Logger log = LoggerFactory
			.getLogger(DefaultWebSessionManager.class);

	public static final String DEFAULT_ACCESS_TOKEN_ID_NAME = "accessTokenId";

	private static final String WEIXIN_BROWSER_NAME = "MicroMessenger";

	/**
	 * 微信浏览器端，session的过期时间30天，单位为ms（毫秒）
	 */
	private static Integer WX_SESSION_TIME_OUT = 30 * 24 * 60 * 60 * 1000;

	/***
	 * 访问token
	 */
	private String accessToken;

	/***
	 * 获取accessTokenId的内容
	 */
	private Cookie accessTokenIdCookie;
	/***
	 * tokenIdCookie 是否启用
	 */
	private boolean accessTokenIdCookieEnabled;

	public AppSessionManager() {
		super();
		Cookie cookie = new SimpleCookie(DEFAULT_ACCESS_TOKEN_ID_NAME);
		cookie.setHttpOnly(true); // more secure, protects against XSS attacks
		this.accessTokenIdCookie = cookie;
		this.accessTokenIdCookieEnabled = true;
	}

	/**
	 *
	 * 返回 供微信浏览器使用
	 * 
	 * @return the accessTokenIdCookie
	 */
	public Cookie getAccessTokenIdCookie() {
		return accessTokenIdCookie;
	}

	/**
	 * 设置 供微信浏览器使用
	 * 
	 * @param accessTokenIdCookie
	 *            the accessTokenIdCookie to set
	 */
	public void setAccessTokenIdCookie(Cookie accessTokenIdCookie) {
		this.accessTokenIdCookie = accessTokenIdCookie;
	}

	/**
	 *
	 * 返回 供微信浏览器使用
	 * 
	 * @return the accessTokenIdCookieEnabled
	 */
	public boolean isAccessTokenIdCookieEnabled() {
		return accessTokenIdCookieEnabled;
	}

	/**
	 * 设置 供微信浏览器使用
	 * 
	 * @param accessTokenIdCookieEnabled
	 *            the accessTokenIdCookieEnabled to set
	 */
	public void setAccessTokenIdCookieEnabled(
			boolean accessTokenIdCookieEnabled) {
		this.accessTokenIdCookieEnabled = accessTokenIdCookieEnabled;
	}

	@Override
	protected Serializable getSessionId(ServletRequest request,
			ServletResponse response) {

		Serializable sessionId = super.getSessionId(request, response);

		String accessTokenId = getAccessTokenIdCookieCookieValue(request,
				response);
		if (UserAgentUtils.isSpecifyBrowser((HttpServletRequest) request,
				WEIXIN_BROWSER_NAME) && StringUtils.isNotBlank(accessTokenId)) {
			String storedSessionId = (String) CacheUtils.get("userStoreCache",
					accessTokenId);
			if (StringUtils.isBlank(storedSessionId)) {
				storedSessionId = (String) sessionId;
				CacheUtils.put("userStoreCache", accessTokenId, sessionId);
			}
			return storedSessionId;
		}
		return sessionId;
	}

	/***
	 * 保存sessionId
	 * 
	 * @param currentId
	 * @param request
	 * @param response
	 */
	private void storeSessionId(Serializable currentId,
			HttpServletRequest request, HttpServletResponse response) {
		if (currentId == null) {
			String msg = "sessionId cannot be null when persisting for subsequent requests.";
			throw new IllegalArgumentException(msg);
		}
		Cookie template = getSessionIdCookie();
		Cookie cookie = new SimpleCookie(template);
		String idString = currentId.toString();
		cookie.setValue(idString);
		cookie.saveTo(request, response);
		log.trace("Set session ID cookie for session with id {}", idString);
	}

	/***
	 * 获取保存的访问token
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String getAccessTokenIdCookieCookieValue(ServletRequest request,
			ServletResponse response) {
		if (!isAccessTokenIdCookieEnabled()) {
			log.debug(
					"Session ID cookie is disabled - session id will not be acquired from a request cookie.");
			return null;
		}
		if (!(request instanceof HttpServletRequest)) {
			log.debug(
					"Current request is not an HttpServletRequest - cannot get session ID cookie.  Returning null.");
			return null;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		return getAccessTokenIdCookie().readValue(httpRequest,
				WebUtils.toHttp(response));
	}

	/***
	 * 将微信浏览器的过期时间设置很长时间
	 */
	@Override
	protected void onStart(Session session, SessionContext context) {

		HttpServletRequest request = WebUtils.getHttpRequest(context);
		HttpServletResponse response = WebUtils.getHttpResponse(context);

		String accessTokenId = getAccessTokenIdCookieCookieValue(request,
				response);
		if (StringUtils.isNotBlank(accessTokenId)) {
			String storedSessionId = (String) CacheUtils.get("userStoreCache",
					accessTokenId);
			if (StringUtils.isBlank(storedSessionId)) {
				storedSessionId = session.getId().toString();
				CacheUtils.put("userStoreCache", accessTokenId,
						storedSessionId);
			}
			if (!StringUtils.equals(storedSessionId,
					session.getId().toString())) {
				((SimpleSession) session).setId(storedSessionId);
			}
		}
		if (UserAgentUtils.isSpecifyBrowser(request, WEIXIN_BROWSER_NAME)) {
			session.setTimeout(WX_SESSION_TIME_OUT);
		}
		super.onStart(session, context);

	}

	@Override
	protected Session retrieveSession(SessionKey sessionKey) {
		Session session = super.retrieveSession(sessionKey);
		if (session == null) {
			removeUserStoreSession();
		}
		return session;
	}

	@Override
	public Date getStartTimestamp(SessionKey key) {
		Date startTimestamp = super.getStartTimestamp(key);
		if (startTimestamp == null) {
			removeUserStoreSession();
		}
		return startTimestamp;
	}

	@Override
	public Date getLastAccessTime(SessionKey key) {
		Date lastAccessTime = super.getLastAccessTime(key);
		if (lastAccessTime == null) {
			removeUserStoreSession();
		}
		return lastAccessTime;
	}

	private void removeUserStoreSession() {
		RequestAttributes requestAttributes = RequestContextHolder
				.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes)
					.getRequest();
			String accessTokenId = getAccessTokenIdCookieCookieValue(request,
					null);
			CacheUtils.remove("userStoreCache", accessTokenId);
		}
	}
}