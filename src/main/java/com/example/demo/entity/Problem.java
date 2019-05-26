package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "problem111")
public class Problem implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) default 0")
//    private Boolean active = false;
//    
//    @Column(name = "non_del", nullable = false, columnDefinition = "SMALLINT(1) default 1")
//    private Boolean nonDel = true;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Boolean getActive() {
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}

//	public Boolean getNonDel() {
//		return nonDel;
//	}
//
//	public void setNonDel(Boolean nonDel) {
//		this.nonDel = nonDel;
//	}
//    
} 