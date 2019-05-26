package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "currency")
public class Currency {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "currency_name", nullable = false)
	private String currencyName;
	
	@Column(name = "currency_description", nullable = true)
	private String currencyDescription;
	
	//@JsonIgnoreProperties(value = "currency")
	@JsonIgnore
	@OneToMany(mappedBy = "currency", orphanRemoval = true)
	private List<Product> productList;

	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Currency(Long id, String currencyName, String currencyDescription, List<Product> productList) {
		super();
		this.id = id;
		this.currencyName = currencyName;
		this.currencyDescription = currencyDescription;
		this.productList = productList;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyDescription() {
		return currencyDescription;
	}

	public void setCurrencyDescription(String currencyDescription) {
		this.currencyDescription = currencyDescription;
	}

	

	public List<Product> getProductList() {
		return productList;
	}


	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	@Override
	public String toString() {
		return "Currency [id=" + id + ", currencyName=" + currencyName + ", currencyDescription=" + currencyDescription
				+ ", productList=" + productList + "]";
	}	
	
}
