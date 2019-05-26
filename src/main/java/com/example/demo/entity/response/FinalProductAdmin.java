package com.example.demo.entity.response;

public class FinalProductAdmin {
	private long idProduct;
	private long customerId;
	private String idCard;
	private String nameCustomer;
	private String phoneNumber;
	private String dayOfBirth;
	private String description;
	private long brokerId;
	
	public FinalProductAdmin() {
		super();
	}

	public FinalProductAdmin(long idProduct, long customerId, String idCard, String nameCustomer, String phoneNumber,
			String dayOfBirth, String description, long brokerId) {
		super();
		this.idProduct = idProduct;
		this.customerId = customerId;
		this.idCard = idCard;
		this.nameCustomer = nameCustomer;
		this.phoneNumber = phoneNumber;
		this.dayOfBirth = dayOfBirth;
		this.description = description;
		this.brokerId = brokerId;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
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
	public long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
