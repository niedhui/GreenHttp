package com.greenhttp.methods;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;

import com.greenhttp.agent.HttpAgent;

public class Delete extends HttpMethod {

	public Delete(String url, HttpAgent agent) {
		super(url, agent);
	}

	@Override
	protected HttpUriRequest createRequest() {
		return new HttpDelete(url + "?" + URLEncodedUtils.format(getStringParams(), HTTP.UTF_8));
	}

}
