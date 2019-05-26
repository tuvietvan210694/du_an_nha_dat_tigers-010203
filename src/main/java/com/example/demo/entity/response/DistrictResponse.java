package com.example.demo.entity.response;

import java.io.Serializable;

public class DistrictResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String maDistrict;
    private String nameDistrict;
	public DistrictResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistrictResponse(Long id, String maDistrict, String nameDistrict) {
		super();
		this.id = id;
		this.maDistrict = maDistrict;
		this.nameDistrict = nameDistrict;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaDistrict() {
		return maDistrict;
	}
	public void setMaDistrict(String maDistrict) {
		this.maDistrict = maDistrict;
	}
	public String getNameDistrict() {
		return nameDistrict;
	}
	public void setNameDistrict(String nameDistrict) {
		this.nameDistrict = nameDistrict;
	}
	@Override
	public String toString() {
		return "DistrictResponse [id=" + id + ", maDistrict=" + maDistrict + ", nameDistrict=" + nameDistrict + "]";
	}
    
}
