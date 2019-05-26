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
@Table(name = "catalog")
public class Catalog {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "catalog_name", nullable = true)
    private String catalogName;
	
	@Column(name = "catalog_des", nullable = true)
    private String catalogDes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "catalog", orphanRemoval = true)
	private List<CatalogDetail> catalogDetail;

	public Catalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalog(Long id, String catalogName, String catalogDes, List<CatalogDetail> catalogDetail) {
		super();
		this.id = id;
		this.catalogName = catalogName;
		this.catalogDes = catalogDes;
		this.catalogDetail = catalogDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogDes() {
		return catalogDes;
	}

	public void setCatalogDes(String catalogDes) {
		this.catalogDes = catalogDes;
	}

	public List<CatalogDetail> getCatalogDetail() {
		return catalogDetail;
	}

	public void setCatalogDetail(List<CatalogDetail> catalogDetail) {
		this.catalogDetail = catalogDetail;
	}
	
}
