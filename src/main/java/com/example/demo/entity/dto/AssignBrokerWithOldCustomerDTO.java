package com.example.demo.entity.dto;

public class AssignBrokerWithOldCustomerDTO {
    
	private long id;
	private long brokerId;
	private long ownerId;
	public AssignBrokerWithOldCustomerDTO(long id, long brokerId, long ownerId) {
		super();
		this.id = id;
		this.brokerId = brokerId;
		this.ownerId = ownerId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
}
