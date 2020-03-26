package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {
		
		List<String> l = new ArrayList<String>();
		l.add("Beep Boop");
		l.add("got em");
		l.add("fork");
		l.add("Beep Boop");
		l.add("got em");
		l.add("fork");
		l.add("Beep Boop");
		l.add("got em");
		l.add("fork");
		
		List<String> b = new ArrayList<String>();
		b.add("56.70");
		b.add("40.30");
		
		List<String> reqListNames = new ArrayList<String>();
		reqListNames.add("brandon");
		reqListNames.add("stephen");
		
		model.put("user_id", name);
		model.put("password", password);
		model.put("employeeID", "1234");
		model.put("dob", "Today");
		model.put("list", l);
		model.put("accountList", l);
		model.put("accountBList", b);
		model.put("requestListAmount", b);
		model.put("requestListNames", reqListNames);
		/*
		 * Need to check for the type of user they are and verify
		 */
		return "customerAccount";
		//return "internalUserProfile";
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
	
	@RequestMapping(value="/confirmationAccoun", method = RequestMethod.POST)
	public String showConfirmationAccount(ModelMap model){
		return "confirmationAccount";
	}
	
	@RequestMapping(value="/forgotPassword", method= RequestMethod.GET)
	public String showForPass(ModelMap model){
		return "forgotPassword";
		
	}
	
	@RequestMapping(value="/forgotPasswordOTP", method= RequestMethod.POST)
	public String showForPassOTP(ModelMap model){
		return "forgotPasswordOTP";
		
	}
	
	@RequestMapping(value="/verifyOTP", method= RequestMethod.POST)
	public String verifyOTP(ModelMap model, @RequestParam String otp){
		if(!otp.equals("123")){
			model.put("errorMessage", "Entry does not match OTP given, please try again.");
			return "forgotPasswordOTP";
		}
		return "confirmfpOTP";
		
	}
}
