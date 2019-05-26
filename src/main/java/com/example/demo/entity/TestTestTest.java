package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testtesttest")
public class TestTestTest implements Serializable {
    
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
    private String email;
	
	@Column(name = "active", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean active = false;
	
	public TestTestTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestTestTest(Long id, String email, Boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}	
}
