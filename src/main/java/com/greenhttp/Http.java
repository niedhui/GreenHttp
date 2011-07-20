package com.greenhttp;

import org.apache.http.HttpRequestInterceptor;

import com.greenhttp.agent.AndroidHttpAgent;
import com.greenhttp.agent.BasicHttpAgent;
import com.greenhttp.agent.CommonHttpAgent;
import com.greenhttp.methods.Delete;
import com.greenhttp.methods.Get;
import com.greenhttp.methods.Post;

public class Http {

	private static BasicHttpAgent defaultHttpAgent;
	
	public static final String DEFAULT_AGENT_STRING = "NIE-HTTP";

	public static Get get(String url) {
		return getHttpAgent().get(url);
	}
	
	public static Post post(String url){
		return getHttpAgent().post(url);
	}
	
	public static Delete delete(String url){
		return getHttpAgent().delete(url);
	} 
	
	public static void init(String agentString) {
		defaultHttpAgent = new CommonHttpAgent(agentString);
	}

	public static void initForAndroid(String agentString) {
		defaultHttpAgent = new AndroidHttpAgent(agentString);
	}
	
	public static void initWithAgent(BasicHttpAgent agent){
		defaultHttpAgent = agent;
	}
	
	public static void setCredentials(String username,String password){
		getHttpAgent().setCredentials(username, password);
	}
	
	public static void clearCredentials(){
		getHttpAgent().clearCredentials();
	}
	
	public static void addRequestInterceptor(HttpRequestInterceptor interceptor) {
		getHttpAgent().addRequestInterceptor(interceptor);
	}
	
	public static void  removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
		getHttpAgent().removeRequestInterceptorByClass(clazz);
	}
	
	public static BasicHttpAgent getHttpAgent(){
		if( defaultHttpAgent == null){
			defaultHttpAgent = new CommonHttpAgent(DEFAULT_AGENT_STRING);
		}
		return defaultHttpAgent;
	}

	public static String baseUri() {
		return getHttpAgent().baseUri();
	}
	
}
