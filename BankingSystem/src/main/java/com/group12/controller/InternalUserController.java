package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.InternalUserDAO;
import com.group12.models.InternalUser;

@Controller
public class InternalUserController {

	@Autowired
	private InternalUserDAO employeeDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/internalUser/profile", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetails(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		// TODO logging messages 
//		String userName = request.getParameter("user_id");
//		InternalUser employee = employeeDAO.getEmployeeProfileDetails(userName);
//		// TODO need to do the integration with the UI parameters
//		model.addObject("First name", employee.getFirst_name());
//		model.addObject("Last name", employee.getLast_name());
//		model.addObject("phone", employee.getMobile());
//		model.addObject("address", employee.getAddress());
//		model.addObject("id", employee.getEmp_id());
//		model.addObject("email", employee.getEmail());
//		model.addObject("user name", employee.getEmp_user_id());
		model.addObject("firstName", "bob");
		model.addObject("lastName", "billy");
		model.setViewName("internalUserProfile");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/transactions", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsTransactions(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of transactions
		 */
		List<String> l = new ArrayList<String>();
		l.add("beep");
		l.add("boop");
		model.addObject("list", l);
		model.setViewName("internalUserViewTransactions");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/accountManagement", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsAccounts(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of employee accounts and their information
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/Requests", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsRequests(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of all requests.
		 */
		model.setViewName("internalUserRequests");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/modifyAccount", method = RequestMethod.POST)
	public ModelAndView modifyInternalUserAccount(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to modify employees account
		 * then return list of employee accounts and a message saying account modified
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/createdEmployee", method = RequestMethod.POST)
	public ModelAndView createInternalUserAccount(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to create an employees account
		 * then return the list of employees and a message saying account created
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/deletedEmployee", method = RequestMethod.POST)
	public ModelAndView deleteInternalUserAccount(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to delete an employees account
		 * then return the list of employees and a message saying account deleted
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/authorizeEmployeeRequests", method = RequestMethod.POST)
	public ModelAndView authorizeInternalUserRequests(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to authorize employee request and erase it from the requests
		 * then return the list of requests and a message saying request authorized
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
	@RequestMapping(value = "/internalUser/declineEmployeeRequests", method = RequestMethod.POST)
	public ModelAndView declineInternalUserRequests(ModelAndView model, HttpServletRequest request) {
		if(request.getSession().getAttribute("role") == null){
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to decline employee request and erase it from the requests
		 * then return the list of requests and a message saying request decline
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
   }
	
}
