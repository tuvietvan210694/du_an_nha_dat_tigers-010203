package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(value = { "roles", "authorities" })
@Entity
@Table(name = "user11")
public class User1 {
	
	 @Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
	 @Column(name = "username", nullable = true)
  private String username;
	 
	 @Column(name = "password", nullable = true)
  private String password;
	 
	 @Column(name = "role", nullable = true)
  private String roles;
  public User1() {
  }
  public User1(long id, String username, String password, String roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
  }
  public List<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    //for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(roles));
    //}
    return authorities;
  }
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRoles() {
	return roles;
}
public void setRoles(String roles) {
	this.roles = roles;
}

//public String[] getRoles() {
//	return roles;
//}
//public void setRoles(String[] roles) {
//	this.roles = roles;
//}  
}
