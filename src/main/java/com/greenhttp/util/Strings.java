package com.greenhttp.util;

public class Strings {

	public static boolean isEmpty(String value) {
		return value == null || "".equals(value);
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
