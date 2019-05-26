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
@Table(name = "city")
public class City {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "ma_city", nullable = true)
    private String maCity;
	
	@Column(name = "name_city", nullable = true)
    private String nameCity;
	
	@Column(name = "type_city", nullable = true)
    private String typeCity;

	@JsonIgnore
	@OneToMany(mappedBy = "city", orphanRemoval = true)
	private List<District> listDistrict;

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(Long id, String maCity, String nameCity, String typeCity, List<District> listDistrict) {
		super();
		this.id = id;
		this.maCity = maCity;
		this.nameCity = nameCity;
		this.typeCity = typeCity;
		this.listDistrict = listDistrict;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaCity() {
		return maCity;
	}

	public void setMaCity(String maCity) {
		this.maCity = maCity;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getTypeCity() {
		return typeCity;
	}

	public void setTypeCity(String typeCity) {
		this.typeCity = typeCity;
	}

	public List<District> getListDistrict() {
		return listDistrict;
	}

	public void setListDistrict(List<District> listDistrict) {
		this.listDistrict = listDistrict;
	}
	
	
}
