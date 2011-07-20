package com.greenhttp;

import com.greenhttp.json.SimpleJson;

public class User {
	private String id;
	private String username;
	
	public User(){}
	
	
	public User(String id, String username) {
		this.id = id;
		this.username = username;
	}

	public User fromJson(SimpleJson json) {
		this.id = json.getString("id");
		this.username = json.getString("username");
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
