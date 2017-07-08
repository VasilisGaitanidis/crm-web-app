package com.vasilisgaitanidis.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vasilisgaitanidis.springdemo.dao.CustomerDAO;
import com.vasilisgaitanidis.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// inject a customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	

	/* Service Layer defines the beginning of transactions
	 * with the @Transactional Spring manages starting and stopping the hibernate
	 * transactions automatically
	 */	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {		
		return customerDAO.getCustomers();
	}

}
