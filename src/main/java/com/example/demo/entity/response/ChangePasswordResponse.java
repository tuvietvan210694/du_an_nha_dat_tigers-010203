package com.example.demo.entity.response;

public class ChangePasswordResponse {
	
	private String email;
	private String token;
	
	
	public ChangePasswordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ChangePasswordResponse(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
}
