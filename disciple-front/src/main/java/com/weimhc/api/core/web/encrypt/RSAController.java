/*
 * 
 * 
 * 
 */
package com.weimhc.api.core.web.encrypt;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.service.RSAService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 获取公钥 Controller
 * 
 * 
 * 
 */
@ApiIgnore
@Controller("apiRSAController")
@RequestMapping("${apiPath}/rsa")
public class RSAController extends ApiBaseController {

	@Resource
	private RSAService rsaService;

	/**
	 * 公钥
	 */
	@RequestMapping(value = "/publicKey", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> publicKey(HttpServletRequest request) {
		RSAPublicKey publicKey = rsaService.generateKey(request);
		logger.debug(request.getSession().getId());

		Map<String, String> data = new HashMap<String, String>();
		data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		data.put("exponent",
				Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		return data;
	}

}