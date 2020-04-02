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
	    model.setViewName("adminSystemLogs");	 	    	    
		return model;
	}
	
	
	

	
	@RequestMapping(value = "/customer/schedule")
	public RedirectView scheduleAppointment(RedirectView model, HttpServletRequest request, RedirectAttributes attr) {
		if(request.getSession().getAttribute("role") == null){
			model = new RedirectView("redirect:/");
			return model;
		}
		
		String reason = request.getParameter("txtArea");
		if(reason.isEmpty() || !reason.matches("^[a-zA-Z0-9.?!]+$")){
			model = new RedirectView("/customer/helpSupport");
			attr.addFlashAttribute("error_msg", "Please enter a reason and use valid characters.");
			return model;
		}
		/*
		 * Need to allow the customer to schedule an appointment then return a message saying appointment
		 * entered.
		 */
		model = new RedirectView("/customer/helpSupport");
		attr.addFlashAttribute("msg", "Appointment Scheduled.");
		return model;
	}
	
	@RequestMapping(value = "/customer/logout")
	public RedirectView logout(RedirectView model, HttpServletRequest request) {
		/*
		 * logout
		 */
		request.getSession().invalidate();
		model = new RedirectView("/");
		return model;
	}
	
    /* update the request params and add DAO call to update it */
	@RequestMapping(value ="/customer/changeProfile", method = RequestMethod.POST)
	public RedirectView changeProfile(RedirectView model,HttpServletRequest request, 
			@RequestParam("password") String password, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("mobile") String phoneNumber, @RequestParam("age") String age,
			@RequestParam("city") String city, @RequestParam("zip") String zip, @RequestParam("state") String state, RedirectAttributes attr) {
		/*
		 * Need to allow customer to put in a request to change their profile information.
		 */
		
		boolean empty = checkEmptyFields("a", "a", "a", password, address, email, phoneNumber, age, city, zip);
		boolean noMatch = checkMatchFields("a", "a", "a", password, address, email, phoneNumber, age, city, zip);
		
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
	
	private boolean checkEmptyFields(String fName, String lName, String uName, String password,
			String address, String email, String phoneNumber, String age, String city, String zip){
		if(fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() ||  address.isEmpty()
				|| email.isEmpty() || phoneNumber.isEmpty() || age.isEmpty() || city.isEmpty() || zip.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	private boolean checkMatchFields(String fName, String lName, String uName, String password, 
			String address, String email, String phoneNumber, String age, String city, String zip){
		if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$") || !uName.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$") 
				 || !address.matches("^[a-zA-Z0-9# ]+$") || !email.matches("^[a-zA-Z0-9@.]+$") || !phoneNumber.matches("^[-0-9]+$")
				|| !age.matches("^[0-9]+$") || !city.matches("^[a-zA-Z]+$") || !zip.matches("^[0-9]+$")){
			return true;
		}
		
		return false;
	}
}
