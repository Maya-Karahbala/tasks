package com.blulogix.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blulogix.springdemo.dao.ProductDAO;
import com.blulogix.springdemo.dao.UserDAO;
import com.blulogix.springdemo.pojo.Product;
import com.blulogix.springdemo.models.ProductModel;
import com.blulogix.springdemo.models.UserError;
import com.blulogix.springdemo.pojo.User;

@Service
public class ProductServiceImpl implements ProductService {
	// need to inject customer dao
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<Product> getProducts() {

		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public Product saveProduct(ProductModel product) {
		User user=userDAO.getUser(product.getUserId());
		if (user == null) throw new UserError("User is not exists");
		Product p= new Product(product.getName(), user);
		
		productDAO.saveProduct(p);
		return p;

	}

	@Override
	@Transactional
	public Product getProduct(int theId) {

		return productDAO.getProduct(theId);
	}

	@Override
	@Transactional
	public void deleteProduct(int theId) {
		productDAO.deleteProduct(theId);

	}

}
