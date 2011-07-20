package com.greenhttp.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenhttp.util.Strings;

public class SimpleJson {

	private JSONObject json;

	private static final String DateFormat = "yyyy-MM-dd HH:mm:ss Z";
	private static final SimpleDateFormat format = new SimpleDateFormat(DateFormat);

	public SimpleJson(String body) {
		try {
			if (Strings.isEmpty(body) || "null".equalsIgnoreCase(body)) {
				this.json = null;
			} else {
				this.json = new JSONObject(body);
			}
		} catch (JSONException e) {
			throw new com.greenhttp.parser.JsonException();
		}
	}

	public SimpleJson(JSONObject json) {
		this.json = json;
	}

	public String getString(String key) {
		try {
			return json.getString(key);
		} catch (JSONException e) {
			return "";
		}
	}

	public Long getLong(String key) {
		try {
			return json.getLong(key);
		} catch (JSONException e) {
			return 0l;
		}
	}

	public Integer getInt(String key) {
		try {
			return json.getInt(key);
		} catch (JSONException e) {
			return 0;
		}
	}

	public boolean getBoolean(String key) {
		try {
			return json.getBoolean(key);
		} catch (JSONException e) {
			return false;
		}
	}

	public Float getFloat(String key) {
		try {
			return (float) json.getDouble(key);
		} catch (JSONException e) {
			return 0.0f;
		}
	}

	public SimpleJson getJsonObject(String key) {
		try {
			return new SimpleJson(json.getJSONObject(key));
		} catch (JSONException e) {
			return null;
		}
	}

	public SimpleJsonArray getJsonArray(String key) {
		try {
			return new SimpleJsonArray(json.getJSONArray(key));
		} catch (JSONException e) {
			return new SimpleJsonArray(new JSONArray());
		}
	}

	public Date getDate(String key) {
		try {
			String dateStr = json.getString(key);
			if (Strings.isNotEmpty(dateStr)) {
				return format.parse(dateStr);
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String toString() {
		return this.json.toString();
	}
	
	public boolean isNull(){
		return this.json == null;
	}

}
