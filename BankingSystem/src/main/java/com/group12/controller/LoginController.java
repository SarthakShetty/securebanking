package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.group12.dao.CustomerDAO;
import com.group12.dao.LoginDAO;
import com.group12.services.EmailService;

@Controller
public class LoginController {
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private EmailService emailService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showLogin(Model model){
		ModelAndView mod = new ModelAndView();
		
		if(model.asMap().get("error_msg") != null){
			mod.addObject("error_msg", model.asMap().get("error_msg"));
		}
		
		mod.setViewName("login");
		return mod;
	}
	
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String handleUserLogin(ModelMap model, @RequestParam String name,
//			@RequestParam String password) {
//		
//		List<String> l = new ArrayList<String>();
//		l.add("Beep Boop");
//		l.add("got em");
//		l.add("fork");
//		l.add("Beep Boop");
//		l.add("got em");
//		l.add("fork");
//		l.add("Beep Boop");
//		l.add("got em");
//		l.add("fork");
//		
//		List<String> b = new ArrayList<String>();
//		b.add("56.70");
//		b.add("40.30");
//		
//		List<String> reqListNames = new ArrayList<String>();
//		reqListNames.add("brandon");
//		reqListNames.add("stephen");
//		
//		model.put("user_id", name);
//		model.put("password", password);
//		model.put("employeeID", "1234");
//		model.put("dob", "Today");
//		model.put("list", l);
//		model.put("accountList", l);
//		model.put("accountBList", b);
//		model.put("requestListAmount", b);
//		model.put("requestListNames", reqListNames);
//		/*
//		 * Need to check for the type of user they are and verify
//		 */
//		return "customerAccount";
//		//return "internalUserProfile";
//	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public RedirectView getCustomerDetails(RedirectView model, HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("password") String password, RedirectAttributes redir) {
		/*
		 * Need to check credentials of the user and set the session variable of the role they have
		 * ie: customer, merchant, administrator 
		 * Other session variables: user_name, customer_id * 
		 * * = maybe
		 * If credentials are correct we link to the correct page using another API call
		 * via model = new ModelAndView("redirect:/blah blah"); 
		 * 
		 * if you need to redirect with attributes we have to use a redirectView.
		 * and use redirectView.attributes and stuff
		 * 
		 */
		if(name.isEmpty() || password.isEmpty()){
			//model = new ModelAndView("redirect:/", "error_msg", "Invalid characters entered, please enter valid characters.");
			model = new RedirectView("/", true);
			redir.addFlashAttribute("error_msg", "Please fill out all the fields.");
			return model;
		}
		if(!name.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$")){
			//model = new ModelAndView("redirect:/", "error_msg", "Invalid characters entered, please enter valid characters.");
			model = new RedirectView("/", true);
			redir.addFlashAttribute("error_msg", "Invalid characters entered, please enter valid characters.");
			return model;
		}
	
		//We only want to set these if the user is a valid one!
		request.getSession().setAttribute("user_name", name);
		request.getSession().setAttribute("role", "admin");
		
		//model = new ModelAndView("redirect:/customer/profile");
		model = new RedirectView("/customer/profile", true);
		return model;
	}
	


	@RequestMapping(value="/otp", method = RequestMethod.GET)
	public ModelAndView showAuthScreen(Model model){
		ModelAndView mod = new ModelAndView();
		if(model.asMap().get("error_msg") != null )
			mod.addObject("error_msg", model.asMap().get("error_msg"));
		mod.setViewName("OTPAuth");
		return mod;
	}
	
	@RequestMapping(value="/newAccount", method = RequestMethod.GET)
	public ModelAndView showNewAccount(Model model){
		ModelAndView mod = new ModelAndView();
		if(model.asMap().get("error_msg") != null )
			mod.addObject("error_msg", model.asMap().get("error_msg"));
		mod.setViewName("newAccount");
		return mod;
	}

	@RequestMapping(value="/conAcc", method = RequestMethod.POST)
	public String showConfirmationAccount(ModelMap model){
		return "confirmationAccount";
	}
	
	@RequestMapping(value="/forgotPassword", method= RequestMethod.GET)
	public String showForPass(ModelMap model){
		return "forgotPassword";
		
	}
	
	@RequestMapping(value="/forgotPasswordOTP", method= RequestMethod.POST)
	public String showForPassOTP(ModelMap model){
		/*
		 * Need to resend an OTP code and update the OTP code stored for the user based on the email.
		 */
		return "forgotPasswordOTP";
		
	}
	
	@RequestMapping(value="/verifyfpOTP", method= RequestMethod.POST)
	public String verifyfpOTP(ModelMap model){
		/*
		 * Need to verify OTP with the stored value for the user email provided. 
		 * 
		 * if OTP is correct -> go to confirmation
		 * else -> send error message
		 */
		
		return "forgotPasswordOTP";
		
	}
	
}
