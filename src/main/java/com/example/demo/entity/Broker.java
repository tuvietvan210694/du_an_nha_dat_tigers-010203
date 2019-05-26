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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "broker")
public class Broker {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnoreProperties(value = "user")
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "broker", orphanRemoval = true)
	private List<Owner> listOwner;
	
	@JsonIgnore
	@OneToMany(mappedBy = "broker", orphanRemoval = true)
	private List<Product> listProduct;

	public Broker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Broker(Long id, User user, List<Owner> listOwner) {
		super();
		this.id = id;
		this.user = user;
		this.listOwner = listOwner;
	}
	

	public Broker(Long id, User user, List<Owner> listOwner, List<Product> listProduct) {
		super();
		this.id = id;
		this.user = user;
		this.listOwner = listOwner;
		this.listProduct = listProduct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Owner> getListOwner() {
		return listOwner;
	}

	public void setListOwner(List<Owner> listOwner) {
		this.listOwner = listOwner;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
}
