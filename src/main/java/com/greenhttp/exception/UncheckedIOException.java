package com.greenhttp.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class UncheckedIOException extends NieHttpException {

	public UncheckedIOException() {

	}

	public UncheckedIOException(IOException exception) {
		super(exception);
	}
}
