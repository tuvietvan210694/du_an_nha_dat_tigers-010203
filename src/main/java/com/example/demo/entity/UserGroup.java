package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey=@ForeignKey(name="ref_user1"))
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false, foreignKey=@ForeignKey(name="ref_group1"))
	private Group group;


	public UserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

}
