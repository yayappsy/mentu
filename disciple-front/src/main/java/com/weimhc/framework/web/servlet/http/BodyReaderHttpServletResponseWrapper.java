package com.weimhc.framework.web.servlet.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.weimhc.framework.web.servlet.MyServletOutputStream;

/**
 * response包装类
 * 
 * <p>
 * 因为流只能读一次, 读了就没有了, 为了后面的代码还能够取得流, 我们应该还需要将其写出去才行
 * </p>
 * 
 * @author chenyuan
 * @version Date: 12/31/14 Time:8:49 PM
 */
public class BodyReaderHttpServletResponseWrapper
		extends HttpServletResponseWrapper {
	private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	private PrintWriter pw;
	private HttpServletResponse response;

	public BodyReaderHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyServletOutputStream(bout);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		pw = new PrintWriter(new OutputStreamWriter(bout,
				this.response.getCharacterEncoding()));
		return pw;
	}

	public byte[] getBuffer() {
		try {
			if (pw != null) {
				pw.close();
			}
			if (bout != null) {
				bout.flush();
				return bout.toByteArray();
			}

			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
