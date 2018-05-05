package com.easemob.server.example.comm;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.thinkgem.javamg.common.config.Global;

import io.swagger.client.ApiException;
import io.swagger.client.api.AuthenticationApi;
import io.swagger.client.model.Token;

/**
 * Created by easemob on 2017/3/14. modified by zsm 2017/09/22
 */
public class TokenUtil {
	public static String GRANT_TYPE = Global.getConfig("easemob.grantType");
	private static String CLIENT_ID = Global.getConfig("easemob.client.id");
	private static String CLIENT_SECRET = Global.getConfig("easemob.client.secret");
	private static Token BODY;
	private static AuthenticationApi API = new AuthenticationApi();
	private static String ACCESS_TOKEN;
	private static Double EXPIREDAT = -1D;
	private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

	/**
	 * get token from server
	 */
	static {
		BODY = new Token().clientId(CLIENT_ID).grantType(GRANT_TYPE).clientSecret(CLIENT_SECRET);
	}

	public static void initTokenByProp() {
		String resp = null;
		try {
			resp = API.orgNameAppNameTokenPost(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, BODY);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
		}
		Gson gson = new Gson();
		Map map = gson.fromJson(resp, Map.class);
		ACCESS_TOKEN = " Bearer " + map.get("access_token");
		EXPIREDAT = System.currentTimeMillis() + (Double) map.get("expires_in");
	}

	/**
	 * get Token from memory
	 *
	 * @return
	 */
	public static String getAccessToken() {
		if (ACCESS_TOKEN == null || isExpired()) {
			initTokenByProp();
		}
		return ACCESS_TOKEN;
	}

	private static Boolean isExpired() {
		return System.currentTimeMillis() > EXPIREDAT;
	}

}
