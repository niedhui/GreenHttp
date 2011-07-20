package com.greenhttp;

import static org.junit.Assert.assertNull;

import org.json.JSONException;
import org.junit.Test;

import com.greenhttp.json.SimpleJson;
import com.greenhttp.parser.JsonParser;

public class SimpleJsonTest {
	
	class TestJsonParser extends JsonParser<TestEntity> {
		@Override
		protected TestEntity fromJson(SimpleJson json) {
			return null;
		}
	}

	@Test
	public void jsonNull() throws JSONException{
		TestEntity entity = new TestJsonParser().parse("null");
		assertNull(entity);
	}
}
