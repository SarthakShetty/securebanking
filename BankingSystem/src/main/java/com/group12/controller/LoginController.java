package com.group12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.group12.dao.CustomerDAO;
import com.group12.dao.LoginDAO;
import com.group12.models.Customer;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private LoginDAO loginService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {
		if (!loginService.validateUser(name, password)) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}

		model.put("name", name);
		model.put("password", password);
		return "welcome";
	}
	
	@RequestMapping(value="/registerOTP", method = RequestMethod.GET)
	public String transferToRegistration(ModelMap model){
		return "signUpEmail";
	}
	
	@RequestMapping(value="/EmailSent", method = RequestMethod.POST)
	public String showEmailSentScreen(ModelMap model){
		return "OTPScreen";
	}
	//this is for testing purposes.
	@RequestMapping(value="/otp", method = RequestMethod.GET)
	public String showAuthScreen(ModelMap model){
		return "OTPAuth";
	}
	
	@RequestMapping(value="/newAccount", method = RequestMethod.POST)
	public String showNewAccount(ModelMap model){
		return "newAccount";
	}
	
	
}
