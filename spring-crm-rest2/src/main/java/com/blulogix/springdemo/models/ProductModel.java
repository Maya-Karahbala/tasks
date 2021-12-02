package com.blulogix.springdemo.models;

public class ProductModel {
	private int id;
	private String name;
	private int userId;
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}
	public ProductModel(String name, int userId) {
		super();
		this.name = name;
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
