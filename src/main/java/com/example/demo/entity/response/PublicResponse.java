package com.example.demo.entity.response;

import com.example.demo.entity.DBFile;

public class PublicResponse {
	private long id;
	private String productLocation;
	//private String catalogDetailName;
	private Long catalogDetailId;
	private long productId;
	private boolean productStatus;
	private boolean nonDel;
	private String productName;
	private long productCost;
	private String productImage;
	private String requestStatus;
	private String currencyName;
	private DBFile dBFile;
	public PublicResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public PublicResponse(long id, String productLocation, Long catalogDetailId, long productId, boolean productStatus,
			boolean nonDel, String productName, long productCost, String productImage, String requestStatus,
			String currencyName, DBFile dBFile) {
		super();
		this.id = id;
		this.productLocation = productLocation;
		this.catalogDetailId = catalogDetailId;
		this.productId = productId;
		this.productStatus = productStatus;
		this.nonDel = nonDel;
		this.productName = productName;
		this.productCost = productCost;
		this.productImage = productImage;
		this.requestStatus = requestStatus;
		this.currencyName = currencyName;
		this.dBFile = dBFile;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductLocation() {
		return productLocation;
	}
	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}
	
	

	public Long getCatalogDetailId() {
		return catalogDetailId;
	}



	public void setCatalogDetailId(Long catalogDetailId) {
		this.catalogDetailId = catalogDetailId;
	}



	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public boolean isProductStatus() {
		return productStatus;
	}
	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}
	public boolean isNonDel() {
		return nonDel;
	}
	public void setNonDel(boolean nonDel) {
		this.nonDel = nonDel;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProductCost() {
		return productCost;
	}
	public void setProductCost(long productCost) {
		this.productCost = productCost;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public DBFile getdBFile() {
		return dBFile;
	}
	public void setdBFile(DBFile dBFile) {
		this.dBFile = dBFile;
	}
	
	
}
