package com.greenhttp.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleJsonArray {

	private JSONArray json;

	public SimpleJsonArray(JSONArray json) {
		this.json = json;
	}
	
	public SimpleJsonArray(String body){
		try {
			json = new JSONArray(body);
		} catch (JSONException e) {
			throw new com.greenhttp.parser.JsonException();
		}
	}

	public SimpleJson getJSONObject(int index) {
		try {
			return new SimpleJson(json.getJSONObject(index));
		} catch (JSONException e) {
			return new SimpleJson(new JSONObject());
		}
	}

	public int length() {
		return this.json.length();
	}

}
