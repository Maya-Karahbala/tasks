package com.blulogix.springdemo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blulogix.springdemo.dao.UserDAO;
import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.pojo.User;


@Service
public class UserServiceImpl implements UserService {

	// need to inject customer dao
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
		
	}
	@Override
	@Transactional
	public Response  getData(String className,	Map<String, Object> filterParamters) {

		return userDAO.getData(className,filterParamters);
	}
	
	@Override
	@Transactional
	public boolean emailIsExists(User user) {
		// check if email is esists befor saving user
		List<User> users= userDAO.getUsers();
		for (User u : users) {
			// email matches with esisting email and not check for the same user
			if(user.getEmail().equals(u.getEmail()) && u.getId()!= user.getId()) {
				return true;
			}
			
		}
		
		return false;
	}
	@Override
	@Transactional
	public void saveUser(User user) {

		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		
		userDAO.deleteUser(theId);
	}
	
	@Override
	@Transactional
	public String validateUser(User user) {
		// check if request user has no email or no password
		if(user.getPassword()==null || user.getEmail()==null) {
			return "no matched user";
		}
		// check if user with same email is exists in db
		User matchedUser= userDAO.getUserByEmail(user.getEmail());
		if(matchedUser!= null ) {
			// check if user with same email  has password equal to entered password
			if(matchedUser.getPassword().equals(user.getPassword())) {
				return "user is exists";
			}
			
		}
		
		return "no matched user";
	}
}





