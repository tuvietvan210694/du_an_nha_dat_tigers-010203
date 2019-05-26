package com.example.demo.entity.response;

public class TokenResponse {
	private String accessToken;
	private String type;
	private String username;
	private String[] authorities;
	public TokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TokenResponse(String accessToken, String type, String username, String[] authorities) {
		super();
		this.accessToken = accessToken;
		this.type = type;
		this.username = username;
		this.authorities = authorities;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

}
