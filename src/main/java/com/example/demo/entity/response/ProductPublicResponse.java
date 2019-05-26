package com.example.demo.entity.response;

public class ProductPublicResponse {
	
	private Long id;
	private String productName;
	private String productLocation;
	private Long productCost;
	private Long productAcreage;
	private String requestStatus;
	private Long fileId;
	private String linkAnh;
	
	public ProductPublicResponse() {
		super();
	}
	
	public ProductPublicResponse(Long id, String productName, String productLocation, Long productCost,
			Long productAcreage, Long fileId, String linkAnh) {
		super();
		this.id = id;
		this.productName = productName;
		this.productLocation = productLocation;
		this.productCost = productCost;
		this.productAcreage = productAcreage;
		this.fileId = fileId;
		this.linkAnh = linkAnh;
	}
	

	public ProductPublicResponse(Long id, String productName, String productLocation, Long productCost,
			Long productAcreage, String requestStatus, Long fileId, String linkAnh) {
		super();
		this.id = id;
		this.productName = productName;
		this.productLocation = productLocation;
		this.productCost = productCost;
		this.productAcreage = productAcreage;
		this.requestStatus = requestStatus;
		this.fileId = fileId;
		this.linkAnh = linkAnh;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getProductAcreage() {
		return productAcreage;
	}
	public void setProductAcreage(Long productAcreage) {
		this.productAcreage = productAcreage;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getLinkAnh() {
		return linkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		this.linkAnh = linkAnh;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	

}
