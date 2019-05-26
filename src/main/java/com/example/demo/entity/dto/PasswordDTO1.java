package com.example.demo.entity.dto;

import javax.validation.constraints.NotBlank;

import com.example.demo.validation.Password;

public class PasswordDTO1 {
    
	private String token;
	private String email;
	
//	@NotBlank(message = "passCurrent must not blank")
//	private String passwordCurrent;
	
	@NotBlank(message = "newPassword must not blank")
	@Password(message="Password has at least one number, one special character, one uppecase character and has from 6 to 10 character")
	private String newPassword;	
	
	@NotBlank(message = "newMatchingPassword mut not blank")
	private String newMatchingPassword;

	public PasswordDTO1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordDTO1(String token, String email,
			@NotBlank(message = "newPassword must not blank") String newPassword,
			@NotBlank(message = "newMatchingPassword mut not blank") String newMatchingPassword) {
		super();
		this.token = token;
		this.email = email;
		this.newPassword = newPassword;
		this.newMatchingPassword = newMatchingPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewMatchingPassword() {
		return newMatchingPassword;
	}

	public void setNewMatchingPassword(String newMatchingPassword) {
		this.newMatchingPassword = newMatchingPassword;
	}
		
}