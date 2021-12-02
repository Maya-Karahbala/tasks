package com.blulogix.springdemo.dao;

import java.util.List;
import java.util.Map;

import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.pojo.User;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User theCustomer);

	public User getUser(int theId);
	public User getUserByEmail(String email);

	public void deleteUser(int theId);
	public Response  getData(String className,Map<String, Object> filterParamters);
	
}
