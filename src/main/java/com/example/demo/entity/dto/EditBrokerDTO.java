package com.example.demo.entity.dto;

public class EditBrokerDTO {
//	id:number;
////name:string;
// email:string;  
// nameBroker:string;   
// active:boolean;
// nonDel:boolean;
// gender:boolean;
// phoneNumber:string;
// address:string;
// dateOfBirth:string;
// password:string;
// nonBlock:boolean;
// linkImageProfile: string
	private long id;
	private String email;
	private String nameBroker;
	private Boolean active;
	private Boolean nonDel;
	private Boolean gender;
	private String phoneNumber;
	private String address;
	private String dateOfBirth;
	private String password;
	private Boolean nonBlock;
	private String linkImageProfile;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getNonBlock() {
		return nonBlock;
	}
	public void setNonBlock(Boolean nonBlock) {
		this.nonBlock = nonBlock;
	}
	public String getLinkImageProfile() {
		return linkImageProfile;
	}
	public void setLinkImageProfile(String linkImageProfile) {
		this.linkImageProfile = linkImageProfile;
	}

}
