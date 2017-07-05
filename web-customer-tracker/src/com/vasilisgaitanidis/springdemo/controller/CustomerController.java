package com.vasilisgaitanidis.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vasilisgaitanidis.springdemo.dao.CustomerDAO;
import com.vasilisgaitanidis.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the DAO into the controller
	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the DAO
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// add the customers to the Spring MVC model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}