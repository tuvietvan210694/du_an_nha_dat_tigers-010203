//package com.example.demo.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "product_broker")
//public class ProductBroker {
//
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn(name = "product_id", nullable = false)
//	private Product product;
//	
//	@ManyToOne
//	@JoinColumn(name = "broker_id", nullable = false)
//	private Broker broker;
//
//	public ProductBroker() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public ProductBroker(Long id, Product product, Broker broker) {
//		super();
//		this.id = id;
//		this.product = product;
//		this.broker = broker;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//
//	public Broker getBroker() {
//		return broker;
//	}
//
//	public void setBroker(Broker broker) {
//		this.broker = broker;
//	}
//
//	@Override
//	public String toString() {
//		return "ProductBroker [id=" + id + ", product=" + product + ", broker=" + broker + "]";
//	}
//		
//} 
