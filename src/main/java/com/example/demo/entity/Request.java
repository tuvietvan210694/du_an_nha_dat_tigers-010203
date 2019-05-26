package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "request")
public class Request {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "request_content", nullable = true)
	private String requestContent;
	
	@Column(name = "request_content_reply", nullable = true)
	private String requestContentReply;
	
	@Column(name = "request_status", nullable = false, columnDefinition = "SMALLINT default 0")
	private Boolean requestStatus = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false)
	private Date updateTime;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;
	

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(Long id, String requestContent, String requestContentReply, Boolean requestStatus, Date updateTime,
			User user) {
		super();
		this.id = id;
		this.requestContent = requestContent;
		this.requestContentReply = requestContentReply;
		this.requestStatus = requestStatus;
		this.updateTime = updateTime;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestContentReply() {
		return requestContentReply;
	}

	public void setRequestContentReply(String requestContentReply) {
		this.requestContentReply = requestContentReply;
	}

	public Boolean getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Boolean requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", requestContent=" + requestContent + ", requestContentReply="
				+ requestContentReply + ", requestStatus=" + requestStatus + ", updateTime=" + updateTime + ", user="
				+ user + "]";
	}

}
