package com.group12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.group12.dao.AccountDAO;
import com.group12.dao.CustomerDAO;
import com.group12.models.Account;
import com.group12.models.Customer;
import com.group12.models.Request;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/openAccount", method = RequestMethod.POST)
	public ModelAndView createNewAccount(ModelAndView model, HttpServletRequest request) {
		log.info(request.getParameter("acc_type") + "request" + request.toString());
		Account account = new Account();
//		account.setAcc_type(request.getParameter("acc_type").charAt(0));
//		account.setCurr_bal(Double.parseDouble(request.getParameter("balance")));
//		account.setCust_id(Integer.parseInt(request.getParameter("cust_id")));
//		accountDAO.createAccount(account);
		account.setAcc_type('s');
		account.setCurr_bal(100);
		account.setCust_id(1);
		accountDAO.createAccount(account);
		return model;
	}
	
	@RequestMapping(value= "/customer/Account")	
	public ModelAndView getAccount(ModelAndView model, HttpServletRequest request, HttpSession session) throws IOException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		String userName = authentication.getName();
		session.setAttribute("userid", userName);
		// Get the details of the customer using the userName
	    Customer customer = customerDAO.getCustomerProfileDetails(userName);
	    // Get the accounts from customer id 
	    List<Account> accounts = accountDAO.getAccountDetails(customer.getCust_id());
	    // set the session id with the customer id
	    session.setAttribute("customerId", customer.getCust_id());
	    model.addObject("getAccount", accounts);
	    model.setViewName("account");	 	    	    
		return model;
	}
	
	@RequestMapping(value ="/customer/creditOrDebit", method = RequestMethod.POST)
	public ModelAndView customerCreditOrDebit(ModelAndView model, HttpServletRequest request) {
		// TODO the assumptions that the UI is doing the checks for the valid amount
	    // Invalid amount should not travel through the controller
		int accountNumber = Integer.parseInt(request.getParameter("acc_no"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String typeOftransfer  = request.getParameter("type_transfer");
		Request customerRequest = new Request();
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(accountNumber);
		customerRequest.setType(typeOftransfer);
		accountDAO.creditOrDebit(customerRequest);
		// TODO let the DAO return the transaction message failure or success
//		String message = accountDAO.creditOrDebit(accountNumber, typeOftransfer, amount);
//		model.addObject("message",message);
//		return model;
		
		
		return model;
	}
	
	@RequestMapping(value ="/customer/transferFunds", method = RequestMethod.POST)
	public ModelAndView customerTransferFunds(ModelAndView model, HttpServletRequest request) {
		// TODO the assumptions that the UI is doing the checks for the valid amount
	    // Invalid amount should not travel through the controller
		int fromAccountNumber = Integer.parseInt(request.getParameter("from_acc"));
		int toAccountNumber = Integer.parseInt(request.getParameter("to_acc"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		// TODO data base has this field as character either change the database or the logic
		int isCritical;
		if(amount > 1000) {
			isCritical = 1;
		} else { 
			// TODO revist the logic
			// DO we need customer approval here I dont think so 
			isCritical = 0;
		}
			
		String typeOftransfer  = request.getParameter("type_transfer");
		Request customerRequest = new Request();
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(fromAccountNumber);
		customerRequest.setSecond_acc_num(toAccountNumber);
		customerRequest.setType(typeOftransfer);
		customerRequest.setIs_critical(isCritical);
		accountDAO.transferFunds_create_request(customerRequest);
		// TODO we need to change the status from char since it is more readable for users to have success
		// TODO we need to add object to the model depending upon response
//		String message = accountDAO.transferFundsFromAcc(fromAccountNumber, toAccountNumber, amount, typeOftransfer);
//		model.addObject("message",message);
		return model;
	}
	
	
	@RequestMapping(value ="/customer/accountManagement", method = RequestMethod.POST)
	public ModelAndView accountManagement(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// This helps to track the customer for which we need either create account or delete account
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		String userName = authentication.getName();
		session.setAttribute("userid", userName);
		
		// Using the user name the customer information is fetched
		Customer customer = customerDAO.getCustomerProfileDetails(userName);
		
		int account_no = Integer.parseInt(request.getParameter("account_no"));
		//TODO UI should set this flag depending upon the select or create account
		int create_account_flag = Integer.parseInt(request.getParameter("create_account_flag"));
		int delete_account_flag = Integer.parseInt(request.getParameter("delete_account_flag"));
		if(create_account_flag == 1) {
			Account account = new Account();
			account.setCurr_bal(0.0);
			account.setCust_id(customer.getCust_id());
			// TODO Currently setting the account type as the credit
			// TODO need to change the hardcoding to the constants
			account.setAcc_type('C');
			accountDAO.createAccount(account);
			// TODO check the status from the DAO and set the model parameter
		}
		// TODO expectation that the button is radio hence either creating account or deleting can happen
		// hence elif and not if
		else if (delete_account_flag  == 1) {
			accountDAO.deleteAccount(account_no, customer.getCust_id(), 0);
			// TODO check the status from the DAO and then update the status to the model
			
		}
		return model;
	}
	
	
}
