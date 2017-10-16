package com.bin.apache.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;

@SuppressWarnings("deprecation")
public class HttpClientUtil {

	private static final String HTTP_TYPE = "http";
	
	private static final String HTTPS_TYPE = "https";

	/**
	 * http get请求
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String doGet(String url, String encode) {

		HttpGet httpget = new HttpGet(url);

		/*RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.DEFAULT)
				.setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

		RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		httpget.setConfig(requestConfig);*/

		return excuteMethod(httpget);
	}

	/**
	 * http post请求
	 * @param url
	 * @param postData
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String doPost(String url, String postData, String encode) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8");
		StringEntity entity = new StringEntity(postData, "UTF-8");
		httpPost.setEntity(entity);
		return excuteMethod(httpPost);
	}
	
	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	private static String excuteMethod(HttpUriRequest httpRequest) {
		String responseBody = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getHttpClient(httpRequest.getURI().toString());
			responseBody = httpClient.execute(httpRequest, getResponseHandler());
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} finally {
			close(httpClient);
		}
		return responseBody;
	}

	private static ResponseHandler<String> getResponseHandler() {
		return (response -> {
			HttpEntity entity = response.getEntity();
			return entity == null ? null : EntityUtils.toString(entity);
		});
	}

	/**
	 * 从连接池中获取httpClient
	 * @return
	 */
	private static CloseableHttpClient getHttpClient(String url) {
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
		ConnectionSocketFactory socketFactory = new PlainConnectionSocketFactory();
		registryBuilder.register(HTTP_TYPE, socketFactory);
		if (StringUtils.isNotEmpty(url) && url.startsWith(HTTPS_TYPE)) {//https请求，信任所有链接
			// 指定信任密钥存储对象和连接套接字工厂
			try {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				// 信任任何链接
				TrustStrategy trustStrategy = ((x50909Certificates, s) -> true);
				SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, trustStrategy).build();
				LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				registryBuilder.register(HTTPS_TYPE, sslSF);
			} catch (Exception e) {
			}
		}
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		// 设置连接管理器
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		// connManager.setDefaultConnectionConfig(connConfig);
		// connManager.setDefaultSocketConfig(socketConfig);
		// 构建客户端
		return HttpClientBuilder.create().setConnectionManager(connectionManager).build();
	}

	/**
	 * 关闭链接
	 * @param httpClient
	 */
	private static void close(CloseableHttpClient httpClient) {
		try {
			if (null != httpClient) {
				httpClient.close();
			}
		} catch (IOException e) {
		}
	}
}
