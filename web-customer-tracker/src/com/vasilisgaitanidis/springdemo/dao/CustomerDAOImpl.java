package com.vasilisgaitanidis.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vasilisgaitanidis.springdemo.entity.Customer;

/**
 * @author Vasilis
 *
 */

/* 
 * with the @Repository Spring component scans and handles the exception translation
 * it always applied to DAO implementations
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject the Hibernate session factory from the bean id sessionFactory
	@Autowired 
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query HQL
		// Hibernate 5.2+ for Query
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the customer given the id
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		// return the result
		return theCustomer;
	}

}
