package com.vasilisgaitanidis.springdemo.dao;

import java.util.List;

import com.vasilisgaitanidis.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomersByName(String theCustomerName);
}
