package com.blulogix.springdemo.service;

import java.util.List;
import java.util.Map;

import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.pojo.User;

public interface UserService {

	public List<User> getUsers();
	public Response getData(String className,Map<String, Object> filterParamters);

	public void saveUser(User user);

	public User getUser(int theId);

	public void deleteUser(int theId);
	public String validateUser(User user);
	public boolean emailIsExists(User user);
	
}
