package com.greenhttp.response;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.greenhttp.exception.UncheckedIOException;


public class HttpEntityStringParser implements HttpEntityParser<String>{

	@Override
	public String parse(HttpEntity httpEntity) {
		try {
			return EntityUtils.toString(httpEntity, HTTP.UTF_8);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return null;
	}

}
