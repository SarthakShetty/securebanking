package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
		String userName = (String)request.getSession().getAttribute("user_id");
		Customer customer = customerDAO.getCustomerProfileDetails(userName);
		model.addObject("Firstname", customer.getFirstName());
		model.addObject("Lastname", customer.getLastName());
		model.addObject("phone", customer.getMobile());
		model.addObject("address", customer.getAddress());
		model.addObject("city", customer.getCity());
		model.addObject("state", customer.getState());
		model.addObject("zip", customer.getZipCode());
		model.addObject("age", customer.getAge());
		model.addObject("email", customer.getEmail());
		model.addObject("username", customer.getUsername());
		model.addObject("accountList",accountDAO.getAccountDetails(customer.getCust_id()));
		model.setViewName("profile");
		return model;
	}
	

	@RequestMapping(path="/activate/{user_Name}",method = {RequestMethod.GET, RequestMethod.POST})
    public String activateCustomer(@PathVariable("user_Name") String user_Name) {

		ModelAndView model = new ModelAndView();
		try {
		customerDAO.activateCustomer(user_Name);
		} catch(Exception ex) {
			throw ex;
		}
		return "redirect:/confirmationAccount";

    }
	
	
	@RequestMapping(value = "/customer/transferEmailPhone", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsEmailPhone(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
//		String userName = request.getParameter("user_name");
//		// Get the details of the customer using the userName
//	    Customer customer = customerDAO.getCustomerProfileDetails(userName);
//	    // Get the accounts from customer id 
//	    List<Account> accounts = accountDAO.getAccountDetails(customer.getCust_id());
//	    // set the session id with the customer id
//	    model.addObject("getAccount", accounts);
		List<String> s = new ArrayList<String>();
		s.add("hele");
		model.addObject("accounts", s);
	    model.setViewName("transferMakePayment");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/transferBA", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsBA(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
//		String userName = request.getParameter("user_name");
//		// Get the details of the customer using the userName
//	    Customer customer = customerDAO.getCustomerProfileDetails(userName);
//	    // Get the accounts from customer id 
//	    List<Account> accounts = accountDAO.getAccountDetails(customer.getCust_id());
//	    // set the session id with the customer id
//	    model.addObject("getAccount", accounts);
		List<String> s = new ArrayList<String>();
		s.add("hele");
		model.addObject("accounts", s);
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
//		String userName = request.getParameter("user_name");
//		// Get the details of the customer using the userName
//	    Customer customer = customerDAO.getCustomerProfileDetails(userName);
//	    // Get the accounts from customer id 
//	    List<Account> accounts = accountDAO.getAccountDetails(customer.getCust_id());
//	    // set the session id with the customer id
//	    model.addObject("getAccount", accounts);
		
		List<String> s = new ArrayList<String>();
		s.add("hele");
		model.addObject("accounts", s);
	    model.setViewName("creditDebit");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/payment", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsPayment(ModelAndView model, HttpServletRequest request) {
		
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return list of requests for a specific customer
		 */
		
	    model.setViewName("makePayment");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/accountManagement", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsAccMan(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
		/*
		 * Need to return list of accounts of the user
		 */
		
	    model.setViewName("accountManagement");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/customer/helpSupport", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsHelpSupp(ModelAndView model, HttpServletRequest request) {

		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
	    model.setViewName("helpsupport");	 	    	    
		return model;
	}
	
	@RequestMapping(value = "/admin/systemLogs", method=RequestMethod.GET)
	public ModelAndView getSystemLogs(ModelAndView model, HttpServletRequest request) {

		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
		/*
		 * return system log.
		 */
		model.addObject("logs", "enter list here");
	    model.setViewName("helpsupport");	 	    	    
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
	
	@RequestMapping(value = "/customer/schedule")
	public RedirectView scheduleAppointment(RedirectView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new RedirectView("redirect:/");
			return model;
		}
		/*
		 * Need to allow the customer to schedule an appointment then return a message saying appointment
		 * entered.
		 */
		
		return model;
	}
	
	@RequestMapping(value = "/customer/logout")
	public RedirectView logout(RedirectView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new RedirectView("/");
			return model;
		}
		/*
		 * logout
		 */
		request.getSession().invalidate();
		return model;
	}
	
    /* update the request params and add DAO call to update it */
	@RequestMapping(value ="/changeProfile", method = RequestMethod.POST)
	public RedirectView changeProfile(RedirectView model,HttpServletRequest request, 
			@RequestParam("password") String password, @RequestParam("cPassword") String cPassword, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("mobile") String phoneNumber, @RequestParam("age") String age,
			@RequestParam("city") String city, @RequestParam("zip") String zip, @RequestParam("state") String state, RedirectAttributes attr) {
		/*
		 * Need to allow customer to put in a request to change their profile information.
		 */
		
		boolean empty = checkEmptyFields("a", "a", "a", password, cPassword, address, email, phoneNumber, age, city, zip);
		boolean noMatch = checkMatchFields("a", "a", "a", password, cPassword, address, email, phoneNumber, age, city, zip);
		
		// Need to call a DAO method to update the profile information
		model = new RedirectView("/customer/profile");
		if(empty){
			
			attr.addFlashAttribute("error_msg", "Please fill out all the fields.");
			
			return model;
		}
		else if(noMatch){
			
			attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");
			return model;
		}
		
		return model;
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
