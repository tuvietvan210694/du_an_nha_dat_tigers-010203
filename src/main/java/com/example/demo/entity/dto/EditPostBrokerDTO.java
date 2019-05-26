package com.example.demo.entity.dto;

public class EditPostBrokerDTO {
//	id: number;
//    productName: string;
//    productLocation: string;
//    productCost: number;
//    productAcreage: number;
//    fileId: number;
//    linkAnh: string;
	private long id;
	private String productName;
	private String productLocation;
	private Long productCost;
	private Long productAcreage;
	private String requestStatus;
	private long fileId;
	private String linkAnh;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
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
