package com.greenhttp.agent;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class AndroidHttpAgent extends BasicHttpAgent {
	private AbstractHttpClient httpClient;

	public AndroidHttpAgent(String agentString) {
		super(agentString);
		this.httpClient = create(agentString);
	}

	@Override
	public HttpClient getClient() {
		return httpClient;
	}
	
	private static final int SOCKET_OPERATION_TIMEOUT = 60 * 1000;

	@SuppressWarnings("deprecation")
	public static AbstractHttpClient create(String agentString) {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		HttpParams params = createHttpParams();
		HttpProtocolParams.setUserAgent(params, agentString);
		ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);
		return new DefaultHttpClient(manager,params);
	}

	private static HttpParams createHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setStaleCheckingEnabled(params, false);

		HttpConnectionParams.setConnectionTimeout(params, SOCKET_OPERATION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_OPERATION_TIMEOUT);
		HttpConnectionParams.setSocketBufferSize(params, 8192);
		HttpClientParams.setRedirecting(params, false);
		return params;
	}

}
