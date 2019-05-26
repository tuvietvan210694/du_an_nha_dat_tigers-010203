package com.example.demo.entity.dto;

public class BrokerDTOEdit {
	
	private Long id;
	private String email;
	private String nameBroker;
	private Boolean gender;
	private String phoneNumber;
	private String address;
	private Boolean active;
	private Boolean nonDel;
	private String dateOfBirth;
	private String password;
	private String role;
	
	public BrokerDTOEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public BrokerDTOEdit(Long id, String email, String nameBroker, Boolean gender, String phoneNumber, String address,
			Boolean active, Boolean nonDel, String dateOfBirth, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.nameBroker = nameBroker;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.active = active;
		this.nonDel = nonDel;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.role = role;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameBroker() {
		return nameBroker;
	}

	public void setNameBroker(String nameBroker) {
		this.nameBroker = nameBroker;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BrokerDTOEdit [email=" + email + ", nameBroker=" + nameBroker + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", active=" + active + ", nonDel=" + nonDel + ", dateOfBirth="
				+ dateOfBirth + ", password=" + password + ", role=" + role + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	
	
	
}
