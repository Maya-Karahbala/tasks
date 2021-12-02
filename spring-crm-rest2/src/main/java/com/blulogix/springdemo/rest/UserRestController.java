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

import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.pojo.User;
import com.blulogix.springdemo.models.UserError;
import com.blulogix.springdemo.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public List<User> getAllUsers(){

		return userService.getUsers();
	}
	@PostMapping("/data")
	public Response getAllData( @RequestBody Map<String, Object> filterParameters){


		return userService.getData(User.class.getName(),filterParameters);
	}
	
	@GetMapping("/{id}")
	public User getCustomer(@PathVariable int id){

		User customer= userService.getUser(id);
		if(customer==null) throw new UserError("no user");
		return customer;
	}

	@PutMapping("/")
	public User  updateUser(@RequestBody User user){
		// check if email is exsists before saving user
		if(userService.emailIsExists(user)) {
			throw new UserError("invalid email");
		}
	    userService.saveUser(user);
		return user;
	}
	@PostMapping("/login")
	public String validateUser(@RequestBody User user){
		return userService.validateUser(user);

	}
	@PostMapping("/")
	public User addUser(@RequestBody User user){
		
		// check if email is exists before saving user
		if(userService.emailIsExists(user)) {
			throw new UserError("user already has an account");
		}
	    userService.saveUser(user);
		return user;
	}
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable int id){

		User user= userService.getUser(id);
		if(user==null) throw new UserError("no user exists");
		userService.deleteUser(id);
		return user;
	}


}
