package com.example.demo.entity.dto;

import com.example.demo.validation.Password;
import com.example.demo.validation.PasswordMatches;
import com.example.demo.validation.Phone;
import com.example.demo.validation.ValidEmail;


@PasswordMatches
public class SignUp {
//	email: string;
//name: string;
//address: string;
//dateOfBirth: string;
//phoneNumber: string;
//gender: boolean;
//password: string;
//passwordConfirm: string;
	//private Long id;
	@ValidEmail(message = "Email must not valid")
	private String email;
	private String name;
	private String address;
	private String dateOfBirth;
	@Phone(message = "Phone must not valid")
	private String phoneNumber;
	private Boolean gender;
	@Password(message = "Password must not be valid, has at least one number, one special character, one uppercase character and has from 6 to 10 character")
	private String password;
	private String passwordConfirm;
	
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}
//	

	public String getEmail() {
		return email;
	}
	public SignUp(String email, String name, String address, String dateOfBirth, String phoneNumber, Boolean gender,
		String password, String passwordConfirm) {
	super();
	this.email = email;
	this.name = name;
	this.address = address;
	this.dateOfBirth = dateOfBirth;
	this.phoneNumber = phoneNumber;
	this.gender = gender;
	this.password = password;
	this.passwordConfirm = passwordConfirm;
}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}
