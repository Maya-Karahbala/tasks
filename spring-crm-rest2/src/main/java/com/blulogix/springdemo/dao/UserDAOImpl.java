package com.blulogix.springdemo.dao;


import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blulogix.springdemo.models.Response;
import com.blulogix.springdemo.models.UserError;
import com.blulogix.springdemo.pojo.User;



@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<User> getUsers() {
		
		// get the current hibernate session
		Session currentSession = getSesion();
				
		// create a query  ... sort by last name
		Query<User> theQuery = currentSession.createQuery("from User",
											User.class);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();
				
		// return the results		
		
		return users;
	}

	@Override
	public void saveUser(User theUser) {

		// get current hibernate session
		Session currentSession = getSesion();
		
		// save/upate the theUser ... finally LOL
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public User getUser(int theId) {

		// get the current hibernate session
		Session currentSession = getSesion();
		
		// now retrieve/read from database using the primary key
		User theUser = currentSession.get(User.class, theId);
		
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {

		// get the current hibernate session
		Session currentSession = getSesion();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from User where id=:userId");
		theQuery.setParameter("userId", theId);
		
		theQuery.executeUpdate();		
	}

	@Override
	public User getUserByEmail(String email) {
		// get the current hibernate session
		Session currentSession = getSesion();
		
		Query theQuery = 
				currentSession.createQuery("select u from User u where u.email=:email");
		theQuery.setParameter("email", email);
		List<User> users = theQuery.getResultList();
		
	
		
		return users.get(0);
	}

	@SuppressWarnings("deprecation")
	public Response  getData(String  className,Map<String, Object> filterParamters) {
		Response response=new Response();
		Criteria cr;
		List results = null;
		

		try {
			// get class feilds to checks conditions key if match class feilds
			ArrayList<String > fieldsNames= getFeilds(className);
			cr = getSesion().createCriteria(Class.forName(className));
			// add restrictions
			for (String key : filterParamters.keySet()) {
				if(fieldsNames.contains(key))
				cr.add(Restrictions.eq(key, filterParamters.get(key)));
				else  throw new UserError("undeclared field");
			}
			
			 results = cr.list();
			 response.setData(results);
			 return response;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
		
				
	}
	public ArrayList<String> getFeilds(String className){
		Field[] allFields=null;
		ArrayList<String > fieldsNames= new ArrayList<>();
		try {
			allFields = Class.forName(className).getDeclaredFields();
			for (Field field : allFields) {
				fieldsNames.add(field.getName());
			}
			
		} catch (SecurityException | ClassNotFoundException e) {

			e.printStackTrace();
		}
		return fieldsNames;
	}
	public Session getSesion() {
		Session currentSession;

		try {
			currentSession = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			currentSession = sessionFactory.openSession();
		}
		return currentSession;
	}

}











