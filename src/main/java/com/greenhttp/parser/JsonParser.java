package com.greenhttp.parser;

import com.greenhttp.json.SimpleJson;

public abstract class JsonParser<T> implements Parser<T> {

	public static SimpleJson parseJson(String body) {
		return new SimpleJson(body);
	}

	@Override
	public T parse(String body) {
		SimpleJson simpleJson = parseJson(body);
		if(simpleJson.isNull()){
			return null;
		}
		return fromJson(simpleJson);
	}

	protected abstract T fromJson(SimpleJson json);

}
