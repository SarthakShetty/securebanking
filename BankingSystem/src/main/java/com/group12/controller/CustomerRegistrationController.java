package com.group12.controller;


import javax.servlet.http.HttpServletRequest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.CustomerDAO;
import com.group12.models.Customer;


@Controller
public class CustomerRegistrationController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="/confirmationAccount", method = RequestMethod.POST)
	public ModelAndView showConfirmationAccount(ModelAndView model,HttpServletRequest request, @ModelAttribute("type_user") String type_user) {
			logger.info(request.getParameter("firstName"));
			/*
			 * Type_user refers to the type of user either customer or employee
			 * Also going to need to send the email in here for the OTP and store the OTP.
			 */
			
			model.setViewName("newAccount");
			if(customerDAO.checkIfMobileNumExists(request.getParameter("mobile"))) {
				model.addObject("message", "Sorry phone number already used please enter another one.");
				return model;
			}
			if(customerDAO.checkIfEmailExists(request.getParameter("email"))) {
				model.addObject("message", "Sorry email already used please enter another one.");
				return model;
			}
			if(customerDAO.checkIfUserNameExists(request.getParameter("username"))) {
				model.addObject("message", "Sorry username already used please enter another one.");
				return model;
			}
			Customer customer  = createCustomer(request);
			customerDAO.insertCutomerData(customer); // should actually call customerDAO.register(customer)
			model.addObject("email", "customers email");
			model.setViewName("OTPAuth");
			return model;
			
}
	
	@RequestMapping(value="/verifyOTP", method = RequestMethod.POST)
	public ModelAndView verifyOTP(ModelAndView model,HttpServletRequest request, @ModelAttribute("type_user") String type_user) {
			/*
			 * Need to verify the OTP with the OTP assigned to specified customer
			 */
			model.setViewName("confirmationAccount");
			return model;
			
	}

	private Customer createCustomer(HttpServletRequest request) {
		Customer customer  = new Customer();
		customer.setAddress(request.getParameter("address"));
		// TODO UI needs to take the age of the customer currently hard coding
		customer.setAge(21);
		customer.setCity(request.getParameter("city"));
		customer.setEmail(request.getParameter("email"));
		customer.setFirstName(request.getParameter("firstName"));
		customer.setMobile(request.getParameter("mobile"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setPassword(request.getParameter("password"));
		customer.setUsername(request.getParameter("username"));
		customer.setZipCode(request.getParameter("zip"));
		/*
		 * Need to do age as well. 
		 */
		// TODO Currently hardcoding it as individual
		customer.setType('I');
		customer.setState(request.getParameter("state"));
		
		return customer;
	}

	
	
}
