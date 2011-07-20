package com.greenhttp.methods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import com.greenhttp.Http;
import com.greenhttp.agent.HttpAgent;
import com.greenhttp.exception.UncheckedIOException;
import com.greenhttp.response.Response;
import com.greenhttp.util.Strings;

public abstract class HttpMethod {

	protected String url;

	protected HttpAgent agent;
	
	protected List<BasicNameValuePair> stringParams = new ArrayList<BasicNameValuePair>();
	
	public HttpMethod(String url,HttpAgent agent){
		if(!url.startsWith("http")){
			url = Http.baseUri() + url;
		}
		this.url = url;
		this.agent = agent;
	}
	
	public HttpMethod with(String key, Double value) {
		return with(key,String.valueOf(value));
	}
	
	public HttpMethod with(String key, Integer value) {
		return with(key,String.valueOf(value));
	}

	public HttpMethod with(String key, String value) {
		if (Strings.isNotEmpty(key) && value != null) {
			stringParams.add(new BasicNameValuePair(key, value));
		}
		return this;
	}
	
	public HttpMethod with(String key, Object value) {
		if(value != null) {
			with(key,value.toString());
		}
		return this;
	}
	
	public HttpMethod with(Map<String,Object> params) {
		Set<Entry<String, Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			with(entry.getKey(),entry.getValue());
		}
		return this;
	}

	public Response go() {
		HttpUriRequest request = createRequest();
		try {
			HttpResponse response = agent.getClient().execute(request);
			return new Response(response);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	protected abstract HttpUriRequest createRequest();

	protected List<BasicNameValuePair> getStringParams(){
		return this.stringParams;
	}
}
