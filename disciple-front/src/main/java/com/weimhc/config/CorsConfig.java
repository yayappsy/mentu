package com.weimhc.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域访问
 * 
 * @author laozh
 *
 */
public class CorsConfig {
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:63342")
						.allowedMethods(HttpMethod.GET.name(),
								HttpMethod.HEAD.name(), HttpMethod.POST.name(),
								HttpMethod.PUT.name())
						.allowedHeaders(CrossOrigin.DEFAULT_ALLOWED_HEADERS)
						.exposedHeaders(HttpHeaders.SET_COOKIE)
						.allowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS)
						.maxAge(CrossOrigin.DEFAULT_MAX_AGE);

			}
		};
	}

}
