package com.weimhc.framework.http.invoker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.weimhc.framework.http.HttpClientUtil;
import com.weimhc.framework.http.wrapper.HeaderWrapper;
import com.weimhc.framework.http.wrapper.ParameterWrapper;
import com.weimhc.framework.http.wrapper.ResponseWrapper;
import com.weimhc.framework.web.utils.MessageSourceUtils;

public class HttpClientRestAPIInvoker {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientRestAPIInvoker.class);

	public static HttpClientRestAPIInvoker invoker = new HttpClientRestAPIInvoker();

	/**
	 * Get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * Get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url, ParameterWrapper queryWrapper) {
		ResponseWrapper responseWrapper = sendRequest(HttpMethod.GET, url, null, queryWrapper, null,
				null, false);
		return (String) responseWrapper.getResponseBody();
	}

	/**
	 * 发送请求
	 * 
	 * @param method
	 *            HTTP request methods
	 * @param url
	 *            请求路径
	 * @param header
	 *            请求头
	 * @param query
	 *            查询参数
	 * @param formData
	 *            表单参数
	 * @param body
	 *            请求body
	 * @param 是否返回json数据
	 *            表单参数
	 * @return
	 */
	public static ResponseWrapper sendRequest(HttpMethod method, String url, HeaderWrapper header,
			ParameterWrapper query, ParameterWrapper formData, Object body, boolean ifJsonResult) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		if (StringUtils.isBlank(url)) {
			String msg = MessageSourceUtils.getMessage("api.error.shouldNotBlank",
					new Object[] { "Parameter url" });
			responseWrapper.addError(msg);
		}
		/*if (!RestAPIUtils.match(
				"http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url)) {
			String msg = MessageSourceUtils.getMessage(
					"api.error.invalid.format",
					new Object[] { "Parameter url" });
			responseWrapper.addError(msg);
		}*/
		if (null == header) {
			header = HeaderWrapper.getDefaultHeader();
		}

		if (responseWrapper.hasError()) {
			return responseWrapper;
		}
		// 将其转换为json字符形式
		String bodyJsonStr = null;
		if (body != null) {
			bodyJsonStr = JsonMapper.toJsonString(body);
		}

		logger.debug("=============Request=============");
		logger.debug("Method: " + method);
		logger.debug("URL: " + url);
		logger.debug("Header: " + header);
		logger.debug("Request Body: " + ((null == bodyJsonStr) ? "" : bodyJsonStr));
		logger.debug("Query: " + query);
		logger.debug("===========Request End===========");

		HttpClient client = HttpClientUtil
				.getHttpClient(StringUtils.startsWithIgnoreCase(url, "HTTPS"));
		URI target = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			builder.addParameters(query.getParameters());
			target = builder.build();
		} catch (URISyntaxException e) {
			responseWrapper.addError(e.getMessage());
			return responseWrapper;
		}

		HttpUriRequest request = null;
		try {
			if (HttpMethod.POST.equals(method)) {
				List<NameValuePair> formparams = formData.getParameters();
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				request = new HttpPost(target);
				((HttpPost) request).setEntity(uefEntity);
				logger.debug(request.toString());
			} else if (HttpMethod.PUT.equals(method)) {
				request = new HttpPut(target);
			} else if (HttpMethod.GET.equals(method)) {
				request = new HttpGet(target);
			} else if (HttpMethod.DELETE.equals(method)) {
				request = new HttpDelete(target);
			} else {
				String msg = MessageSourceUtils.getMessage("api.error.unsupport.method",
						new Object[] { method, "Http Method" });
				logger.error(msg);
				throw new RuntimeException(msg);
			}
		} catch (UnsupportedEncodingException e) {
			responseWrapper.addError(e.getMessage());
			return responseWrapper;
		}

		if (null != bodyJsonStr) {
			((HttpEntityEnclosingRequestBase) request)
					.setEntity(new StringEntity(bodyJsonStr, "UTF-8"));
		}
		// 构建消息头
		request.setHeaders(header.getHeadersArray());

		// 请求数据
		HttpResponse response = null;
		try {
			response = client.execute(request);
			responseWrapper = readResponse(responseWrapper, response, false, ifJsonResult);
		} catch (IOException e) {
			responseWrapper.addError(e.getMessage());
			return responseWrapper;
		} finally {
			HttpClientUtil.closeResponse(response);
		}

		logger.debug("=============Response=============");
		logger.debug(responseWrapper.toString());
		logger.debug("===========Response End===========");
		return responseWrapper;
	}

	/**
	 * 读取response中的信息
	 * 
	 * @param responseWrapper
	 * @param response
	 *            返回消息
	 * @param isFile
	 *            是返回文件请求
	 * @param ifJsonResult
	 *            是否返回json字符串
	 * @return
	 */
	private static ResponseWrapper readResponse(ResponseWrapper responseWrapper,
			HttpResponse response, boolean isFile, boolean ifJsonResult) {
		HttpEntity entity = response.getEntity();
		if (null != entity) {
			responseWrapper.setResponseStatus(response.getStatusLine().getStatusCode());
			Object responseContent;
			try {
				if (isFile) {
					responseContent = entity.getContent();
				} else {
					responseContent = EntityUtils.toString(entity, "UTF-8");
					EntityUtils.consume(entity);
				}
			} catch (ParseException e) {
				responseWrapper.addError(e.getMessage());
				return responseWrapper;
			} catch (IOException e) {
				responseWrapper.addError(e.getMessage());
				return responseWrapper;
			}

			if (!isFile && ifJsonResult) {
				ObjectNode responseNode;
				ObjectMapper mapper = new ObjectMapper();
				JsonFactory factory = mapper.getFactory();
				JsonParser jp;
				try {
					jp = factory.createParser(responseContent.toString());
					logger.debug(responseContent.toString());
					responseNode = mapper.readTree(jp);
					responseWrapper.setResponseBody(responseNode);
				} catch (IOException e) {
					logger.debug(MessageSourceUtils.getMessage("api.error.str2json"));
					responseWrapper.addError(MessageSourceUtils.getMessage("api.error.str2json"));
				}
			} else {
				responseWrapper.setResponseBody(responseContent);
			}
		}
		return responseWrapper;
	}

}
