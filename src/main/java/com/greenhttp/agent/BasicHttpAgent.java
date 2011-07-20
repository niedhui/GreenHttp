package com.greenhttp.agent;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import com.greenhttp.methods.Delete;
import com.greenhttp.methods.Get;
import com.greenhttp.methods.Post;
import com.greenhttp.util.Strings;

public abstract class BasicHttpAgent implements HttpAgent {
	protected String agentString;
	
	protected String baseUri;
	
	public BasicHttpAgent setBaseUri(String uri){
		if (!uri.startsWith("http")) {
			uri = "http://" + uri;
		}
		this.baseUri = uri;
		return this;
	}
	
	public BasicHttpAgent(String agentString) {
		this.agentString = agentString;
	}
	
	public Get get(String url) {
		return new Get(url, this);
	}

	public Post post(String url) {
		return new Post(url, this);
	}
	
	public Delete delete(String url){
		return new Delete(url, this);
	}

	public BasicHttpAgent useBasicAuth() {
		((DefaultHttpClient)getClient()).addRequestInterceptor(new HttpBasicAuthInterceptor(), 0);
		return this;
	}
	
	public BasicHttpAgent setCredentials(String username, String password) {
		if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
			clearCredentials();
		} else {
			((DefaultHttpClient)getClient()).getCredentialsProvider()
				.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(username, password));
		}
		return this;
	}
	
	public BasicHttpAgent clearCredentials(){
		((DefaultHttpClient)getClient()).getCredentialsProvider().clear();
		return this;
	}
	
	public BasicHttpAgent addRequestInterceptor(HttpRequestInterceptor interceptor) {
		((DefaultHttpClient)getClient()).addRequestInterceptor(interceptor);
		return this;
	}
	
	public BasicHttpAgent removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
		((DefaultHttpClient)getClient()).removeRequestInterceptorByClass(clazz);
		return this;
	}

	static class HttpBasicAuthInterceptor implements HttpRequestInterceptor {
		@Override
		public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {

			AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
			CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
			HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			if (authState.getAuthScheme() == null) {
				AuthScope authScope = new AuthScope(targetHost.getHostName(), targetHost.getPort());
				org.apache.http.auth.Credentials creds = credsProvider.getCredentials(authScope);
				if (creds != null) {
					authState.setAuthScheme(new BasicScheme());
					authState.setCredentials(creds);
				}
			}
		}
	}

	@Override
	public String baseUri() {
		return this.baseUri;
	}

}
