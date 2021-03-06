package com.weimhc.framework.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.CacheUtils;

/**
 * TokenHelper
 * 
 */
public class TokenHelper {

	/**
	 * 保存token值的默认命名空间
	 */
	public static final String TOKEN_NAMESPACE = "xxx.tokens";

	/**
	 * The default name to map the token value
	 */
	public static final String DEFAULT_TOKEN_NAME = "token";

	/**
	 * 持有token名称的字段名
	 */
	public static final String TOKEN_NAME_FIELD = "xxx.token.name";

	private static final Logger logger = LoggerFactory
			.getLogger(TokenHelper.class);

	private static final Random RANDOM = new SecureRandom();

	/**
	 * token保存到缓存的位置
	 */
	public static final String TOKEN_CACHE = "tokenCache";

	/**
	 * 使用随机字串作为token名字保存token
	 * 
	 * @param request
	 * @return token
	 */
	public static String setToken(HttpServletRequest request) {
		return setToken(request, DEFAULT_TOKEN_NAME);
	}

	/**
	 * 使用给定的字串作为token名字保存token
	 * 
	 * @param request
	 * @param tokenName
	 * @return token
	 */
	private static String setToken(HttpServletRequest request,
			String tokenName) {
		String token = generateGUID();
		setCacheToken(request, tokenName, token);
		return token;
	}

	/**
	 * 保存一个给定名字和值的token
	 * 
	 * @param request
	 * @param tokenName
	 * @param token
	 */
	private static void setCacheToken(HttpServletRequest request,
			String tokenName, String token) {
		try {
			String tokenCacheName = buildTokenCacheAttributeName(tokenName);
			CacheUtils.put(TOKEN_CACHE, tokenCacheName, token);
			request.setAttribute(TOKEN_NAME_FIELD, tokenName);
			request.setAttribute(tokenName, token);
		} catch (IllegalStateException e) {
			String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: "
					+ e.getMessage();
			logger.error(msg, e);
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * 构建一个基于token名字的带有命名空间为前缀的token名字
	 * 
	 * @param tokenName
	 * @return the name space prefixed session token name
	 */
	public static String buildTokenCacheAttributeName(String tokenName) {
		return TOKEN_NAMESPACE + "." + tokenName;
	}

	/**
	 * 从请求域中获取给定token名字的token值
	 * 
	 * @param tokenName
	 * @return the token String or null, if the token could not be found
	 */
	public static String getToken(HttpServletRequest request,
			String tokenName) {
		if (tokenName == null) {
			return null;
		}
		Map params = request.getParameterMap();
		String[] tokens = (String[]) params.get(tokenName);
		String token;
		if ((tokens == null) || (tokens.length < 1)) {
			logger.warn(
					"Could not find token mapped to token name " + tokenName);
			return null;
		}

		token = tokens[0];
		return token;
	}

	/**
	 * 从请求参数中获取token名字
	 * 
	 * @return the token name found in the params, or null if it could not be
	 *         found
	 */
	public static String getTokenName(HttpServletRequest request) {
		Map params = request.getParameterMap();

		if (!params.containsKey(TOKEN_NAME_FIELD)) {
			logger.warn("Could not find token name in params.");
			return null;
		}

		String[] tokenNames = (String[]) params.get(TOKEN_NAME_FIELD);
		String tokenName;

		if ((tokenNames == null) || (tokenNames.length < 1)) {
			logger.warn("Got a null or empty token name.");
			return null;
		}

		tokenName = tokenNames[0];

		return tokenName;
	}

	/**
	 * 验证当前请求参数中的token是否合法，如果合法的token出现就会删除它，它不会再次成功合法的token
	 * 
	 * @return 验证结果
	 */
	public static boolean validToken(HttpServletRequest request) {
		String tokenName = getTokenName(request);

		if (tokenName == null) {
			logger.debug("no token name found -> Invalid token ");
			return false;
		}

		String token = getToken(request, tokenName);

		if (token == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("no token found for token name " + tokenName
						+ " -> Invalid token ");
			}
			return false;
		}

		String tokenCacheName = buildTokenCacheAttributeName(tokenName);
		String cacheToken = (String) CacheUtils.get(TOKEN_CACHE,
				tokenCacheName);

		if (!token.equals(cacheToken)) {
			logger.warn("xxx.internal.invalid.token Form token " + token
					+ " does not match the session token " + cacheToken + ".");
			return false;
		}

		// remove the token so it won't be used again

		return true;
	}

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

}
