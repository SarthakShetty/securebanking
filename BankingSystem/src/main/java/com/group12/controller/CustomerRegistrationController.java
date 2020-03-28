package com.group12.controller;


import javax.servlet.http.HttpServletRequest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.group12.dao.CustomerDAO;
import com.group12.models.Customer;


@Controller
public class CustomerRegistrationController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="/confirmationAccount", method = RequestMethod.POST)
	public RedirectView showConfirmationAccount(RedirectView model,HttpServletRequest request, @RequestParam("type_user") String type_user,
			@RequestParam("firstName") String fName, @RequestParam("lastName") String lName, @RequestParam("username") String uName,
			@RequestParam("password") String password, @RequestParam("cPassword") String cPassword, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("mobile") String phoneNumber, @RequestParam("age") String age,
			@RequestParam("city") String city, @RequestParam("zip") String zip, @RequestParam("state") String state, RedirectAttributes attr) {
			logger.info(request.getParameter("firstName"));
			/*
			 * Type_user refers to the type of user either customer or employee
			 * Also going to need to send the email in here for the OTP and store the OTP.
			 */
			
			boolean empty = checkEmptyFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age, city, zip, state, type_user);
			boolean noMatch = checkMatchFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age, city, zip, state, type_user);
			if(empty){
				model = new RedirectView("/newAccount", true);
				attr.addFlashAttribute("error_msg", "Please fill out all the fields.");
				
				return model;
			}
			else if(noMatch){
				model = new RedirectView("/newAccount", true);
				attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");
				
				return model;
			}
			
			if(!password.equals(cPassword)){
				model = new RedirectView("/newAccount", true);
				attr.addFlashAttribute("error_msg", "Passwords do not match, please enter passwords that match.");
				
				return model;
			}
			else if(password.length() < 8){
				model = new RedirectView("/newAccount", true);
				attr.addFlashAttribute("error_msg", "Password is too short. ");
				
				return model;
			}
			else if(Integer.parseInt(age) < 0){
				model = new RedirectView("/newAccount", true);
				attr.addFlashAttribute("error_msg", "Please enter a valid age.");
				
				return model;
			}
			
			/*
			 * Going to need to change these to how we used the model above.
			 */
//			if(customerDAO.checkIfMobileNumExists(request.getParameter("mobile"))) {
//				model.addObject("message", "Sorry phone number already used please enter another one.");
//				return model;
//			}
//			if(customerDAO.checkIfEmailExists(request.getParameter("email"))) {
//				model.addObject("message", "Sorry email already used please enter another one.");
//				return model;
//			}
//			if(customerDAO.checkIfUserNameExists(request.getParameter("username"))) {
//				model.addObject("message", "Sorry username already used please enter another one.");
//				return model;
//			}
			//Customer customer  = createCustomer(fName, lName, uName, password, cPassword, address, email, phoneNumber, age, city, zip, state, type_user);
//			customerDAO.insertCutomerData(customer); // should actually call customerDAO.register(customer)\
			
			//Doing this because the ability for employees to create an account from there dashboard.
			model = new RedirectView("/otp");
			attr.addFlashAttribute("customer_email", type_user);	
			return model;
			
			
	}
	
	private boolean checkEmptyFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip, String state, 
			String type_user){
		if(fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() || cPassword.isEmpty() || address.isEmpty()
				|| email.isEmpty() || phoneNumber.isEmpty() || age.isEmpty() || city.isEmpty() || zip.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	private boolean checkMatchFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip, String state, 
			String type_user){
		if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$") || !uName.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$") 
				|| !cPassword.matches("^[a-zA-Z0-9]+$") || !address.matches("^[a-zA-Z0-9# ]+$") || !email.matches("^[a-zA-Z0-9@.]+$") || !phoneNumber.matches("^[-0-9]+$")
				|| !age.matches("^[0-9]+$") || !city.matches("^[a-zA-Z]+$") || !zip.matches("^[0-9]+$")){
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(value="/verifyOTP", method = RequestMethod.POST)
	public RedirectView verifyOTP(RedirectView model,HttpServletRequest request, @RequestParam("OTP") String otp, @RequestParam("email") String email,
			RedirectAttributes attr) {
			/*
			 * Need to verify the OTP with the OTP assigned to specified customer
			 */
		
			if(otp.isEmpty()){
				model = new RedirectView("/otp", true);
				attr.addFlashAttribute("error_msg", "Please enter an OTP code.");
				attr.addFlashAttribute("customer_email", email);
				return model;
			}
			else if(!otp.matches("^[a-fA-F0-9]+$")){
				model = new RedirectView("/otp", true);
				attr.addFlashAttribute("error_msg", "Please enter an OTP with valid characters.");
				attr.addFlashAttribute("customer_email", email);
				return model;
			}
			
			model = new RedirectView("/conAcc");
			return model;
			
	}

	private Customer createCustomer(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip, String state, 
			String type_user) {
		Customer customer  = new Customer();
//		customer.setAddress(request.getParameter("address"));
//		// TODO UI needs to take the age of the customer currently hard coding
//		customer.setAge(21);
//		customer.setCity(request.getParameter("city"));
//		customer.setEmail(request.getParameter("email"));
//		customer.setFirstName(request.getParameter("firstName"));
//		customer.setMobile(request.getParameter("mobile"));
//		customer.setLastName(request.getParameter("lastName"));
//		customer.setPassword(request.getParameter("password"));
//		customer.setUsername(request.getParameter("username"));
//		customer.setZipCode(request.getParameter("zip"));
		customer.setAddress(address);
		// TODO UI needs to take the age of the customer currently hard coding
		customer.setAge(21);
		customer.setCity(city);
		customer.setEmail(email);
		customer.setFirstName(fName);
		customer.setMobile(phoneNumber);
		customer.setLastName(lName);
		customer.setPassword(password);
		customer.setUsername(uName);
		customer.setZipCode(zip);
		/*
		 * Need to do age as well. 
		 */
		// TODO Currently hardcoding it as individual
		customer.setType('I');
		customer.setState(state);
		
		return customer;
	}

	
	
}
