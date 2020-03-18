package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.CustomerDAO;
import com.group12.models.Account;
import com.group12.models.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/customer/profile")
	public ModelAndView getCustomerDetails(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		String userName = request.getParameter("user_id");
		Customer customer = customerDAO.getCustomerProfileDetails(userName);
		// TODO need to do the integration with the UI parameters
		model.addObject("First name", customer.getFirstName());
		model.addObject("Last name", customer.getLastName());
		model.addObject("phone", customer.getMobile());
		model.addObject("address", customer.getAddress());
		model.addObject("city", customer.getCity());
		model.addObject("state", customer.getState());
		model.addObject("zip", customer.getZipCode());
		model.addObject("age", customer.getCity());
		model.addObject("email", customer.getEmail());
		model.addObject("user name", customer.getUsername());
		return model;
	}
	
	
	

}
