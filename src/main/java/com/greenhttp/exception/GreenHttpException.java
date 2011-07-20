package com.greenhttp.exception;

@SuppressWarnings("serial")
public class GreenHttpException extends RuntimeException {
	private int statusCode;
	private String body;

	public GreenHttpException() {
		super();
	}

	public GreenHttpException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GreenHttpException(String arg0) {
		super(arg0);
	}

	public GreenHttpException(Throwable arg0) {
		super(arg0);
	}
	
	public GreenHttpException(int statusCode,String body) {
		this.statusCode = statusCode;
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}
	
	

}
