package com.blulogix.springdemo.dao;

import java.util.List;

import com.blulogix.springdemo.pojo.Product;



public interface ProductDAO {

	public List<Product> getProducts();

	public void saveProduct(Product product);

	public Product getProduct(int theId);


	public void deleteProduct(int theId);
	
}
