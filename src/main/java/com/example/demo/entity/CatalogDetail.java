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
@Table(name = "catalog_detail")
public class CatalogDetail {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "catalog_detail_name", nullable = true)
    private String catalogDetailName;
	
	@Column(name = "catalog_detail_des", nullable = true)
    private String catalogDetailDes;
	
	@OneToOne
	@JoinColumn(name = "catalog_id", unique = true, nullable = false)
	private Catalog catalog;
	
	@JsonIgnore
	@OneToMany(mappedBy = "catalogDetail", orphanRemoval = true)
	private List<Product> listProduct;
	

	public CatalogDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CatalogDetail(Long id, String catalogDetailName, String catalogDetailDes, Catalog catalog,
			List<Product> listProduct) {
		super();
		this.id = id;
		this.catalogDetailName = catalogDetailName;
		this.catalogDetailDes = catalogDetailDes;
		this.catalog = catalog;
		this.listProduct = listProduct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatalogDetailName() {
		return catalogDetailName;
	}

	public void setCatalogDetailName(String catalogDetailName) {
		this.catalogDetailName = catalogDetailName;
	}

	public String getCatalogDetailDes() {
		return catalogDetailDes;
	}

	public void setCatalogDetailDes(String catalogDetailDes) {
		this.catalogDetailDes = catalogDetailDes;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
}
