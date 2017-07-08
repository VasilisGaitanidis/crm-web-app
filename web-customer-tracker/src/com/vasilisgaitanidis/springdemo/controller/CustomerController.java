package com.vasilisgaitanidis.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vasilisgaitanidis.springdemo.entity.Customer;
import com.vasilisgaitanidis.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the Service Layer into the controller
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service layer
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the Spring MVC model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}
