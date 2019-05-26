package com.example.demo.entity.response;

public class BrokerResponse {
	private Long id;
	private String email;
	private String nameBroker;
	private Boolean active;
	private Boolean nonDel;
	private Boolean gender;
	private String phoneNumber;
	private String address;
	private String dateOfBirth;
	private String linkImageProfile;
	public BrokerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BrokerResponse(Long id, String email, String nameBroker, Boolean active, Boolean nonDel, Boolean gender,
			String phoneNumber, String address, String dateOfBirth, String linkImageProfile) {
		super();
		this.id = id;
		this.email = email;
		this.nameBroker = nameBroker;
		this.active = active;
		this.nonDel = nonDel;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.linkImageProfile = linkImageProfile;
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
	public String getNameBroker() {
		return nameBroker;
	}
	public void setNameBroker(String nameBroker) {
		this.nameBroker = nameBroker;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getLinkImageProfile() {
		return linkImageProfile;
	}

	public void setLinkImageProfile(String linkImageProfile) {
		this.linkImageProfile = linkImageProfile;
	}

	@Override
	public String toString() {
		return "BrokerResponse [id=" + id + ", email=" + email + ", nameBroker=" + nameBroker + ", active=" + active
				+ ", nonDel=" + nonDel + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
		
}
