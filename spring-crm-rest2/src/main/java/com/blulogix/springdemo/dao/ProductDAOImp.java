package com.blulogix.springdemo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blulogix.springdemo.pojo.Product;



@Repository
public class ProductDAOImp implements ProductDAO {
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Product> getProducts() {
		Session currentSession = getSesion();
				

		// create a query 
		Query<Product> theQuery = currentSession.createQuery("from Product",
											Product.class);
		
		// execute query and get result list
		List<Product> products = theQuery.getResultList();
				
		// return the results		
		
		return products;
	}

	@Override
	public void saveProduct(Product product) {
		// get current hibernate session
		// get the current hibernate session
		Session currentSession = getSesion();
		
		// save/upate the theProducr ... f
		
		currentSession.saveOrUpdate(product);

	}

	@Override
	public Product getProduct(int theId) {

		// get the current hibernate session
		Session currentSession = getSesion();
		
		// now retrieve/read from database using the primary key
		Product product = currentSession.get(Product.class, theId);
		
		return product;
	}

	@Override
	public void deleteProduct(int theId) {
		// get the current hibernate session
		Session currentSession = getSesion();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Product where id=:productId");
		theQuery.setParameter("productId", theId);
		
		theQuery.executeUpdate();		

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
