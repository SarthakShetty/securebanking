package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.group12.dao.InternalUserDAO;
import com.group12.dao.LoginDAO;
import com.group12.models.Customer;
import com.group12.services.EmailService;
import com.group12.utils.Constants;

@Controller
public class LoginController {
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InternalUserDAO employeeDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showLogin(Model model){
		ModelAndView mod = new ModelAndView();
		
		if(model.asMap().get("error_msg") != null){
			mod.addObject("error_msg", model.asMap().get("error_msg"));
		}
		
		mod.setViewName("login");
		return mod;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView getCustomerDetails(RedirectView model, HttpServletRequest request, RedirectAttributes redir) {
		
		log.debug("I am in login");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (name.isEmpty() || password.isEmpty()) {
			// model = new ModelAndView("redirect:/", "error_msg", "Invalid characters
			// entered, please enter valid characters.");
			model = new RedirectView("/", true);
			redir.addFlashAttribute("error_msg", "Please fill out all the fields.");
			return model;
		}

		if (!name.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$")) {
			// model = new ModelAndView("redirect:/", "error_msg", "Invalid characters
			// entered, please enter valid characters.");
			model = new RedirectView("/", true);
			redir.addFlashAttribute("error_msg", "Invalid characters entered, please enter valid characters.");
			return model;
		}
		model = new RedirectView("/", true);
	

		//model = new RedirectView("/customer/profile", true);
		log.info(request.getParameter("type_user"));
		if (Constants.EMPLOYEE.equals(request.getParameter("type_user"))) {
			Integer empId = null;
			try {
				empId = loginDAO.checkIfTheEmployeeIsValid(name, password);
			} catch (RuntimeException ex) {
				redir.addFlashAttribute("error_msg", ex.getMessage());
				model = new RedirectView("/", true);
				return model;
			}
			if (empId != null) {
				request.getSession().setAttribute("emp_id", empId);
				model = new RedirectView("/internalUser/profile", true);
			}

		} else {
			Integer cust_id = null;
			try {
				cust_id = loginDAO.checkIfTheCustomerIsValid(name, password);
			} catch (RuntimeException ex) {
				redir.addFlashAttribute("error_msg", ex.getMessage());
				model = new RedirectView("/", true);
				return model;
			}
			if (cust_id != null) {
				request.getSession().setAttribute("cust_id", cust_id);
				model = new RedirectView("/customer/profile", true);
			}
		}

		// We only want to set these if the user is a valid one!
		request.getSession().setAttribute("user_id", name);

		// if the user is an employee we need to get role from DB. 
		request.getSession().setAttribute("role", "tier1");

		return model;
	}

	/*
	 * Only used for going to OTP page.
	 */
	@RequestMapping(value="/otp", method = RequestMethod.GET)
	public ModelAndView showAuthScreen(Model model,  HttpServletRequest request){
		ModelAndView mod = new ModelAndView();
		if(model.asMap().get("error_msg") != null )
			mod.addObject("error_msg", model.asMap().get("error_msg"));

		mod.setViewName("emailSentNotification");
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

	@RequestMapping(value="/confirmationAccount",method = RequestMethod.GET)
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
