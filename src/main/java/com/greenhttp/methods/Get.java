package com.greenhttp.methods;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;

import com.greenhttp.agent.HttpAgent;


public class Get extends HttpMethod{

	public Get(String url,HttpAgent agent) {
		super(url,agent);
	}

	@Override
	protected HttpUriRequest createRequest() {
		return new HttpGet(url + "?" + URLEncodedUtils.format(getStringParams(), HTTP.UTF_8));
	}
	
}
