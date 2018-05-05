/*
 * 
 * 
 * 
 */
package com.weimhc.framework.service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.utils.RSAUtils;

/**
 * Service - RSA安全
 * 
 * 
 * 
 */
@Service
@Transactional(readOnly = true)
public class RSAService {

	/** "私钥"参数名称 */
	private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "privateKey";

	public RSAPublicKey generateKey(HttpServletRequest request) {
		Assert.notNull(request);
		KeyPair keyPair = RSAUtils.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		HttpSession session = request.getSession();
		session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
		return publicKey;
	}

	public void removePrivateKey(HttpServletRequest request) {
		Assert.notNull(request);
		HttpSession session = request.getSession();
		session.removeAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
	}

	/**
	 * 从request中获取加密的密码
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public String decryptParameter(String name, HttpServletRequest request) {
		Assert.notNull(request);
		if (name != null) {
			HttpSession session = request.getSession();
			RSAPrivateKey privateKey = (RSAPrivateKey) session
					.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
			String parameter = request.getParameter(name);
			if (privateKey != null && StringUtils.isNotEmpty(parameter)) {
				return RSAUtils.decrypt(privateKey, parameter);
			}
		}
		return null;
	}

	/**
	 * 直接校验加密的字符串
	 * 
	 * @param enPassword
	 * @param request
	 * @return
	 */
	public String decryptPassword(String enPassword,
			HttpServletRequest request) {
		Assert.notNull(request);
		if (enPassword != null) {
			HttpSession session = request.getSession();
			RSAPrivateKey privateKey = (RSAPrivateKey) session
					.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
			if (privateKey != null) {
				return RSAUtils.decrypt(privateKey, enPassword);
			}
		}
		return null;
	}

}