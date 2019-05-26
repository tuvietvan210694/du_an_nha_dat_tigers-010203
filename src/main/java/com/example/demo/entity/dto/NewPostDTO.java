package com.example.demo.entity.dto;

public class NewPostDTO {
    
	private int id;
	private long productAcreage;
	private long productCost;
	private String productDescription;
	private String productImage;
	private String productLocation;
	private String productName;
	private Boolean productStatus;
	private String requestStatus;
	private String zipCode;
	//private long brokerId;
	//cai brokerId la khong can thiet de dang bai len...
	private long currencyId;
	private long ownerId;
	private long catalogDetailId;
	
	public NewPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public NewPostDTO(int id, long productAcreage, long productCost, String productDescription, String productImage,
			String productLocation, String productName, Boolean productStatus, String requestStatus, String zipCode,
			long currencyId, long ownerId, long catalogDetailId) {
		super();
		this.id = id;
		this.productAcreage = productAcreage;
		this.productCost = productCost;
		this.productDescription = productDescription;
		this.productImage = productImage;
		this.productLocation = productLocation;
		this.productName = productName;
		this.productStatus = productStatus;
		this.requestStatus = requestStatus;
		this.zipCode = zipCode;
		this.currencyId = currencyId;
		this.ownerId = ownerId;
		this.catalogDetailId = catalogDetailId;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public long getProductAcreage() {
		return productAcreage;
	}
	public void setProductAcreage(long productAcreage) {
		this.productAcreage = productAcreage;
	}
	public long getProductCost() {
		return productCost;
	}
	public void setProductCost(long productCost) {
		this.productCost = productCost;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductLocation() {
		return productLocation;
	}
	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Boolean getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}
	
	public long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public long getCatalogDetailId() {
		return catalogDetailId;
	}
	public void setCatalogDetailId(long catalogDetailId) {
		this.catalogDetailId = catalogDetailId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
		
}
