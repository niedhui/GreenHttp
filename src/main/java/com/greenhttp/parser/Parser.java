package com.greenhttp.parser;

public interface Parser<T> {
	T parse(String body);
}
