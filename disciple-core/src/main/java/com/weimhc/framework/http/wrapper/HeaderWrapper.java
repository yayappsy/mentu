package com.weimhc.framework.http.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.http.MediaType;

public class HeaderWrapper implements Serializable {

	/**
	 * 用户代理
	 */
	private static final String USER_AGENT = "User-Agent";

	/**
	 * 
	 */
	private static final String ACCEPT_LANGUAGE = "Accept-Language";

	/**
	 * 
	 */
	private static final String ACCEPT_ENCODING = "Accept-Encoding";

	/**
	 * 
	 */
	private static final String ACCEPT_CHARSET = "Accept-Charset";

	/**
	 * 
	 */
	private static final long serialVersionUID = -745987962847038245L;

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static final String HEADER_AUTH = "Authorization";

	private static final String RESTRICT_ACCESS = "restrict-access";

	private static final String THUMBNAIL = "thumbnail";

	private static final String SHARE_SECRET = "share-secret";

	private static final String ACCEPT = "Accept";

	private List<Header> headers = new ArrayList<Header>();

	public static HeaderWrapper newInstance() {
		return new HeaderWrapper();
	}

	public HeaderWrapper addHeader(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return this;
		}

		headers.add(new BasicHeader(key, value));
		return this;
	}

	public HeaderWrapper addHeader(Header header) {
		if (header == null) {
			return this;
		}
		headers.add(header);
		return this;
	}

	public HeaderWrapper addJsonContentHeader() {
		return addHeader(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
	}

	public HeaderWrapper addApplicationFormUrlencodedValueContentHeader() {
		return addHeader(HEADER_CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public Header[] getHeadersArray() {
		return headers.toArray(new Header[0]);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Header header : headers) {
			sb.append("[").append(header.getName()).append(":").append(header.getValue())
					.append("] ");
		}

		return sb.toString();
	}

	public static HeaderWrapper getJsonHeader() {
		return HeaderWrapper.newInstance().addJsonContentHeader();
	}

	public static HeaderWrapper getFormHeader() {
		return HeaderWrapper.newInstance().addApplicationFormUrlencodedValueContentHeader();
	}

	public static HeaderWrapper getDefaultHeader() {
		HeaderWrapper wrapper = HeaderWrapper.newInstance();
		wrapper.addHeader(new BasicHeader(ACCEPT,
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		wrapper.addHeader(new BasicHeader(ACCEPT_CHARSET, "utf-8;q=0.7,*;q=0.7"));

		wrapper.addHeader(new BasicHeader(ACCEPT_ENCODING, "gzip, deflate"));

		wrapper.addHeader(new BasicHeader(ACCEPT_LANGUAGE, "zh-cn,zh;q=0.5"));

		wrapper.addHeader(new BasicHeader(USER_AGENT,
				"Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2"));
		return wrapper;
	}

}
