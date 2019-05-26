package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_not_authority")
public class CustomerNotAuthority {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_card", unique = true, nullable = false)
    private String idCard;
	
	@Column(name = "name", nullable = true)
    private String nameCustomer;
	
	@Column(name = "phone_number", nullable = true)
    private String phoneNumber;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "day_of_birth", nullable=true)
	private Date dayOfBirth;
	
	@Column(name = "description", nullable = true)
    private String description;
	
	@Column(name = "non_del", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean nonDel = true;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerNotAuthority", orphanRemoval = true)
	private List<Product> listProduct;
	

	public CustomerNotAuthority() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CustomerNotAuthority(Long id, String idCard, String nameCustomer, String phoneNumber, String address,
			Date dayOfBirth, String description, Boolean nonDel, List<Product> listProduct) {
		super();
		this.id = id;
		this.idCard = idCard;
		this.nameCustomer = nameCustomer;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dayOfBirth = dayOfBirth;
		this.description = description;
		this.nonDel = nonDel;
		this.listProduct = listProduct;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
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
