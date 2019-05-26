package com.example.demo.entity;

import java.util.Date;

//import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_customer")
public class ProductCustomer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deal_history_date", nullable = false)
	private Date dealHistoryDate;
	
	@Column(name = "deal_history_desc", nullable = true)
	private String DealHistoryDesc;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, foreignKey=@ForeignKey(name="ref_product111"))
	private Product product;
	
//	//sua tai day
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "broker_id" , nullable = false, foreignKey=@ForeignKey(name="ref_broker"))
//	private Broker broker;
	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "customer_id", nullable = false, foreignKey=@ForeignKey(name="ref_customer111"))
//	private Customer customer;

	public ProductCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCustomer(Long id, Date dealHistoryDate, String dealHistoryDesc, Product product, Customer customer) {
		super();
		this.id = id;
		this.dealHistoryDate = dealHistoryDate;
		DealHistoryDesc = dealHistoryDesc;
		this.product = product;
		//this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDealHistoryDate() {
		return dealHistoryDate;
	}

	public void setDealHistoryDate(Date dealHistoryDate) {
		this.dealHistoryDate = dealHistoryDate;
	}

	public String getDealHistoryDesc() {
		return DealHistoryDesc;
	}

	public void setDealHistoryDesc(String dealHistoryDesc) {
		DealHistoryDesc = dealHistoryDesc;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

//	@Override
//	public String toString() {
//		return "DealHistory [id=" + id + ", dealHistoryDate=" + dealHistoryDate + ", DealHistoryDesc=" + DealHistoryDesc
//				+ ", product=" + product + ", customer=" + customer + "]";
//	}
//	
}
