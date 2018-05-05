package com.weimhc.framework.http;

import java.io.Closeable;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * Create a httpClient instance
	 *
	 * @param isSSL
	 *            if the request is protected by ssl
	 * @return HttpClient instance
	 */
	public static HttpClient getHttpClient(boolean isSSL) {
		CloseableHttpClient client = null;

		if (isSSL) {
			try {
				HostnameVerifier verifier = new HostnameVerifier() {

					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};

				TrustManager[] tm = new TrustManager[] { new X509TrustManager() {

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}

					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}
				} };

				SSLContext sslContext = SSLContext.getInstance("SSL");

				sslContext.init(null, tm, new SecureRandom());

				client = HttpClients.custom().setSSLContext(sslContext)
						.setSSLHostnameVerifier(verifier).build();
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage(), e);
			} catch (KeyManagementException e) {
				logger.error(e.getMessage(), e);
				;
			}

		} else {
			client = HttpClients.createDefault();
		}

		return client;
	}

	/**
	 * 关闭返回
	 * 
	 * @param response
	 */
	public static void closeResponse(HttpResponse response) {
		if (response != null && response instanceof Closeable) {
			try {
				((Closeable) response).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
				;
			}
		}

	}
}
