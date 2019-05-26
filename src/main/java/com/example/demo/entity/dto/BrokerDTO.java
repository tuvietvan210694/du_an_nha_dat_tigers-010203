package com.example.demo.entity.dto;

public class BrokerDTO {
    
	//private static final long serialVersionUID = 1L;
	
	private String email;
	private String nameBroker;
	private Boolean gender;
	private String phoneNumber;
	private String address;
	private Boolean active;
	private Boolean nonDel;
	private Boolean nonBlock;
	private String dateOfBirth;
	private String password;
	private String role;
	private String description;
	
	public BrokerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
	
	public BrokerDTO(String email, String nameBroker, Boolean gender, String phoneNumber, String address,
			Boolean active, Boolean nonDel, Boolean nonBlock, String dateOfBirth, String password, String role,
			String description) {
		super();
		this.email = email;
		this.nameBroker = nameBroker;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.active = active;
		this.nonDel = nonDel;
		this.nonBlock = nonBlock;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.role = role;
		this.description = description;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

	public Boolean getNonBlock() {
		return nonBlock;
	}

	public void setNonBlock(Boolean nonBlock) {
		this.nonBlock = nonBlock;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "BrokerDTO [email=" + email + ", nameBroker=" + nameBroker + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", active=" + active + ", nonDel=" + nonDel + ", nonBlock="
				+ nonBlock + ", dateOfBirth=" + dateOfBirth + ", password=" + password + ", role=" + role
				+ ", description=" + description + "]";
	}
		
}
