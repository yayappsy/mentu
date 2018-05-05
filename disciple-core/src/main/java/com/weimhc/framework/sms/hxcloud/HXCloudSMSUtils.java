package com.weimhc.framework.sms.hxcloud;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.taobao.api.internal.util.RequestParametersHolder;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.internal.util.TaobaoUtils;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.sms.hxcloud.dto.req.HXCloudSMSRQ;
import com.weimhc.framework.sms.hxcloud.dto.resp.HXCloudSMSRS;

import net.sf.cglib.beans.BeanMap;

/***
 * 华信通讯云 短信发送工具类
 * 
 * @author szuo
 *
 */
public class HXCloudSMSUtils {

	private static Logger logger = LoggerFactory
			.getLogger(HXCloudSMSUtils.class);
	/**
	 * 用户ID
	 */
	private static String USER_ID = Global.getConfig("sms.hx.userId");// "CI2016000015";;
	/**
	 * 令牌
	 */
	private static String SECRET = Global.getConfig("sms.hx.secret");// "4a9ed0b198a54b9990dbbacde2f1994c";

	/**
	 * 短信API接口引用的地址有Base URL,由配置文件设定
	 * 
	 * 沙盒环境的Base URL：http://sandboxapi.huaxincloud.com:8081
	 * 
	 * 生产环境的Base URL：http://api.huaxincloud.com:8081
	 */
	private static String SETTING_BASE_URL = Global.getConfig("sms.hx.baseUrl");
	/**
	 * 短信API接口引用的地址,默认为生产环境
	 */
	private static String DEFAULT_BASE_URL = "http://api.huaxincloud.com:8081";

	@SuppressWarnings("unchecked")
	public static HXCloudSMSRS sendSMS(HXCloudSMSRQ hxCloudSMSRQ) {

		if (StringUtils.isBlank(hxCloudSMSRQ.getDynadatas())) {
			HXCloudSMSRS hxCloudSMSRS = new HXCloudSMSRS();
			hxCloudSMSRS.setErrorCode("-1");
			hxCloudSMSRS.setMsg("模板数据为空");
			return hxCloudSMSRS;
		}
		logger.debug(hxCloudSMSRQ.getDynadatas());

		try {
			hxCloudSMSRQ.setDynadatas(
					URLEncoder.encode(hxCloudSMSRQ.getDynadatas(), "utf-8"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		BeanMap beanMap = BeanMap.create(hxCloudSMSRQ);

		TaobaoHashMap tbmap = new TaobaoHashMap(beanMap);
		Map<String, String> mapParams = TaobaoUtils.cleanupMap(tbmap);
		return HXCloudSMSUtils.process(USER_ID, "putDynaSms", mapParams,
				SECRET);
	}

	public static HXCloudSMSRS process(String custId, String actionName,
			Map<String, String> mapParams, String secret) {
		HXCloudSMSRS hXCloudSMSRS = null;
		try {
			// 权限校验
			TaobaoHashMap tbmap = new TaobaoHashMap();
			RequestParametersHolder requestHolder = new RequestParametersHolder();
			requestHolder.setApplicationParams(tbmap);
			tbmap.put("timestamp", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date()));
			tbmap.put("sign_method", "hmac");
			tbmap.putAll(mapParams);

			try {
				boolean isHmac = "hmac"
						.equalsIgnoreCase(tbmap.get("sign_method"));
				String signBringUp = TaobaoUtils
						.signTopRequestNew(requestHolder, secret, isHmac);
				tbmap.put("sign", signBringUp);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}

			String sb = (StringUtils.isBlank(SETTING_BASE_URL)
					? DEFAULT_BASE_URL : SETTING_BASE_URL) + "/custom/" + custId
					+ "/sms/" + actionName + "/";
			// String sb = "http://api.huaxincloud.com:8081/custom/" + custId
			// + "/sms/" + actionName + "/";
			// String sb=
			// "http://api.huaxincloud.com:8081/custom/"+custId+"/sms/"+actionName+"/";
			URL url = new URL(sb);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url.toString());

			// 设置header
			StringEntity se = new StringEntity(JsonMapper.toJsonString(tbmap));
			se.setContentType("application/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=utf-8"));

			httpPost.setHeader("Content-Type",
					"application/json;charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(se);

			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			// 返回处理
			try {
				HttpEntity resEntity = httpResponse.getEntity();
				String ret = EntityUtils.toString(resEntity);

				hXCloudSMSRS = (HXCloudSMSRS) JsonMapper.fromJsonString(ret,
						HXCloudSMSRS.class);
				EntityUtils.consume(resEntity);
			} catch (IOException e) {
				logger.debug("执行HTTP Post请求" + url.toString() + "时，发生异常！");
			} finally {
				httpResponse.close();
			}
			return hXCloudSMSRS;
		} catch (IOException e) {
			logger.debug("内部错误，发生异常！" + e.getMessage());

		}
		return hXCloudSMSRS;

	}

	/***
	 * 生成 短信动态数据
	 * 
	 * @param dynadatas
	 * @return
	 */
	public static String generateDynadatas(
			List<? extends AbstractHXSMSTemplate> dynadata) {

		String dynadatas = JsonMapper.toJsonString(dynadata);

		if (StringUtils.isBlank(dynadatas)) {
			return "";
		}

		return dynadatas;
	}

	public static void main(String[] args) {

		try {
			List<TestHXSMSTemplate> dynadata = Lists.newArrayList();
			for (int i = 0; i < 10; i++) {
				TestHXSMSTemplate captchaHXSMSTemplate = new TestHXSMSTemplate();
				captchaHXSMSTemplate.setDestination("15831642372");
				dynadata.add(captchaHXSMSTemplate);
			}
			System.out.println(generateDynadatas(dynadata));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}