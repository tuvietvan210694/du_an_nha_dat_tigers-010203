package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.PrimaryKeyJoinColumn;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean active = false;
    
    @Column(name = "non_del", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean nonDel = true;
    
    @Column(name = "non_locked", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean nonLocked = true;
    
    @Column(name = "name", nullable = true)
    private String nameUser;
    
    @Column(name = "gender", nullable = false, columnDefinition = "BOOLEAN default true")
    private Boolean gender = true;
    
    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;
    
    @Column(name = "address", nullable = true)
    private String address;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "day_of_birth", nullable=true)
	private Date dayOfBirth;
    
    
    @Column(name = "description", nullable = true)
    private String description;
    
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    // @JsonIgnore
    //sua tai day
    @JsonIgnore
    @JsonIgnoreProperties(value= "user")
	@OneToOne(mappedBy = "user",orphanRemoval=true)
	private TokenVerifition tokenVerifition;
    
    
//    @JsonIgnoreProperties(value= "user")
//    @OneToOne(mappedBy = "user", orphanRemoval=true)
//    private Role role;
    
    @JsonIgnore
    @JsonIgnoreProperties(value = "user")
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Admin admin;
    
    @JsonIgnore
    @JsonIgnoreProperties(value = "user")
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Request request;
    
    @JsonIgnore
    @JsonIgnoreProperties(value = "user")
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Customer customer;
    
    @JsonIgnore
    @JsonIgnoreProperties(value = "user")
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Broker broker;
    
    @JsonIgnore
    @JsonIgnoreProperties(value= "user")
	@OneToOne(mappedBy = "user", orphanRemoval=true)
    //@OneToOne(mappedBy = "user")
	private BlockUser blockUser;
    
    @OneToOne
	@JoinColumn(name = "fileProfile_id", nullable = true)
	private DBFileProfile dBFileProfile;
    
    @JsonIgnore
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<UserGroup> listUserGroup;
    
    
    
    
    //@JsonIgnore
//  @JsonIgnoreProperties(value = "user")
//	//@OneToOne(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)
//  @OneToOne(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)
//
////	private Employee employee;
//  
////  @OneToOne(cascade = {CascadeType.ALL})
//// // @PrimaryKeyJoinColumn
//  private Employee employee;

//  @JsonIgnore
//  //@JsonIgnoreProperties(value = "user")
//  //@OneToMany(mappedBy = "user",orphanRemoval=true)
//	@OneToMany(mappedBy = "user")
//	private List<UserRole> userRoles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public User(Long id, String email, String password, Boolean active, Boolean nonDel, Boolean nonLocked,
			String nameUser, Boolean gender, String phoneNumber, String address, Date dayOfBirth, String description,
			Role role, TokenVerifition tokenVerifition, Admin admin, Request request, Customer customer, Broker broker,
			BlockUser blockUser, DBFileProfile dBFileProfile, List<UserGroup> listUserGroup) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.active = active;
		this.nonDel = nonDel;
		this.nonLocked = nonLocked;
		this.nameUser = nameUser;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dayOfBirth = dayOfBirth;
		this.description = description;
		this.role = role;
		this.tokenVerifition = tokenVerifition;
		this.admin = admin;
		this.request = request;
		this.customer = customer;
		this.broker = broker;
		this.blockUser = blockUser;
		this.dBFileProfile = dBFileProfile;
		this.listUserGroup = listUserGroup;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Boolean getNonDel() {
		return nonDel;
	}


	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}


	public Boolean getNonLocked() {
		return nonLocked;
	}


	public void setNonLocked(Boolean nonLocked) {
		this.nonLocked = nonLocked;
	}


	public String getNameUser() {
		return nameUser;
	}


	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}


	public Boolean getGender() {
		return gender;
	}


	public void setGender(Boolean gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDayOfBirth() {
		return dayOfBirth;
	}


	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


//	public Role getRole() {
//		return role;
//	}
//
//
//	public void setRole(Role role) {
//		this.role = role;
//	}


	public TokenVerifition getTokenVerifition() {
		return tokenVerifition;
	}


	public void setTokenVerifition(TokenVerifition tokenVerifition) {
		this.tokenVerifition = tokenVerifition;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Broker getBroker() {
		return broker;
	}


	public void setBroker(Broker broker) {
		this.broker = broker;
	}


	public BlockUser getBlockUser() {
		return blockUser;
	}
	

	public DBFileProfile getdBFileProfile() {
		return dBFileProfile;
	}

	public void setdBFileProfile(DBFileProfile dBFileProfile) {
		this.dBFileProfile = dBFileProfile;
	}

	public List<UserGroup> getListUserGroup() {
		return listUserGroup;
	}

	public void setListUserGroup(List<UserGroup> listUserGroup) {
		this.listUserGroup = listUserGroup;
	}

	public void setBlockUser(BlockUser blockUser) {
		this.blockUser = blockUser;
	}
	public List<GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    //for (String role : roles) {
	      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	    //}
	    return authorities;
	  }

//	public List<UserGroup> getListUserGroup() {
//		return listUserGroup;
//	}
//
//
//	public void setListUserGroup(List<UserGroup> listUserGroup) {
//		this.listUserGroup = listUserGroup;
//	}


//	@Override
//	public String toString() {
//		return "User [id=" + id + ", email=" + email + ", password=" + password + ", active=" + active + ", nonDel="
//				+ nonDel + ", nonLocked=" + nonLocked + ", nameUser=" + nameUser + ", gender=" + gender
//				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", dayOfBirth=" + dayOfBirth
//				+ ", description=" + description + ", role=" + role + ", tokenVerifition=" + tokenVerifition
//				+ ", admin=" + admin + ", request=" + request + ", customer=" + customer + ", broker=" + broker
//				+ ", blockUser=" + blockUser + ", listUserGroup=" + listUserGroup + "]";
//	}

	
	
	}
