package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "owner")
public class Owner {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "owner_name", nullable = false)
	private String ownerName;
	
	@Column(name = "id_card", nullable = false)
	private String idCard;
	
	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "owner_birthday", nullable = true)
	private Date ownerBirthday;
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "broker_id", nullable = true)
	private Broker broker;
	
	@Column(name = "trang_thai", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean trangThai = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Product> productList;
	
	@JsonIgnore
    @OneToOne
    @JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;

	public Owner() {
		super();
	}
	
	public Owner(Long id, String ownerName, Date ownerBirthday, Broker broker, Boolean trangThai,
			List<Product> productList, Admin admin) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.ownerBirthday = ownerBirthday;
		this.broker = broker;
		this.trangThai = trangThai;
		this.productList = productList;
		this.admin = admin;
	}

	public Owner(Long id, String ownerName, Date ownerBirthday, Broker broker, List<Product> productList, Admin admin) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.ownerBirthday = ownerBirthday;
		this.broker = broker;
		this.productList = productList;
		this.admin = admin;
	}

	public Owner(Long id, String ownerName, String idCard, Date ownerBirthday, Broker broker, Boolean trangThai,
			List<Product> productList, Admin admin) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.idCard = idCard;
		this.ownerBirthday = ownerBirthday;
		this.broker = broker;
		this.trangThai = trangThai;
		this.productList = productList;
		this.admin = admin;
	}
	

	public Owner(Long id, String ownerName, String idCard, String phoneNumber, String address, Date ownerBirthday,
			Broker broker, Boolean trangThai, List<Product> productList, Admin admin) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.idCard = idCard;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.ownerBirthday = ownerBirthday;
		this.broker = broker;
		this.trangThai = trangThai;
		this.productList = productList;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Date getOwnerBirthday() {
		return ownerBirthday;
	}

	public void setOwnerBirthday(Date ownerBirthday) {
		this.ownerBirthday = ownerBirthday;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
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
