package com.example.demo.entity.dto;

import javax.validation.constraints.NotBlank;

//import com.example.demo.validation.IdCard;
import com.example.demo.validation.Phone;
public class AssignBrokerWithCustomerDTO {
	
	private long id;
	@NotBlank
	private String ownerName;
	@Phone(message = "Id card must not valid")
	private String idCard;
	//@Phone(message = "Phone Number must not valid")
	private String phoneNumber;
	private String address;
	private long brokerId;
	
	public AssignBrokerWithCustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public AssignBrokerWithCustomerDTO(long id, String ownerName, String idCard, long brokerId) {
//		super();
//		this.id = id;
//		this.ownerName = ownerName;
//		this.idCard = idCard;
//		this.brokerId = brokerId;
//	}
    
	
	public long getId() {
		return id;
	}
	public AssignBrokerWithCustomerDTO(long id, @NotBlank String ownerName, String idCard, String phoneNumber,
		String address, long brokerId) {
	super();
	this.id = id;
	this.ownerName = ownerName;
	this.idCard = idCard;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.brokerId = brokerId;
}

	public void setId(long id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	
    
}
