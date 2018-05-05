package com.weimhc.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.thinkgem.javamg.common.config.Global;

public class AlipayUtil {

	private static Logger logger = LoggerFactory.getLogger(AlipayUtil.class);

	public static final String ALIPAY_APPID = Global.getConfig("alipay.appId"); // "2017041106656431";

	public static String APP_PRIVATE_KEY = Global
			.getConfig("alipay.app.private.key");// "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCu561Cp3LKBqDVhsVfn2gRFNiT+a9MRkWZcHtHFpQVvaq3OlDk+N3Dy3PYx7v8H9Q90kg9EDHyPnXo8QrSjiuLbwfUHnHjrt2UihJmkz3JwRiNtn82DoqJti48zi5n7rSYeFW8ULASyMdeEe6KOFqs6VTyySwmraURgRYbzZnFfqY+qVbQDBA2xAvj//kZbdDNjNU5HgRBPw0/ZnRMF4GVP3He5aFOmYJ5psw7UfW6HsOBPdSaMst3XI92ez8asSIGleAFAmUmnZIfUcL0C5Av7WUQaQvICMbLKjTLwre9qVZtYuxa89cTVTnuV16vTyuu1m2emUKhWELiILaplG+bAgMBAAECggEBAKhDKWOeQ8gnaEMDTI4ZhLwqGdEBu6rdT8e1dmOFTSE9PibXa0tcP7xE1kYOoMKEvGJdEjHsiqBJhpV6jrbRHwMc5aMjRt2LNI/Z5KnZrO+TXFO45djTVGP3Z7QHho1mD0c/PPvVhA4OKf5NsfY3EMSYFKkc7+Sl2M9mKCbHPM/I1pAN95Jl8bRANv2NmB1B/AVBj7Gi+mj17yLI3qlEcxVKwMzRfGTUomK/2uwQY3PA3N1+oLHCrragXhJbwJ61rpPCyXcQVTe4Nmr1wVnUIX8ND6JeOHYscjZZAs9O91trXoOv6uHE+pgI55ZsavK2QMLcPh8/jCtO5vtibholxQECgYEA6Pe8atMkPJglikOgh/ptsv1k21lxU5j4z/QuPPbbMG4z6j0FNK4loMjt8XEiVn7D0djxLgdhiD8lgA8veRQjYY0t+SclPMV+tJrjLcyEpTYsE85Y8w9aMVyniWthHhB7x+69B5cIXZRqT3EDbtrjliuHyKhMjDCx5Zfm2Ixhj2sCgYEAwDJpAlW5hX0WtqXoqQuqGIV8tGETpTUOG6u6JJ0sWZ+hpHJYL1Z7+Rz6vXEJ6TY0lC1vDscqp4W5RCUnHtMbdFw6/SSB7XRkvoP10ztBKbhfUit+6u9Me6DxmEZrg1nQpZgWtqoxloxBB8rItVTBpn9CS/n6zwSpzF+HfxUHnJECgYEAyaPDPWLK9ndp5qZqB8fFsyqHZSOhZw38LWHXs4SmauC3poJyQwqIHwZKjLOzWNIXg0YWWhShYl/lgVWjbVT02rZrmD89h8AYzxFQmI9wL98Xf30WmCotkpPznomGYZM6z+3fRvZ+kyCmc2QJ6KUkXxIGULL6rkxnbaVwdPXsa0kCgYBbHUrU9Yje9L841Yxn34TC3T2SUdc7YdyPV6kGRJDK6Dd8O0z9HBXnskpYVudo7O6eNK/KpLbe9jEvUiGqplAEqNi6O31w8dX0z6BRhGzO934Q+VfQzqNGlLUUCIZdnc2hQ2m5LRQXF+SYahUeidDIOtpWJGs3izFoFv2oTHLwUQKBgQC/gcGxV2V4Q8g7TkkWMgx4Czkq87U4rUhjHAxkR8toKbrPPH8EYQuXE+nSbM5doWC8IwXvjzvpU0wvQD64tdQLA3UecwSM413h7cDZcS4gaB0NpNT1hDxht2mgST0GbfGmWZznKegR6blpgPEciIwj2lS2hTvEC9sjbwQHcnRDLQ==";
	public static String ALIPAY_PUBLIC_KEY = Global
			.getConfig("alipay.public.key"); // "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo++xxN0ssYUGie2wG9rmW5fdoPJNL/cyMtE8DG2DnLEljmpBFseMFJy1F3Ek2wjB5wCxssMnyzJ9iC6yFjSaVjXIiFpMBMWszaxCSUPuudCktCIFB71jE3SXyc2yUCLlsR3l8Qcxz7NMYBbhSLgPZtigNQ5fIK9oCyvV8MS1PD1GnFe4LFtfsoDnUGyMWM9jqNUl1NHW4BlrFdETMs2+RN1wd4H+CH0vTsPl3Hs2habhjfW8lhtOeYku1Rel5rRX/bBE/Z5ohighXISwTwW/QQz4818J3N6tLRxer2ZxvmwE+Z97ZahgqYAL0tx/6+JvqZbH2yT812kLNb0E54uNTwIDAQAB";

	// 统一收单交易创建接口
	private static AlipayClient alipayClient = null;

	public static AlipayClient getAlipayClient() {
		if (alipayClient == null) {
			synchronized (AlipayUtil.class) {
				if (null == alipayClient) {
					alipayClient = new DefaultAlipayClient(
							"https://openapi.alipay.com/gateway.do",
							ALIPAY_APPID, APP_PRIVATE_KEY,
							AlipayConstants.FORMAT_JSON,
							AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY,
							"RSA2");
				}
			}
		}
		return alipayClient;
	}

	/**
	 * 对支付参数信息进行签名
	 * 
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, String rsaKey) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		boolean first = true;
		for (String key : keys) {
			if (first) {
				first = false;
			} else {
				authInfo.append("&");
			}
			authInfo.append(key).append("=").append(map.get(key));
		}

		return SignUtil.sign(authInfo.toString(), rsaKey);
	}

	/**
	 * 返回签名编码拼接url
	 * 
	 * @param params
	 * @param isEncode
	 * @return
	 */
	public static String getSignEncodeUrl(Map<String, String> map,
			boolean isEncode) {
		String sign = map.get("sign");
		String encodedSign = "";
		if (map != null) {
			map.remove("sign");
			List<String> keys = new ArrayList<String>(map.keySet());
			// key排序
			Collections.sort(keys);

			StringBuilder authInfo = new StringBuilder();

			boolean first = true;// 是否第一个
			for (String key : keys) {
				if (first) {
					first = false;
				} else {
					authInfo.append("&");
				}
				authInfo.append(key).append("=");
				if (isEncode) {
					try {
						authInfo.append(URLEncoder.encode(map.get(key),
								AlipayConstants.CHARSET_UTF8));
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage(), e);
					}
				} else {
					authInfo.append(map.get(key));
				}
			}

			try {
				encodedSign = authInfo.toString() + "&sign="
						+ URLEncoder.encode(sign, AlipayConstants.CHARSET_UTF8);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return encodedSign.replaceAll("\\+", "%20");
	}

}