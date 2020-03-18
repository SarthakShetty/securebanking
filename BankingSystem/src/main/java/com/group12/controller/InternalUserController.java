package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.InternalUserDAO;
import com.group12.models.InternalUser;

public class InternalUserController {

	@Autowired
	private InternalUserDAO employeeDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/InternalUser/profile")
	public ModelAndView getInternalUserDetails(ModelAndView model, HttpServletRequest request) {
		// TODO logging messages 
		String userName = request.getParameter("user_id");
		InternalUser employee = employeeDAO.getEmployeeProfileDetails(userName);
		// TODO need to do the integration with the UI parameters
		model.addObject("First name", employee.getFirst_name());
		model.addObject("Last name", employee.getLast_name());
		model.addObject("phone", employee.getMobile());
		model.addObject("address", employee.getAddress());
		model.addObject("id", employee.getEmp_id());
		model.addObject("email", employee.getEmail());
		model.addObject("user name", employee.getEmp_user_id());
		return model;
   }
}
