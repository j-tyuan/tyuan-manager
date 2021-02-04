package com.tyuan.manager.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@SuppressWarnings("deprecation")
public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private static final String HTTP = "com/tyuan/common/http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	@SuppressWarnings("deprecation")
	private static SSLContextBuilder builder = null;

	/**
	 * 初始化HttpClient
	 */
	public static synchronized void initHttpClientPool() {
		if (cm == null) {
			logger.info("[initHttpClientPool]. Init HttpClient Pool");
			initClient();
		}
	}

	public static CloseableHttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	@SuppressWarnings("deprecation")
	private static void initClient() {
		try {
			builder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = buildSSLConnectionSocketFactory();

			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setDefaultMaxPerRoute(10);
			cm.setMaxTotal(20);// max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
		try {
			return new SSLConnectionSocketFactory(createIgnoreVersifySSL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SSLConnectionSocketFactory.getSocketFactory();
	}

	private static SSLContext createIgnoreVersifySSL() {
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSLv3");
			sslContext.init(null, new TrustManager[] { trustManager }, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sslContext;
	}

	/**
	 * 设置超时时间
	 */
	public static void setTimeOut(HttpRequestBase httpRequest) {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(2000)
				.setConnectionRequestTimeout(2000).build();
		httpRequest.setConfig(requestConfig);
	}

	/**
	 * @date 2019年10月29日
	 * @todo 单独设置超时时间
	 */
	public static void setSocketTimeOut(HttpRequestBase httpRequest, int socketTimeOut) {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(2000)
				.setConnectionRequestTimeout(2000).build();
		httpRequest.setConfig(requestConfig);
	}

}
