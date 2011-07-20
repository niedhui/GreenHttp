package com.greenhttp;

import com.greenhttp.json.SimpleJson;
import com.greenhttp.parser.JsonParser;

public class UserParser extends JsonParser<User> {

	@Override
	protected User fromJson(SimpleJson json) {
		return new User().fromJson(json);
	}

	
}
