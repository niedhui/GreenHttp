package com.greenhttp.response;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.greenhttp.exception.UncheckedIOException;

public class HttpEntityBytesParser implements HttpEntityParser<byte[]>{

	@Override
	public byte[] parse(HttpEntity httpEntity) {
		try {
			return EntityUtils.toByteArray(httpEntity);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
