package com.greenhttp.response;

import org.apache.http.HttpEntity;

public interface HttpEntityParser<T> {

	T  parse(HttpEntity httpEntity);
}
