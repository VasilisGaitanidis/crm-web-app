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

	@Override
	public void deleteCustomer(int theId) {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// HQL delete query
		String hql = "delete from Customer where id=:customerId";
		
		// create query
		Query theQuery = currentSession.createQuery(hql);
		
		// SQL injection purposes
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		// Alternate method 1 to delete customer (less accurate)
		/* 
		 * Customer customer = currentSession.get(Customer.class, id);
		 * currentSession.delete(customer);
		 */
		// this approach requires two database operation
		// one to get the customer for db and another one to delete
		
		// Alternate method 2 to delete customer without HQL
		/*
		 *Customer theCustomer = new Customer();
		 *theCustomer.setId(theId);
		 *session.delete(theCustomer); 
		 */
	}

	@Override
	public List<Customer> searchCustomersByName(String theSearchName) {
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = 	null;
		
		// trim whitespaces from the search name
		String trimmedSearchName = theSearchName.trim();
		System.out.println(trimmedSearchName);
		
		// search only by name if theSearchName is not empty
		
		if (trimmedSearchName != null && trimmedSearchName.length() > 0) {
			
			// Hibernate Query Language
			String hql = "from Customer where first_name like :searchedName or last_name like :searchedName";
			theQuery = currentSession.createQuery(hql, Customer.class);
			
			// SQL injection purposes
			// with the % we find any value including the searched name
			theQuery.setParameter("searchedName", "%" + trimmedSearchName + "%");
			
		}
		else {
			// the search name is empty .. list all the customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
				
		// execute query and get a result list of customers
		List<Customer> theCustomers = theQuery.getResultList();
		
		// return the customers
		return theCustomers;
	}

}
