package com.group12.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		int cust_id = (Integer) request.getSession().getAttribute("cust_id");
	    List<Account> accounts = accountDAO.getAccountDetails(cust_id);
	   
	    model.addObject("accountList", accounts);
	    model.setViewName("customerAccount");	 	    	    
		return model;
	}
	
	// credit and debit
	@RequestMapping(value ="/customer/creditOrDebit", method = {RequestMethod.GET, RequestMethod.POST})
	public RedirectView customerCreditOrDebit(RedirectView model, HttpServletRequest request, @RequestParam("account") String accountNum,
			@RequestParam("transferAmount") String transferAmount, @RequestParam("type_request") String type_request, RedirectAttributes attr) {
		// TODO the assumptions that the UI is doing the checks for the valid amount
	    // Invalid amount should not travel through the controller
		/*
		 * We can use session to hold cust id, so request.getSession().getAttribute("cust_id");
		 */
		model = new RedirectView("/customer/CreditDebit");
		if(!transferAmount.matches("^[0-9]+$")){
			attr.addFlashAttribute("error_msg", "Please enter valid characters in the amount. ");
			return model;
		}
		int cust_id = (Integer)request.getSession().getAttribute("cust_id");
		int accountNumber = Integer.parseInt(accountNum);
		double amount = Double.parseDouble(transferAmount);
		String typeOftransfer  = type_request;
		Request customerRequest = new Request();
		String msg = "";
		int isCritical;
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(accountNumber);
		customerRequest.setType(typeOftransfer);
		customerRequest.setCust_id(cust_id);
		if(amount > 1000) {
			msg = "Request Submitted.";
			isCritical = 1;
		}
		else
		{
			msg = "Transfer completed.";
		}
		accountDAO.createCreditOrDebitReq(customerRequest);
		// TODO let the DAO return the transaction message failure or success
//		String message = accountDAO.creditOrDebit(accountNumber, typeOftransfer, amount);
//		model.addObject("message",message);
//		return model;
		attr.addFlashAttribute("msg", msg);
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
	public RedirectView customerTransferFunds(RedirectView model, HttpServletRequest request, @RequestParam("transferAmount") String transferAmount,
			@RequestParam("from_acc") String from, @RequestParam("to_acc") String to, RedirectAttributes attr) {
		// TODO the assumptions that the UI is doing the checks for the valid amount
	    // Invalid amount should not travel through the controller
		model = new RedirectView("/customer/transferBA");
		if(!transferAmount.matches("^[0-9]+$")){
			attr.addFlashAttribute("error_msg", "Please enter valid characters for the amount.");
			return model;
		}
		
		String msg = "";
		int fromAccountNumber = Integer.parseInt(request.getParameter("from_acc"));
		int toAccountNumber = Integer.parseInt(request.getParameter("to_acc"));
		double amount = Double.parseDouble(request.getParameter("transferAmount"));
		// TODO data base has this field as character either change the database or the logic
		int isCritical;
		if(amount > 1000) {
			isCritical = 1;
			msg = "Transfer request submitted.";
		} else { 
			// TODO revist the logic
			// DO we need customer approval here I dont think so 
			isCritical = 0;
			msg = "Transfer completed";
		}
		/*
		 * Since this is for transferring between the same accounts we can just put transfer.	
		 */
		String typeOftransfer  = "transfer";//request.getParameter("type_transfer");
		Request  customerRequest = createRequest((int)request.getSession().getAttribute("cust_id"),amount, fromAccountNumber, toAccountNumber, typeOftransfer);
		accountDAO.transferFunds_create_request(customerRequest);
		// TODO we need to change the status from char since it is more readable for users to have success
		// TODO we need to add object to the model depending upon response
//		String message = accountDAO.transferFundsFromAcc(fromAccountNumber, toAccountNumber, amount, typeOftransfer);
//		model.addObject("message",message);
		/*
		 * Need to return a message of successful. 
		 */
		//we can do something like this since we're redirecting, however it will be a GET so people will see the message.
		attr.addFlashAttribute("msg", msg);
		return model;
	}
	
	// transfer funds via email or phone
	/*
	 * Need to check for the email or phone radio button then check the field of the correlating one
	 */
	@RequestMapping(value = "/customer/transferFundsEmailPhone", method = RequestMethod.POST)
	public RedirectView customerTransferFundsEmailOrPhone(RedirectView model, HttpServletRequest request,
			RedirectAttributes attr) {

		model = new RedirectView("/customer/transferEmailPhone");

		String transferAmount = request.getParameter("peAmount");
		String from_accP = request.getParameter("from_accP");

		if (!transferAmount.matches("^[0-9]+$")) {
			attr.addFlashAttribute("error_msg", "Please enter valid characters for the amount.");
			return model;
		}

		String type = request.getParameter("type");
		int cust_id = -1;
		if ("phone".equals(type)) {
			String phoneNum = request.getParameter("phoneNumber");
			if (!phoneNum.matches("^[-0-9]+$")) {
				attr.addFlashAttribute("error_msg", "Please enter valid characters for the phone number.");
				return model;
			}

			try {
				cust_id = customerDAO.getCutomerIdFromMobile(phoneNum);
			} catch (Exception ex) {
				attr.addFlashAttribute("error_msg", "Customer Is Not a Valid Customer.");
				return model;
			}

		} else if ("email".equals(type)) {
			String emailAddress = request.getParameter("emailAddress");
			if (!emailAddress.matches("^[a-zA-Z0-9@.]+$")) {
				attr.addFlashAttribute("error_msg", "Please enter valid characters for the email address.");
				return model;
			}
			try {
				cust_id = customerDAO.getCutomerIdFromEmail(emailAddress);
			} catch (Exception ex) {
				attr.addFlashAttribute("error_msg", "Customer Is Not a Valid Customer.");
				return model;
			}

		}

		if (cust_id == -1) {
			attr.addFlashAttribute("error_msg", "Customer Is Not a Valid Customer.");
			return model;

		}

		return createRequestForEmailAndMobileTransfer(model, request, attr, transferAmount, from_accP, cust_id);

	}

	private RedirectView createRequestForEmailAndMobileTransfer(RedirectView model, HttpServletRequest request,
			RedirectAttributes attr, String transferAmount, String from_accP, int cust_id) {
		List<Account> account = accountDAO.getAccountDetails(cust_id);
		if(account.size()==0) {
			attr.addFlashAttribute("error_msg", "No Valid Banking is Associated with the given Deatils.");
			return model;
		}
		
		int from_Acc_num = Integer.parseInt(from_accP);
		int To_Acc_num = account.get(0).getAcc_id();
		double transfer_Amount = Double.parseDouble(transferAmount);
		int isCritical;
		if (transfer_Amount > 1000) {
			isCritical = 1;
		} else {
			isCritical = 0;
		}
		
		Request customerRequest = createRequest((int) request.getSession().getAttribute("cust_id"), transfer_Amount,
				from_Acc_num, To_Acc_num, Constants.TRANSACTION_TYPE_TRANSFER);
		customerRequest.setIs_critical(isCritical);
		accountDAO.transferFunds_create_request(customerRequest);
		return model;
	}
	
	// transfer funds between two different peoples account
	/*
	 * Have to check the account number first to make sure its an account
	 */
	@RequestMapping(value = "/customer/transferFundsOtherAccount", method = RequestMethod.POST)
	public RedirectView customerTransferFundsToOtherCustomer(RedirectView model, HttpServletRequest request,
			RedirectAttributes attr) {

		String transferAmount = request.getParameter("accAmount");
		String to = request.getParameter("accNumber");
		String username= request.getParameter("accUsername");
		String from = request.getParameter("from_acc");
		String request_type = request.getParameter("request");

		model = new RedirectView("/customer/transferEmailPhone");
		if (!transferAmount.matches("^[0-9]+$")) {
			attr.addFlashAttribute("error_msg", "Please enter valid numbers for the amount");
			return model;
		}

		int fromAccountNumber = Integer.parseInt(from);

		double amount = Double.parseDouble(transferAmount);
	
		int isCritical;
		if (amount > 1000) {
			isCritical = 1;
		} else {
			isCritical = 0;
		}
		if (!accountDAO.checkIfAccIsActive(fromAccountNumber)) {
			log.info("Firscond");
			attr.addFlashAttribute("error_msg",
					"The account from where we are requesting for transfer is not yet Activated");
			return model;
		}

		if (Constants.TRANSACTION_TYPE_TRANSFER.equals(request_type)) {
			int toAccountNumber = Integer.parseInt(to);

			if (!accountDAO.checkIfAccIsActive(toAccountNumber)) {
				log.info("second");
				attr.addFlashAttribute("error_msg", "The account to which we sending amount is not yet Activated");
				return model;
			}
			Request customerRequest = createRequest((int) request.getSession().getAttribute("cust_id"), amount,
					fromAccountNumber, toAccountNumber, request_type);
			customerRequest.setIs_critical(isCritical);
			accountDAO.transferFunds_create_request(customerRequest);
			return model;
		}
		
		return createRequestForPaymentReqs(fromAccountNumber, username, amount, model, isCritical,attr);
}
	
	
	private RedirectView createRequestForPaymentReqs(int fromAccountNumber, String to, double amount,
			RedirectView model, int isCritical,RedirectAttributes attr) {

		Request request = new Request();
		Integer custId = customerDAO.getCustomerId(to);
		if(custId==null) {
			attr.addFlashAttribute("error_msg", "The Person from whom you requesting for money does not exists");
			return model;
		}
		request.setAmount(amount);
		request.setCust_id(custId);
		request.setSecond_acc_num(fromAccountNumber);
		request.setIs_critical(isCritical);
		request.setType(Constants.TANSACTION_TYPE_REQUEST);
		accountDAO.transferFunds_create_request(request);
		return model;

	}

	@RequestMapping(value = "/customer/accountManagement/{flag}", method = RequestMethod.POST)
	public RedirectView accountManagement(RedirectView model, HttpServletRequest request,
			@PathVariable("flag") String flag, RedirectAttributes attr) {

		model = new RedirectView("/customer/accountManagement");

		String msg = "";
		int cust_id = (Integer) request.getSession().getAttribute("cust_id");

		int check_flag = Integer.parseInt(flag);
		if (check_flag == 1) {
			Account account = new Account();
			account.setAcc_type(request.getParameter("type_account"));
			account.setCurr_bal(Double.parseDouble(request.getParameter("intialdeposit")));
			account.setCust_id(cust_id);
			accountDAO.createAccount(account);
			msg = "Account created.";
		}

		else if (check_flag == 0) {
			int account_no = Integer.parseInt(request.getParameter("account"));
			accountDAO.deleteAccount(account_no, cust_id, 0);
			msg = "Account deleted.";

		}

		attr.addFlashAttribute("msg", msg);
		return model;
	}
	
	@RequestMapping(value ="/customer/authorizeRequest", method = RequestMethod.POST)
	public RedirectView acceptRequest(RedirectView model, HttpServletRequest request, @ModelAttribute("auth") String accOrdec) {
		/*
		 * Need to be able to allow customer to accept/decline a transfer request from another customer
		 * and take it out of the request list and take money from account. Then return the list of requests
		 * and a message based on accept/decline.
		 */
		model = new RedirectView("/customer/payment");
		return model;
	}
	
	
	
	
	
	@RequestMapping(value ="/customer/downloadBankingStatements", method = RequestMethod.POST)
	public RedirectView downloadStatements(RedirectView model, HttpServletRequest request, @ModelAttribute("auth") String accOrdec) {
		/*
		 * Need to allow customer to download their banking statements.
		 * Can return a model back to the accountManagement page
		 * model = new ModelAndView("/customer/accountMangement");
		 */
		model = new RedirectView("/customer/accountManagement");
		return model;
	}
	
	
	@RequestMapping(value = "/customer/transferEmailPhone", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsEmailPhone(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		List<Account> accounts = accountDAO.getAccountDetails((int) request.getSession().getAttribute("cust_id"));
//		List<String> accounts = new ArrayList<String>();
//		accounts.add("blah");
		model.addObject("accounts", accounts);
	    model.setViewName("transferMakePayment");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/transferBA", method = RequestMethod.GET)
	public ModelAndView getCustomerDetailsBA(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		List<Account> accounts = accountDAO.getAccountDetails((int) request.getSession().getAttribute("cust_id"));
//		List<String> accounts = new ArrayList<String>();
//		accounts.add("boo");
		model.addObject("accountList", accounts);
		model.setViewName("transferBetweenAccounts");
		return model;
	}
	
	@RequestMapping(value = "/customer/CreditDebit", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsCreditDebit(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}

		List<Account> accounts = accountDAO.getAccountDetails((int) request.getSession().getAttribute("cust_id"));

		model.addObject("accounts", accounts);
	    model.setViewName("creditDebit");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/payment", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsPayment(ModelAndView model, HttpServletRequest request) {
		
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
		
		int cust_id = (int) request.getSession().getAttribute("cust_id");
		List<Request> listOfCustomerRequests = customerDAO.retrieveAllPaymentRequestForCust(cust_id);
		model.addObject("list",listOfCustomerRequests);
	    model.setViewName("makePayment");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/accountManagement", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsAccMan(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
		List<Account> accounts = accountDAO.getAccountDetails((int) request.getSession().getAttribute("cust_id"));
		model.addObject("accountList", accounts);
	    model.setViewName("accountManagement");	 	    	    
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
	

	private Request createRequest(int cust_id,double amount, int fromAccountNumber, int toAccountNumber, String typeOftransfer) {
		Request customerRequest = new Request();
		customerRequest.setCust_id(cust_id);
		customerRequest.setAmount(amount);
		customerRequest.setFirst_acc_num(fromAccountNumber);
		customerRequest.setSecond_acc_num(toAccountNumber);
		customerRequest.setType(typeOftransfer);
		return customerRequest;
		}
	
	private boolean checkEmptyFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip){
		if(fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() || cPassword.isEmpty() || address.isEmpty()
				|| email.isEmpty() || phoneNumber.isEmpty() || age.isEmpty() || city.isEmpty() || zip.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	private boolean checkMatchFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip){
		if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$") || !uName.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$") 
				|| !cPassword.matches("^[a-zA-Z0-9]+$") || !address.matches("^[a-zA-Z0-9# ]+$") || !email.matches("^[a-zA-Z0-9@.]+$") || !phoneNumber.matches("^[-0-9]+$")
				|| !age.matches("^[0-9]+$") || !city.matches("^[a-zA-Z]+$") || !zip.matches("^[0-9]+$")){
			return true;
		}
		
		return false;
	}
}
