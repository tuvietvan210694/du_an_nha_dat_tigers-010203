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
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@Column(name = "role_desc", nullable = true)
	private String roleDesc;
	
	//@JsonIgnoreProperties(value = "user")
	@JsonIgnore
	@OneToMany(mappedBy = "role", orphanRemoval = true)
	private List<User> user;
	
	
	//@JsonIgnoreProperties(value = "user")
//		@JsonIgnore
//		@OneToMany(mappedBy = "role", orphanRemoval = true)
//		private List<User2> user2;
	

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Role(Long id, String roleName, String roleDesc, List<User> user) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.user = user;
		//this.user2 = user2;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getRoleDesc() {
		return roleDesc;
	}


	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}


//	public List<User2> getUser2() {
//		return user2;
//	}
//
//
//	public void setUser2(List<User2> user2) {
//		this.user2 = user2;
//	}

}
