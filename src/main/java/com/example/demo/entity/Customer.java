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

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
	
//	@OneToMany(mappedBy = "customer", orphanRemoval = true)
//	private List<DealHistory> dealHistory;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Customer(Long id, User user, List<DealHistory> dealHistory) {
//		super();
//		this.id = id;
//		this.user = user;
//		this.dealHistory = dealHistory;
//	}

	
	public Long getId() {
		return id;
	}

	public Customer(Long id, User user) {
	super();
	this.id = id;
	this.user = user;
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

    	
}
