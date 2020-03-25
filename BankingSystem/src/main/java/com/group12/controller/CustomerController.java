package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.AccountDAO;
import com.group12.dao.CustomerDAO;
import com.group12.dao.CustomerRequestDAO;
import com.group12.models.Account;
import com.group12.models.Customer;
import com.group12.models.Request;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/customer/profile", method=RequestMethod.GET)
	public ModelAndView getCustomerDetails(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		String userName = request.getParameter("user_id");
//		Customer customer = customerDAO.getCustomerProfileDetails(userName);
//		// TODO need to do the integration with the UI parameters
//		model.addObject("First name", customer.getFirstName());
//		model.addObject("Last name", customer.getLastName());
//		model.addObject("phone", customer.getMobile());
//		model.addObject("address", customer.getAddress());
//		model.addObject("city", customer.getCity());
//		model.addObject("state", customer.getState());
//		model.addObject("zip", customer.getZipCode());
//		model.addObject("age", customer.getCity());
//		model.addObject("email", customer.getEmail());
//		model.addObject("user name", customer.getUsername());
		model.addObject("FirstName", userName);
		return model;
	}
	
	
	@RequestMapping(value = "/customer/requestfunds")
	public ModelAndView requestFunds(ModelAndView model, HttpServletRequest request) {
		// Assumption that UI will be sending the session id of the user
		int from_account_no = Integer.parseInt(request.getParameter("from_account"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("type");
		int cust_id = Integer.parseInt(request.getParameter("cust_id"));
		int isCritical;
		if (amount > 1000) {
			isCritical = 1;
		} else {
			isCritical = 0;
		}
		Request customerRequest = new Request();
		customerRequest.setAmount(amount);
		customerRequest.setSecond_acc_num(from_account_no);
		customerRequest.setType(type);
		customerRequest.setIs_critical(isCritical);
		customerRequest.setCust_id(cust_id);
		accountDAO.transferFunds_create_request(customerRequest);
		model.addObject("message", "Sucess");
		return model;
	}
	
	@RequestMapping(value = "/approvecustomerpaymentrequest")
	public ModelAndView approveCustomerPayment(ModelAndView model, HttpServletRequest request) {
		int to_account_no = Integer.parseInt(request.getParameter("to_account"));
		int req_id = Integer.parseInt(request.getParameter("req_id"));
		Request customerRequest = new Request();
		customerRequest.setFirst_acc_num(to_account_no);
		customerRequest.setReq_id(req_id);
		customerDAO.authorizeRequestCust(customerRequest, null);
		model.addObject("message", "Sucess");
		return model;
	}

}
