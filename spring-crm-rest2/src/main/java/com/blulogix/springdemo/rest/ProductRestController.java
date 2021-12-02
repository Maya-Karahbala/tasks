package com.blulogix.springdemo.rest;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blulogix.springdemo.pojo.Product;
import com.blulogix.springdemo.models.ProductModel;
import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.service.ProductService;
import com.blulogix.springdemo.service.UserService;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id){

		Product product= productService.getProduct(id);
		if(product==null) throw new Error();
		return product;
	}

	@PutMapping("/")
	public Product  updateProduct(@RequestBody ProductModel product){
		return productService.saveProduct(product);
	}

	@PostMapping("/")
	public Product addProduct(@RequestBody ProductModel product){
		
		return productService.saveProduct(product);
		
	}
	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable int id){
		Product product= productService.getProduct(id);
		if(product==null) throw new Error();
		productService.deleteProduct(id);
		return product;
	}
	@GetMapping("/")
	public List<Product> getAllProducts(){

		return productService.getProducts();
	}


	@PostMapping("/data")
	public Response getAllData( @RequestBody Map<String, Object> filterParameters){
		return userService.getData(Product.class.getName(),filterParameters);
	}
	
}
