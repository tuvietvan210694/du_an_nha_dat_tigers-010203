package com.example.demo.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_status", nullable = false, columnDefinition="BOOLEAN default false")
	private Boolean productStatus = false;
	
	@Column(name = "non_del", nullable = false, columnDefinition="BOOLEAN default true")
	private Boolean nonDel = true;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "product_created_date", nullable = false)
//	private Date productCreatedDate;
	
	@Column(name = "product_location", nullable = false)
	private String productLocation;
	
	@Column(name = "product_cost", nullable = false)
	private Long productCost;
	
	@Column(name = "product_image", nullable = true)
	private String productImage;
	
	
	@Column(name = "request_status", nullable = true)
	private String requestStatus;
	
	@Column(name = "zipcode", nullable = true)
	private String zipCode;
	
	@OneToOne
	@JoinColumn(name = "customerNotAuthority_id", nullable = true)
	private CustomerNotAuthority customerNotAuthority;
	
//	@Column(name = "thu_string", nullable = false)
//	private String thuString;
	
	
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "currency_id", nullable = false)
	private Currency currency;
	
	
	@OneToOne
	@JoinColumn(name = "file_id", unique = true, nullable = false)
    private DBFile dBFile;
	
	
	
//	//@JsonIgnore
//	@OneToMany(mappedBy = "product", orphanRemoval = true)
//	private List<Currency> listCurrency;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", orphanRemoval = true)
//	private List<ProductBroker> listProductBroker;
	
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false, foreignKey=@ForeignKey(name="ref_owner"))
	private Owner owner;
	
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "broker_id",  nullable = true)
	private Broker broker;
	
	@OneToOne
	@JoinColumn(name = "admin_id",  nullable = true)
	private Admin admin;
	
//	@OneToMany(mappedBy = "product", orphanRemoval = true)
//	private List<DealHistory> dealHistory;
	
	
	
//	@OneToMany(mappedBy = "product", orphanRemoval = true)
//	private List<DealHistory> dealHistory;
	
	@Column(name = "product_acreage", nullable = false)
	private Long productAcreage;
	
	@Column(name = "product_description", nullable = true)
	private String productDescription;
	
	@OneToOne
	@JoinColumn(name = "catalog_detail_id", nullable = false)
	private CatalogDetail catalogDetail;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, Boolean productStatus, Boolean nonDel, String productName, String productLocation,
			Long productCost, String productImage, String requestStatus, String zipCode,
			CustomerNotAuthority customerNotAuthority, Currency currency, DBFile dBFile, Owner owner, Broker broker,
			Long productAcreage, String productDescription, CatalogDetail catalogDetail) {
		super();
		this.id = id;
		this.productStatus = productStatus;
		this.nonDel = nonDel;
		this.productName = productName;
		this.productLocation = productLocation;
		this.productCost = productCost;
		this.productImage = productImage;
		this.requestStatus = requestStatus;
		this.zipCode = zipCode;
		this.customerNotAuthority = customerNotAuthority;
		this.currency = currency;
		this.dBFile = dBFile;
		this.owner = owner;
		this.broker = broker;
		this.productAcreage = productAcreage;
		this.productDescription = productDescription;
		this.catalogDetail = catalogDetail;
	}

	public Product(Long id, Boolean productStatus, Boolean nonDel, String productName, String productLocation,
			Long productCost, String productImage, String requestStatus, String zipCode,
			CustomerNotAuthority customerNotAuthority, Currency currency, DBFile dBFile, Owner owner, Broker broker,
			Admin admin, Long productAcreage, String productDescription, CatalogDetail catalogDetail) {
		super();
		this.id = id;
		this.productStatus = productStatus;
		this.nonDel = nonDel;
		this.productName = productName;
		this.productLocation = productLocation;
		this.productCost = productCost;
		this.productImage = productImage;
		this.requestStatus = requestStatus;
		this.zipCode = zipCode;
		this.customerNotAuthority = customerNotAuthority;
		this.currency = currency;
		this.dBFile = dBFile;
		this.owner = owner;
		this.broker = broker;
		this.admin = admin;
		this.productAcreage = productAcreage;
		this.productDescription = productDescription;
		this.catalogDetail = catalogDetail;
	}



	public DBFile getdBFile() {
		return dBFile;
	}

	public void setdBFile(DBFile dBFile) {
		this.dBFile = dBFile;
	}
    

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLocation() {
		return productLocation;
	}

	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}

	public Long getProductCost() {
		return productCost;
	}

	public void setProductCost(Long productCost) {
		this.productCost = productCost;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

//	public Broker getBroker() {
//		return broker;
//	}
//
//	public void setBroker(Broker broker) {
//		this.broker = broker;
//	}


	public Long getProductAcreage() {
		return productAcreage;
	}

	public void setProductAcreage(Long productAcreage) {
		this.productAcreage = productAcreage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public CatalogDetail getCatalogDetail() {
		return catalogDetail;
	}

	public void setCatalogDetail(CatalogDetail catalogDetail) {
		this.catalogDetail = catalogDetail;
	}

	public Boolean getNonDel() {
		return nonDel;
	}

	public void setNonDel(Boolean nonDel) {
		this.nonDel = nonDel;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public CustomerNotAuthority getCustomerNotAuthority() {
		return customerNotAuthority;
	}

	public void setCustomerNotAuthority(CustomerNotAuthority customerNotAuthority) {
		this.customerNotAuthority = customerNotAuthority;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", productStatus=" + productStatus + ", nonDel=" + nonDel + ", productName="
//				+ productName + ", productLocation=" + productLocation + ", productCost=" + productCost
//				+ ", productImage=" + productImage + ", requestStatus=" + requestStatus + ", currency=" + currency
//				+ ", owner=" + owner + ", broker=" + broker + ", dealHistory=" + dealHistory + ", productAcreage="
//				+ productAcreage + ", productDescription=" + productDescription + ", catalogDetail=" + catalogDetail
//				+ "]";
//	}
	
}
