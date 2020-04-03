package com.group12.controller;

import java.util.Date;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.group12.dao.SupportDAO;
import com.group12.models.Account;
import com.group12.models.Customer;
import com.group12.models.Request;
import com.group12.models.Support;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerRequestDAO customerRequestDAO;
	
	@Autowired
	private SupportDAO supportDAO;
	
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

	@RequestMapping(value = "/customer/helpSupport", method=RequestMethod.GET)
	public ModelAndView getCustomerDetailsHelpSupp(ModelAndView model, HttpServletRequest request) {

		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		
	    model.setViewName("helpsupport");	 	    	    
		return model;
	}


	@RequestMapping(value = "/customer/schedule",method=RequestMethod.POST)
	public RedirectView scheduleAppointment(RedirectView model, HttpServletRequest request, RedirectAttributes attr) {
		if(request.getSession().getAttribute("role") == null){
			model = new RedirectView("redirect:/");
			return model;
		}
		
		String date = (String)request.getParameter("dateS");
		
		String reason = request.getParameter("txtArea");
		if(reason.isEmpty() || date == null || date.length() ==0){
			model = new RedirectView("/customer/helpSupport");
			attr.addFlashAttribute("error_msg", "Please enter a reason and use valid characters.");
			return model;
		}
		try {
		supportDAO.insertIssue((int)request.getSession().getAttribute("cust_id"),date,reason);
		}catch(Exception ex) {
			attr.addFlashAttribute("error_msg", "Error in ctreating the Appointment");
			return model;

		}
		model = new RedirectView("/customer/helpSupport");
		attr.addFlashAttribute("msg", "Appointment Scheduled.");
		return model;
	}
	
	@RequestMapping(value = "/customer/logout")
	public RedirectView logout(RedirectView model, HttpServletRequest request) {

		int cust_id = (int) request.getSession().getAttribute("cust_id");
		customerDAO.customerLogout(cust_id);
		request.getSession().invalidate();
		model = new RedirectView("/");
		return model;
	}

	
	@RequestMapping(value = "/customer/downloadBankingStatements", method=RequestMethod.GET)
	public RedirectView downloadBankingStatements(RedirectView model, HttpServletRequest request,RedirectAttributes attr) throws IOException {

	
		
		model = new RedirectView("/customer/accountManagement");
		try {
	
		int acc_num = Integer.parseInt(request.getParameter("account"));
		int cust_id = (Integer) request.getSession().getAttribute("cust_id");
		// assuming that the time is given in terms of month, we can add change for year 
		int time = Integer.parseInt(request.getParameter("time"));
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -time);
        Date date = calendar.getTime();
        Timestamp timestamp = new Timestamp(date.getTime());
        
        // call the customer request dao with specific account, transaction date, and type ( credit and debit, transfer and request)
        customerRequestDAO.getBankingStatements(timestamp, cust_id, acc_num);
        /*
		 * return system log.
		 */
		}catch(Exception ex) {
			attr.addFlashAttribute("error_msg", "Error in downloading the statement");
			return model;
		}
		attr.addFlashAttribute("msg", "Downloaded Statement!!");		 	    	    
		return model;
	}
	
	
	@RequestMapping(value ="/customer/changeProfile", method = {RequestMethod.GET, RequestMethod.POST})
	public RedirectView changeProfile(RedirectView model,HttpServletRequest request, RedirectAttributes attr) {
		/*
		 * Need to allow customer to put in a request to change their profile information.
		 */
		int cust_id = (Integer)request.getSession().getAttribute("cust_id");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("mobile");
		String userName = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String state = request.getParameter("state");
		boolean noMatch;
		//checking if they updated a value for the password, if not, should just use old password. 
		if(password.isEmpty()){
			noMatch = checkMatchFields("a", "a", userName, "a", address, email, phoneNumber, age, city, zip, userName, state);
			
			/*
			 * Do this with old password though
			 * customerDAO.updateCustomerData(userName, password, address, email, phoneNumber, age, city, zip, state, cust_id);
			 */
		}
		else
		{
			noMatch = checkMatchFields("a", "a", userName, password, address, email, phoneNumber, age, city, zip, userName, state);
			customerDAO.updateCustomerData(userName, password, address, email, phoneNumber, age, city, zip, state, cust_id);
		}
		
		// Need to call a DAO method to update the profile information
		
		model = new RedirectView("/customer/profile");
		if(noMatch){
			
			attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");
			return model;
		}
		
		return model;
	}
	
	private boolean checkEmptyFields(String fName, String lName, String uName, String password,
			String address, String email, String phoneNumber, Integer age, String city, String zip, String state){
		if(fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() ||  address.isEmpty()
				|| email.isEmpty() || phoneNumber.isEmpty() || age > 18 || city.isEmpty() || zip.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	private boolean checkMatchFields(String fName, String lName, String uName, String password, 
			String address, String email, String phoneNumber, Integer age, String city, String zip, String userName, String state){
		if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$") || !uName.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$") 
				 || !address.matches("^[a-zA-Z0-9# ]+$") || !email.matches("^[a-zA-Z0-9@.]+$") || !phoneNumber.matches("^[-0-9]+$")
				|| age > 18 || !city.matches("^[a-zA-Z]+$") || !zip.matches("^[0-9]+$")){
			return true;
		}
		
		return false;
	}
}
