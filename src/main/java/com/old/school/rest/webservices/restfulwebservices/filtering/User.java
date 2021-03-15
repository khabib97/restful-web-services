package com.old.school.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//approach 2: static filtering
@JsonIgnoreProperties(value = {"token"})
public class User {
	
	private String username;
	@JsonIgnore // approach 1: static filtering {best one}
	private String password;
	
	private String token;

	public User(String username, String password, String token) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
