package com.example.demo.entity.dto;

import javax.validation.constraints.NotBlank;

import com.example.demo.validation.ValidEmail;

public class EmailDTO {

	//@Email
	@NotBlank(message = "You must not be blank")
	@ValidEmail(message="Email sai bieu thuc chinh quy roi ban oi...")
	private String email;

	public EmailDTO() {
		super();
	}

	public EmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
