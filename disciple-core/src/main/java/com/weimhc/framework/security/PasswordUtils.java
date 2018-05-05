package com.weimhc.framework.security;

import com.thinkgem.javamg.common.security.Digests;
import com.thinkgem.javamg.common.utils.Encodes;

/**
 * 密码工具类，进行加密和密码验证等功能
 * 
 * @author szuo
 *
 */
public abstract class PasswordUtils {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword,
			String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return password.equals(
				Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}

	public static void main(String[] args) {

		System.out.println(entryptPassword("123456"));

		System.out.println(validatePassword("123456",
				"0e1f91a81942ff85032ffd7324fd746a0222d983816a020828c88887"));
	}
}
