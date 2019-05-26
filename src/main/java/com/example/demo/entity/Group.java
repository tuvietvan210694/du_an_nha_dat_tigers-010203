package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "groups")
public class Group {
	
	//private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name", nullable = true)
    private String nameGroup;
	
	@Column(name = "description", nullable = true)
    private String desGroup;
	
	@JsonIgnore
	@OneToMany(mappedBy = "group", orphanRemoval = true)
	private List<UserGroup> listUserGroup1;
    

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}


}
