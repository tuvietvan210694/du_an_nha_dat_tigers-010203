package com.example.demo.entity.dto;

public class TestThuDTO {
	
	private int a;
	private String b;
	private Boolean active;
	
	public TestThuDTO() {
		super();
	}

	public TestThuDTO(int a, String b, Boolean active) {
		super();
		this.a = a;
		this.b = b;
		this.active = active;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
		
}
