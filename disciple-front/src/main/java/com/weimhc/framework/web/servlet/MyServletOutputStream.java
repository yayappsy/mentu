package com.weimhc.framework.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class MyServletOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream bout;

	public MyServletOutputStream(ByteArrayOutputStream bout) {
		this.bout = bout;
	}

	@Override
	public void write(int b) throws IOException {
		this.bout.write(b);
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		// TODO Auto-generated method stub

	}

}
