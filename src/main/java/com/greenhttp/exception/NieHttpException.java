package com.greenhttp.exception;

@SuppressWarnings("serial")
public class NieHttpException extends RuntimeException {

	public NieHttpException() {
		super();
	}

	public NieHttpException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NieHttpException(String arg0) {
		super(arg0);
	}

	public NieHttpException(Throwable arg0) {
		super(arg0);
	}

}
