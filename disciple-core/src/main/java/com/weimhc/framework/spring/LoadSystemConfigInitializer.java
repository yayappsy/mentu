/**
 * 
 */
package com.weimhc.framework.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

/**
 * Description:加载系统配置文件 加载javamg.properties中以-S开头的配置文件
 * 
 * @author shaozuo
 *
 */
public class LoadSystemConfigInitializer implements WebApplicationInitializer {

	private static final String SYSTEM_PROPERTY_PREFIX = "-S";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/javamg.properties");
		try {
			prop.load(in);
			Enumeration<Object> iter = prop.keys();
			while (iter.hasMoreElements()) {
				String key = iter.nextElement().toString();
				String realKey = null;
				if (key.indexOf(SYSTEM_PROPERTY_PREFIX) == 0) {
					realKey = key.substring(2);
					System.setProperty(realKey, prop.getProperty(key));
					System.out.printf("set system parameter : %s = %s \n", realKey,
							prop.getProperty(key));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
