package com.example.demo.entity.dto;

import javax.validation.constraints.NotBlank;

import com.example.demo.validation.Password;

public class PasswordDTOProfile {
	
	@NotBlank(message = "passCurrent must not blank")
	private String passwordCurrent;
	
	@NotBlank(message = "newPassword must not blank")
	@Password(message="newPassword has at least one number, one special character, one uppecase character and has from 6 to 10 character")
	private String newPassword;	
	
	@NotBlank(message = "newMatchingPassword mut not blank")
	private String newMatchingPassword;

	public String getPasswordCurrent() {
		return passwordCurrent;
	}

	public void setPasswordCurrent(String passwordCurrent) {
		this.passwordCurrent = passwordCurrent;
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

	public PasswordDTOProfile(@NotBlank(message = "passCurrent must not blank") String passwordCurrent,
			@NotBlank(message = "newPassword must not blank") String newPassword,
			@NotBlank(message = "newMatchingPassword mut not blank") String newMatchingPassword) {
		super();
		this.passwordCurrent = passwordCurrent;
		this.newPassword = newPassword;
		this.newMatchingPassword = newMatchingPassword;
	}

}
