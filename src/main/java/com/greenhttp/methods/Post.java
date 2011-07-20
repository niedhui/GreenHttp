package com.greenhttp.methods;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HTTP;

import com.greenhttp.agent.HttpAgent;

public class Post extends HttpMethod{

	public Post(String url,HttpAgent agent) {
		super(url,agent);
	}

	@Override
	protected HttpUriRequest createRequest() {
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(getStringParams(),HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			//Ignore it
		}
		return post;
	}
}
