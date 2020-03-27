package com.group12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.group12.dao.AccountDAO;
import com.group12.dao.CustomerDAO;
import com.group12.dao.InternalUserDAO;
import com.group12.models.Account;
import com.group12.models.Customer;
import com.group12.models.Request;
import com.group12.utils.Constants;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private InternalUserDAO internalUserDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/openAccount", method = RequestMethod.POST)
	public ModelAndView createNewAccount(ModelAndView model, HttpServletRequest request) {
		
		log.info(request.getParameter("acc_type") + "request" + request.toString());
		Account account = new Account();
		account.setAcc_type(request.getParameter("acc_type").charAt(0));
		account.setCurr_bal(Double.parseDouble(request.getParameter("balance")));
		account.setCust_id(Integer.parseInt(request.getParameter("cust_id")));
		accountDAO.createAccount(account);

		return model;
	}
	
	@RequestMapping(value = "/activateaccount", method = RequestMethod.POST)
	public ModelAndView activateCustomer(ModelAndView model, HttpServletRequest request) {
		String user_name = request.getParameter("user_name");
		String type = request.getParameter("type");
		int req_id = Integer.parseInt(request.getParameter("req_id"));
		Request customerRequest = new Request();
		customerRequest.setType(type);
		customerRequest.setReq_id(req_id);
		internalUserDAO.authorizeCustomerTransactions(customerRequest, null);
		model.addObject("message", Constants.TRANSACTION_SUCCESFUL);
		return model;
	}
	
	// customer details
	@RequestMapping(value= "/customer/Account", method = RequestMethod.GET)	
	public ModelAndView getAccount(ModelAndView model, HttpServletRequest request) throws IOException{
		
		String userName = request.getParameter("user_name");
		// Get the details of the customer using the userName
	    Customer customer = customerDAO.getCustomerProfileDetails(userName);
	    // Get the accounts from customer id 
	    List<Account> accounts = accountDAO.getAccountDetails(customer.getCust_id());
	    // set the session id with the customer id
	    model.addObject("getAccount", accounts);
	    model.setViewName("customerAccount");	 	    	    
		return model;
	}
	
	// credit and debit
	@RequestMapping(value ="/customer/creditOrDebit", method = RequestMethod.POST)
	public ModelAndView customerCreditOrDebit(ModelAndView model, HttpServletRequest request) {
		// TODO the assumptions that the UI is doing the checks for the valid amount
	    // Invalid amount should not travel through the controller
		int cust_id = Integer.parseInt(request.getParameter("cust_id"));
		int accountNumber = Integer.parseInt(request.getParameter("acc_no"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String typeOftransfer  = request.getParameter("type_transfer");
		Request customerRequest = new Request();
		int isCritical;
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(accountNumber);
		customerRequest.setType(typeOftransfer);
		customerRequest.setCust_id(cust_id);
		if(amount > 1000) {
			isCritical = 1;
		}
		accountDAO.createCreditOrDebitReq(customerRequest);
		// TODO let the DAO return the transaction message failure or success
//		String message = accountDAO.creditOrDebit(accountNumber, typeOftransfer, amount);
//		model.addObject("message",message);
//		return model;
		
		/*
		 * Then must return all of customers accounts back.
		 */
		
		return model;
	}
	
	@RequestMapping(value = "/approvedebitorcredit", method = RequestMethod.POST)
	public ModelAndView approveDebitOrCreditCustomer(ModelAndView model, HttpServletRequest request) {
		String type = request.getParameter("type");
		int req_id = Integer.parseInt(request.getParameter("req_id"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		int account_no = Integer.parseInt(request.getParameter("account_no"));
		Request customerRequest = new Request();
		customerRequest.setType(type);
		customerRequest.setReq_id(req_id);
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(account_no);
		internalUserDAO.authorizeCustomerTransactions(customerRequest, null);
		model.addObject("message", Constants.TRANSACTION_SUCCESFUL);
		return model;
	}
	
	// transfer funds
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
		/*
		 * Since this is for transferring between the same accounts we can just put transfer.	
		 */
		String typeOftransfer  = "transfer";//request.getParameter("type_transfer");
		Request  customerRequest = createRequest(amount, fromAccountNumber, toAccountNumber, typeOftransfer);
		accountDAO.transferFunds_create_request(customerRequest);
		// TODO we need to change the status from char since it is more readable for users to have success
		// TODO we need to add object to the model depending upon response
//		String message = accountDAO.transferFundsFromAcc(fromAccountNumber, toAccountNumber, amount, typeOftransfer);
//		model.addObject("message",message);
		return model;
	}
	
	// transfer funds via email or phone
	/*
	 * Need to check for the email or phone radio button then check the field of the correlating one
	 */
	@RequestMapping(value ="/customer/transferFundsEmailPhone", method = RequestMethod.POST)
	public ModelAndView customerTransferFundsEmailOrPhone(ModelAndView model, HttpServletRequest request) {
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
		Request  customerRequest = createRequest(amount, fromAccountNumber, toAccountNumber, typeOftransfer);
		accountDAO.transferFunds_create_request(customerRequest);
		// TODO we need to change the status from char since it is more readable for users to have success
		// TODO we need to add object to the model depending upon response
//			String message = accountDAO.transferFundsFromAcc(fromAccountNumber, toAccountNumber, amount, typeOftransfer);
//			model.addObject("message",message);
		return model;
	}
	
	// transfer funds between two different peoples account
	/*
	 * Have to check the account number first to make sure its an account
	 */
	@RequestMapping(value ="/customer/transferFundsOtherAccount", method = RequestMethod.POST)
	public ModelAndView customerTransferFundsToOtherCustomer(ModelAndView model, HttpServletRequest request) {
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
		Request  customerRequest = createRequest(amount, fromAccountNumber, toAccountNumber, typeOftransfer);
		accountDAO.transferFunds_create_request(customerRequest);
		// TODO we need to change the status from char since it is more readable for users to have success
		// TODO we need to add object to the model depending upon response
//			String message = accountDAO.transferFundsFromAcc(fromAccountNumber, toAccountNumber, amount, typeOftransfer);
//			model.addObject("message",message);
		return model;
	}
	
	
	
	@RequestMapping(value ="/customer/accountManagement/{flag}", method = RequestMethod.POST)
	public ModelAndView accountManagement(ModelAndView model, HttpServletRequest request, @PathVariable("flag") String flag) {
		// This helps to track the customer for which we need either create account or delete account
		String userName = request.getParameter("user_name");
		
		// Using the user name the customer information is fetched
		Customer customer = customerDAO.getCustomerProfileDetails(userName);
		
		int account_no = Integer.parseInt(request.getParameter("account_no"));
		//TODO UI should set this flag depending upon the select or create account
//		int create_account_flag = Integer.parseInt(request.getParameter("create_account_flag"));
//		int delete_account_flag = Integer.parseInt(request.getParameter("delete_account_flag"));
		String msg = "";
		//1 for delete, 0 for create
		int check_flag = Integer.parseInt(flag);
		if(check_flag == 0) {
			Account account = new Account();
			account.setCurr_bal(0.0);
			account.setCust_id(customer.getCust_id());
			// TODO Currently setting the account type as the credit
			// TODO need to change the hardcoding to the constants
			account.setAcc_type('C');
			accountDAO.createAccount(account);
			// TODO check the status from the DAO and set the model parameter
			msg = "Account created.";
		}
		// TODO expectation that the button is radio hence either creating account or deleting can happen
		// hence elif and not if
		else if (check_flag  == 1) {
			accountDAO.deleteAccount(account_no, customer.getCust_id(), 0);
			// TODO check the status from the DAO and then update the status to the model
			msg = "Account deleted.";
			
		}
		model = new ModelAndView("/customer/accountManagement");
		model.addObject("message", msg);
		return model;
	}
	
	@RequestMapping(value ="/customer/authorizeRequest", method = RequestMethod.POST)
	public ModelAndView acceptRequest(ModelAndView model, HttpServletRequest request, @ModelAttribute("auth") String accOrdec) {
		/*
		 * Need to be able to allow customer to accept/decline a transfer request from another customer
		 * and take it out of the request list and take money from account. Then return the list of requests
		 * and a message based on accept/decline.
		 */
		model.setViewName("makePayment");
		return model;
	}
	
	@RequestMapping(value ="/customer/changeProfile", method = RequestMethod.POST)
	public ModelAndView changeProfile(ModelAndView model, HttpServletRequest request, @ModelAttribute("auth") String accOrdec) {
		/*
		 * Need to allow customer to put in a request to change their profile information and send back 
		 * their profile information and a message saying their request has been submitted.
		 * 
		 * Think instead of sending back the same info we can use
		 * model = new ModelAndView("/customer/profile") which calls the url and will return the information and 
		 * add the message to the model. 
		 */
		
		return model;
	}
	
	@RequestMapping(value ="/customer/donwloadBankingStatements", method = RequestMethod.POST)
	public ModelAndView downloadStatements(ModelAndView model, HttpServletRequest request, @ModelAttribute("auth") String accOrdec) {
		/*
		 * Need to allow customer to download their banking statements.
		 * Can return a model back to the accountManagement page
		 * model = new ModelAndView("/customer/accountMangement");
		 */
		
		return model;
	}
	
	

	private Request createRequest(double amount, int fromAccountNumber, int toAccountNumber, String typeOftransfer) {
		Request customerRequest = new Request();
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(fromAccountNumber);
		customerRequest.setSecond_acc_num(toAccountNumber);
		customerRequest.setType(typeOftransfer);
		return customerRequest;
		}
}
