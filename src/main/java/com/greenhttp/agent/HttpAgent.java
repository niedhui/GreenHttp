package com.greenhttp.agent;

import org.apache.http.client.HttpClient;

public interface HttpAgent {

	HttpClient getClient();
	
	HttpAgent setBaseUri(String uri);

	String baseUri();
}
