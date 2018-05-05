package com.weimhc.front.core.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.javamg.common.config.Global;

public class WebContextListener
		extends org.springframework.web.context.ContextLoaderListener {

	@Override
	public WebApplicationContext initWebApplicationContext(
			ServletContext servletContext) {
		if (!printKeyLoadMessage()) {
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}

	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 " + Global.getConfig("system.name")
				+ "  - Powered By " + Global.getConfig("system.projectName")
				+ "    \r\n");
		sb.append(
				"\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}
}
