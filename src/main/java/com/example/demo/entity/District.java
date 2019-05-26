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
@Table(name = "district")
public class District {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "ma_district", nullable = true)
    private String maDistrict;
	
	@Column(name = "name_district", nullable = true)
    private String nameDistrict;
	
	@Column(name = "type_district", nullable = true)
    private String typeDistrict;
	
	@OneToOne
    @JoinColumn(name = "ma_city", nullable = false)
    private City city;
	
	@JsonIgnore
	@OneToMany(mappedBy = "district", orphanRemoval = true)
	private List<Ward> listWard;

	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	public District(String maDistrict, String nameDistrict, String typeDistrict, City city, List<Ward> listWard) {
		super();
		this.maDistrict = maDistrict;
		this.nameDistrict = nameDistrict;
		this.typeDistrict = typeDistrict;
		this.city = city;
		this.listWard = listWard;
	}

	public String getMaDistrict() {
		return maDistrict;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMaDistrict(String maDistrict) {
		this.maDistrict = maDistrict;
	}

	public String getNameDistrict() {
		return nameDistrict;
	}

	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}

	public String getTypeDistrict() {
		return typeDistrict;
	}

	public void setTypeDistrict(String typeDistrict) {
		this.typeDistrict = typeDistrict;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Ward> getListWard() {
		return listWard;
	}

	public void setListWard(List<Ward> listWard) {
		this.listWard = listWard;
	}
	
}