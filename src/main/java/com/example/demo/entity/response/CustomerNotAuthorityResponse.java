package com.example.demo.entity.response;

public class CustomerNotAuthorityResponse {
	
	private long id;
	private String idCard;
	private String nameCustomer;
	private String phoneNumber;
	private String dayOfBirth;
	private String description;
	private Boolean nonDel;
	public CustomerNotAuthorityResponse() {
		super();
	}
	
	public CustomerNotAuthorityResponse(long id, String idCard, String nameCustomer, String phoneNumber,
			String dayOfBirth, String description, Boolean nonDel) {
		super();
		this.id = id;
		this.idCard = idCard;
		this.nameCustomer = nameCustomer;
		this.phoneNumber = phoneNumber;
		this.dayOfBirth = dayOfBirth;
		this.description = description;
		this.nonDel = nonDel;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

}
