package com.weimhc.framework.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.security.BasePrincipal;
import com.thinkgem.javamg.common.security.Digests;
import com.thinkgem.javamg.common.utils.CacheUtils;

/**
 * AppTokenHelper
 * <p>
 * 生成app使用的token，实现APP端与服务器接口交互的Session统制
 * </p>
 */
public class AppTokenHelper {

	/**
	 * 加盐
	 */
	private static final String SALT = "testMRf1$shao787aaadfjkds//*-+'[]jfeu;384785*^*&%^%$%";

	private static final Random RANDOM = new SecureRandom();
	/**
	 * token保存到缓存的位置
	 */
	public static final String TOKEN_CACHE = "tokenCache";

	public static final String TOKEN_STR = "token";

	/**
	 * seed保存的位置，用来进行单点登录处理
	 */
	public static final String SEED_TOKEN_CACHE = "seedTokenCache";

	/**
	 * 生成token，并缓存
	 * 
	 * @param principal
	 * @return
	 */
	public static String generateToken(BasePrincipal principal) {
		String token = Digests.md5(principal.getId());
		String seed = Digests.md5(principal.getId(), SALT);
		CacheUtils.put(SEED_TOKEN_CACHE, seed, token);
		CacheUtils.put(TOKEN_CACHE, token, principal);
		return token;
	}

	/**
	 * 获取token对应的登录凭证缓存的信息
	 * 
	 * @return
	 */
	public static BasePrincipal getBasePrincipal(String token) {
		if (valid(token)) {
			return (BasePrincipal) CacheUtils.get(TOKEN_CACHE, token);
		}
		return null;
	}

	/**
	 * 验证token,和seed中保存的token是否相等
	 * 
	 * @param token
	 * @param seed
	 * @return
	 */
	public static boolean valid(String token, String seed) {
		String storedToken = (String) CacheUtils.get(SEED_TOKEN_CACHE, seed);
		if (StringUtils.equals(token, storedToken)) {
			return true;
		}

		return false;
	}

	/**
	 * 验证token是否有效
	 * 
	 * @param token
	 * @return
	 */
	public static boolean valid(String token) {
		if (StringUtils.isBlank(token)) {
			return false;
		}
		// 是否可以获得token
		BasePrincipal principal = (BasePrincipal) CacheUtils.get(TOKEN_CACHE, token);
		if (principal == null) {
			return false;
		}
		String seed = Digests.md5(principal.getId(), SALT);
		return valid(token, seed);
	}

	/**
	 * 清除token
	 * 
	 * @param token
	 * @return
	 */
	public static void clearToken(String token) {

		// 是否可以获得token
		BasePrincipal principal = (BasePrincipal) CacheUtils.get(TOKEN_CACHE, token);
		if (principal != null) {
			String seed = Digests.md5(principal.getId(), SALT);
			CacheUtils.remove(TOKEN_CACHE, token);
			CacheUtils.remove(SEED_TOKEN_CACHE, seed);
		}

	}

	/**
	 * 单点登录处理
	 * 
	 * @return
	 */
	public static void clearLogin(String username) {
		String seed = Digests.md5(username, SALT);
		String token = (String) CacheUtils.get(SEED_TOKEN_CACHE, seed);
		CacheUtils.remove(TOKEN_CACHE, token);
		CacheUtils.remove(SEED_TOKEN_CACHE, seed);
	}

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.print(Digests.md5("15831642372") + "  ");
			System.out.print(Digests.md5("15831642372", SALT) + "  ");
			System.out.println(generateGUID());
		}
	}
}
