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
import com.group12.models.Customer;


@Controller
public class CustomerRegistrationController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="/confirmationAccount", method = RequestMethod.POST)
	public ModelAndView showConfirmationAccount(ModelAndView model,HttpServletRequest request) {
			logger.info(request.getParameter("firstName"));
			
			
			model.setViewName("confirmationAccount");
			if(customerDAO.checkIfMobileNumExists(request.getParameter("mobile"))) {
				return model;
			}
			if(customerDAO.checkIfEmailExists(request.getParameter("email"))) {
				return model;
			}
			if(customerDAO.checkIfUserNameExists(request.getParameter("username"))) {
				return model;
			}
			Customer customer  = createCustomer(request);
			customerDAO.insertCutomerData(customer); // should actually call customerDAO.register(customer)
			
				return model;
			
}

	private Customer createCustomer(HttpServletRequest request) {
		Customer customer  = new Customer();
		customer.setAddress(request.getParameter("address"));
		customer.setAge(Integer.parseInt(request.getParameter("age")));
		customer.setCity(request.getParameter("city"));
		customer.setEmail(request.getParameter("email"));
		customer.setFirstName(request.getParameter("firstName"));
		customer.setMobile(request.getParameter("mobile"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setPassword(request.getParameter("password"));
		customer.setUsername(request.getParameter("username"));
		customer.setZipCode(request.getParameter("zip"));
		customer.setType(request.getParameter(request.getParameter("type")).charAt(0));
		customer.setState(request.getParameter("state"));
		
		return customer;
	}

	
	
}
