package com.vasilisgaitanidis.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	// inject the hibernate session factory from the bean id sessionFactory
	@Autowired 
	private SessionFactory sessionFactory;
	
	
	/*
	 * with the @Transactional Spring manages starting and stopping the hibernate
	 * transactions automatically
	 */	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
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

}
