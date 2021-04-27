package com.example.demo.entity;

import org.springframework.data.annotation.Id;

public class Otp {

	@Id
	private int id;
	private String username;
	private String otp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
