package com.greenhttp.response;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.greenhttp.exception.CredentialsException;
import com.greenhttp.exception.GreenHttpException;
import com.greenhttp.exception.ServerDownException;
import com.greenhttp.json.SimpleJson;
import com.greenhttp.parser.JsonListParser;
import com.greenhttp.parser.JsonParser;
import com.greenhttp.parser.Parser;
import com.greenhttp.util.EntityUtils;

public class Response {

	private int statusCode;

	private HttpResponse realResponse;

	public Response(HttpResponse httpResponse) {
		this.realResponse = httpResponse;
		this.statusCode = this.realResponse.getStatusLine().getStatusCode();
	}

	public boolean isSuccess() {
		return statusCode == 200;
	}
	
	private <T> T resposeWith(HttpEntityParser<T> parser){
		HttpEntity responseEntity = this.realResponse.getEntity();
		switch (statusCode) {
		case 200:
			return parser.parse(responseEntity);
		case 401:
			releaseEntity(responseEntity);
			throw new CredentialsException();
		case 502:
			releaseEntity(responseEntity);
			throw new ServerDownException();
		default:
			throw new GreenHttpException(statusCode, new HttpEntityStringParser().parse(responseEntity));
		}

	}

	public String stringBody() {
		return resposeWith(new HttpEntityStringParser());
	}
	
	public byte[] bytesBody(){
		return resposeWith(new HttpEntityBytesParser());
	}
	

	private void releaseEntity(HttpEntity responseEntity) {
		try {
			EntityUtils.consume(responseEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SimpleJson toJson() {
		return JsonParser.parseJson(stringBody());
	}

	public <T> T toEntity(Parser<T> parser) {
		return parser.parse(stringBody());
	}
	
	public <T> List<T> toEntities(JsonListParser<T> parser){
		return parser.parse(stringBody());
	}
	
	public int getStatusCode(){
		return this.statusCode;
	}
	
	public HttpResponse getRealResonse() {
		return this.realResponse;
	}

}
