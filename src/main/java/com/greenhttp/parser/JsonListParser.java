package com.greenhttp.parser;

import java.util.ArrayList;
import java.util.List;

import com.greenhttp.json.SimpleJson;
import com.greenhttp.json.SimpleJsonArray;

public abstract class JsonListParser<T> implements Parser<List<T>> {

	public List<T> parse(String body) {
		List<T> result = new ArrayList<T>();
		SimpleJsonArray array = new SimpleJsonArray(body);
		for (int i = 0; i < array.length(); i++) {
			result.add(fromJson(array.getJSONObject(i)));
		}
		return result;
	}
	
	public abstract T fromJson(SimpleJson json);
}
