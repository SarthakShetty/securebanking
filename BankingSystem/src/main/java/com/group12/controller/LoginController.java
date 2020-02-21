package com.group12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
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
	
	@RequestMapping(value="/confirmationAccount", method = RequestMethod.POST)
	public String showConfirmationAccount(ModelMap model){
		return "confirmationAccount";
	}
}
