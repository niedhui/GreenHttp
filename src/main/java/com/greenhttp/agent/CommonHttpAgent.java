package com.greenhttp.agent;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class CommonHttpAgent extends BasicHttpAgent {
	private DefaultHttpClient httpClient;

	public CommonHttpAgent(String agentString) {
		super(agentString);
		httpClient = create(agentString);
	}

	@Override
	public HttpClient getClient() {
		return httpClient;
	}
	
	private static final int TIMEOUT = 60;

	public static DefaultHttpClient create(String agentString) {
		final SchemeRegistry supportedSchemes = new SchemeRegistry();
		supportedSchemes.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(supportedSchemes);
		HttpParams params = createHttpParams();
		HttpProtocolParams.setUserAgent(params, agentString);
		DefaultHttpClient client = new DefaultHttpClient(ccm, params);
		
		return client;
	}

	//TODO: duplicated with android http client factory
	private static HttpParams createHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setStaleCheckingEnabled(params, false);

		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT * 1000);
		HttpConnectionParams.setSoTimeout(params, TIMEOUT * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		HttpClientParams.setRedirecting(params, false);
		return params;
	}

}
