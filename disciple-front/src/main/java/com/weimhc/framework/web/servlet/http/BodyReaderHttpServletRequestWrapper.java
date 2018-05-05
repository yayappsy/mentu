package com.weimhc.framework.web.servlet.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.weimhc.framework.utils.RequestUtils;

/**
 * request包装类
 * 
 * <p>
 * 因为流只能读一次, 读了就没有了, 为了后面的代码还能够取得流, 我们应该还需要将其写出去才行
 * </p>
 * 
 * @author chenyuan
 * @version Date: 12/31/14 Time:8:49 PM
 */
public class BodyReaderHttpServletRequestWrapper
		extends HttpServletRequestWrapper {

	/**
	 * 将request body先保存
	 */
	private final String body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
			throws IOException {
		super(request);
		body = RequestUtils.getRequestBodyString(request);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(
				body.getBytes(Charset.forName("UTF-8")));

		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}

	/**
	 * 获取Request body
	 * 
	 * @return
	 */
	public String getBody() {
		return this.body;
	}
}
