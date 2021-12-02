package com.blulogix.springdemo.service;

import java.util.List;

import com.blulogix.springdemo.pojo.Product;
import com.blulogix.springdemo.models.ProductModel;


public interface ProductService {

	public List<Product> getProducts();

	public Product saveProduct(ProductModel product);

	public Product getProduct(int theId);

	public void deleteProduct(int theId);

}
