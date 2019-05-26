package com.example.demo.entity.response;

import java.util.Date;

public class UserSearchResponse {
	
	private Long id;
	private String email;
	private String name;
	private String phoneNumber;
	private String password;
	private Boolean Active;
	private Boolean nonDel;
	private Boolean nonLocked;
	private Boolean gender;
	private Date dayOfBirth;
	public UserSearchResponse() {
		super();
	}

	public UserSearchResponse(Long id, String email, String name, String phoneNumber, String password, Boolean active,
			Boolean nonDel, Boolean nonLocked, Boolean gender, Date dayOfBirth) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		Active = active;
		this.nonDel = nonDel;
		this.nonLocked = nonLocked;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

	public Boolean getNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(Boolean nonLocked) {
		this.nonLocked = nonLocked;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}	
}
