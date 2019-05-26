package com.example.demo.entity.dto;

public class ProductDTO {
	private Long id;
	private String requestStatus;
	
	public ProductDTO(Long id, String requestStatus) {
		super();
		this.id = id;
		this.requestStatus = requestStatus;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRequestStatus() {
		return requestStatus;
	}


	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}


	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", requestStatus=" + requestStatus + "]";
	}
	

}
