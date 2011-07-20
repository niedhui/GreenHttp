package com.greenhttp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenhttp.Http;
import com.greenhttp.json.SimpleJson;
import com.greenhttp.response.Response;

public class PostTest {

	@BeforeClass
	public static void init(){
		Http.getHttpAgent().useBasicAuth().setBaseUri("localhost:4999");
	}
	
	@Test
	public void simplePost(){
		Response response = Http.post("/hello").go();
		assertTrue(response.isSuccess());
		assertThat(response.stringBody(), is("bye"));
	}
	
	@Test
	public void postWithParams(){
		SimpleJson json = Http.post("/signup.json").with("username", "lily").go().toJson();
		assertThat(json.getString("username"), is("lily"));
	}
	
}
