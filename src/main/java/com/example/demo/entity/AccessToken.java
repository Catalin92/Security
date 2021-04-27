package com.example.demo.entity;

public class AccessToken {

	public AccessToken(String username, String token) {
		this.username = username;
		this.token = token;
	}

	private String id;
	private String username;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
