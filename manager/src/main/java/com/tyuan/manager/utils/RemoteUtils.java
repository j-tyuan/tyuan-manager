package com.tyuan.manager.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RemoteUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public static final String CHARSET = "UTF-8";

	public static String postJson(String url, String jsonParam) {
		logger.info("[postJson]进行postJson请求，url： {}.", url);
		CloseableHttpClient httpClient = HttpClientUtils.getHttpClient();
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		HttpEntity entity = null;
		try {
			HttpClientUtils.setTimeOut(post);
			StringEntity s = new StringEntity(jsonParam, "UTF-8"); // 中文乱码在此解决
			s.setContentType("application/json");
			s.setContentEncoding("UTF-8");
			post.setEntity(s);
			logger.info("[postJson]postJson请求参数： {}.", jsonParam);
			response = httpClient.execute(post);
			// 获取响应实体
			entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
				String entityInfoStr = EntityUtils.toString(entity, CHARSET);
				logger.info("[postJson]postJson请求结果： {}", entityInfoStr);
				return entityInfoStr;
			} else {
				logger.error("[postJson][{}]HTTP ERROR response status={}, response body={}.",
						response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), CHARSET));
			}
		} catch (IOException e) {
			logger.error("[postJson][{}][{}].", e.toString(), e);
		} finally {
			// 关闭连接,释放资源
			try {
				if (response != null) {
					response.close();
				}
				EntityUtils.consumeQuietly(entity);
			} catch (Exception e) {
				logger.error("[postJson] exception.", e);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		HttpClientUtils.initHttpClientPool();
		while (2 == 2) {
			postJson("https://blog.csdn.net/weixin_39032575/article/details/81604336", "");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 鉴权验证 HTTP Basic 机制 请求极光接口
	 */
	public static String postJson(String url, String jsonParam, String key, String secret,String pid) {
		CloseableHttpClient httpClient = HttpClientUtils.getHttpClient();

		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		HttpEntity entity = null;
		try {
			HttpClientUtils.setTimeOut(post);
			StringEntity s = new StringEntity(jsonParam, CHARSET); // 解决中文乱码问题
			s.setContentType("application/json");
			s.setContentEncoding(CHARSET);
			post.setEntity(s);
			post.setHeader("accept", "application/json");
			post.setHeader("Content-Type", "application/json");
			if (StringUtils.isNotEmpty(pid)) {
				post.setHeader("productId", pid);
			}
			// 鉴权验证 HTTP Basic 机制
			String base64Str = key + ":" + secret;
			String base64Auth = new String(Base64.encodeBase64(base64Str.getBytes(CHARSET)));
			post.setHeader("Authorization", "Basic " + base64Auth);
			logger.info("[postJson]postJson url={}, base64Auth={}.", url, base64Auth);
			response = httpClient.execute(post);
			// 获取响应实体
			entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
				String entityInfoStr = EntityUtils.toString(entity, CHARSET);
				return entityInfoStr;
			} else {
				logger.error("[postJson][{}]HTTP ERROR response status={}, response body={}.", urlProcess(url),
						response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), CHARSET));
			}
		} catch (IOException e) {
			logger.error("[postJson][{}][{}].", urlProcess(url), e.toString(), e);
		} finally {
			// 关闭连接,释放资源
			try {
				if (response != null) {
					response.close();
				}
				EntityUtils.consumeQuietly(entity);
			} catch (Exception e) {
				logger.error("[postJson][{}]response.close() exception.", urlProcess(url), e);
			}
		}
		return null;
	}

	public static String urlProcess(String url) {
		if (StringUtils.isNotBlank(url) && url.contains("?")) {
			url = url.substring(0, url.indexOf("?"));
		}
		return url;
	}

}
