package com.blulogix.springdemo.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue()
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	// relation between product and user
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	// name of joning filde inside course table in db
	@JoinColumn(name="user_id")
    @JsonIgnoreProperties("products")
	private User user;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
