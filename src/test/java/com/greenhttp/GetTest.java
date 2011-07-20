package com.greenhttp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.greenhttp.exception.CredentialsException;
import com.greenhttp.exception.ServerDownException;
import com.greenhttp.exception.UncheckedIOException;
import com.greenhttp.json.SimpleJson;
import com.greenhttp.response.Response;


public class GetTest {
	
	@BeforeClass
	public static void init(){
		Http.getHttpAgent().useBasicAuth().setBaseUri("localhost:4999");
	}
	
	@After
	public void clear() {
		Http.clearCredentials();
	}

	@Test
	public void simpleGet(){
		Response response = Http.get("/hello").go();
		assertTrue(response.isSuccess());
		assertThat(response.stringBody(), is("world"));
	}
	
	@Test 
	public void getWithParam(){
		Response response = Http.get("/username").with("id", "1").go();
		assertThat(response.stringBody(), is("Jack"));
	}
	
	@Test
	public void getAndParseJson(){
		SimpleJson json = Http.get("/users.json").with("id", "1").go().toJson();
		assertThat(json.getInt("id"), is(1));
		assertThat(json.getString("username"),is("jack"));
	}
	
	public void getAndParseJsonThenError(){
		assertNull(Http.get("/users.json").with("id", "2").go().toJson());
	}
	
	@Test(expected=UncheckedIOException.class)
	public void getFromAnUnreachedUrl(){
		Http.get("http://localhost:4568/users.json").go().toJson();
	}
	
	@Test
	public void getAndParseToUser(){
		User expectedUser = new User("1","jack");
		User user = Http.get("/users.json").with("id", "1").go().toEntity(new UserParser());
		assertThat(user, is(expectedUser));
	}
	
	@Test(expected=ServerDownException.class)
	@Ignore
	public void getAndServerDown(){
		Http.get("http://down.ssssserver.com").go().stringBody();
	}
	
	@Test
	public void getWithBasciAuth(){
		Http.setCredentials("manager", "secret");
		Response response = Http.get("/signin.json").go();
		assertTrue(response.isSuccess());
	}
	
	@Test(expected=CredentialsException.class)
	public void getWithoutCredential(){
		Http.get("/signin.json").go().stringBody();
	}
	
}
