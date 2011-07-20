package com.greenhttp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenhttp.response.Response;

public class CredentialsTest {
	
	@BeforeClass
	public static void init(){
		Http.getHttpAgent().useBasicAuth().setBaseUri("localhost:4999");
	}
	
	@Test 
	public void getWithParam(){
		Http.setCredentials("manager", "secret");
		Response response = Http.get("/username").with("id", "1").go();
		assertThat(response.stringBody(), is("Jack"));
	}
}
