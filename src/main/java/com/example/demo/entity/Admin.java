package com.example.demo.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "admin")
public class Admin {
	
    
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "admin_name", nullable = false)
//	private String adminName;
//	
//	@Column(name = "admin_description", nullable = true)
//	private String adminDescription;
//	
//	@Column(name = "address", nullable = true)
//	private String address;
//	
//	@Column(name = "phone", nullable = true)
//	private String phone;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin", orphanRemoval = true)
	private List<Owner> listOwner;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin", orphanRemoval = true)
	private List<Product> listProduct;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Admin(Long id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
	

	public Admin(Long id, User user, List<Owner> listOwner) {
		super();
		this.id = id;
		this.user = user;
		this.listOwner = listOwner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public List<Owner> getListOwner() {
		return listOwner;
	}

	public void setListOwner(List<Owner> listOwner) {
		this.listOwner = listOwner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", user=" + user + "]";
	}
    
}
