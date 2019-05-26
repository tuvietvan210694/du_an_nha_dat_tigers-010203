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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ward")
public class Ward {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "ma_ward", nullable = true)
    private String maWard;
	
	@Column(name = "name_ward", nullable = true)
    private String nameWard;
	
	@Column(name = "type_ward", nullable = true)
    private String typeWard;
	
	@OneToOne
    @JoinColumn(name = "ma_district", nullable = false)
    private District district;

	public Ward() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ward(String maWard, String nameWard, String typeWard, District district) {
		super();
		this.maWard = maWard;
		this.nameWard = nameWard;
		this.typeWard = typeWard;
		this.district = district;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaWard() {
		return maWard;
	}

	public void setMaWard(String maWard) {
		this.maWard = maWard;
	}

	public String getNameWard() {
		return nameWard;
	}

	public void setNameWard(String nameWard) {
		this.nameWard = nameWard;
	}

	public String getTypeWard() {
		return typeWard;
	}

	public void setTypeWard(String typeWard) {
		this.typeWard = typeWard;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
}