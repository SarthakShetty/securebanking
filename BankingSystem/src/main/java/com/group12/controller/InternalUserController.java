package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.AccountDAO;
import com.group12.dao.InternalUserDAO;
import com.group12.models.InternalUser;
import com.group12.models.Request;
import com.group12.models.Account;
import com.group12.models.Customer;


public class InternalUserController {

	@Autowired
	private InternalUserDAO employeeDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/internalUser/profile")
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
   
   @RequestMapping(value = "/approvecustomertransactions")
   public ModelAndView approveCustomerTransactions(ModelAndView model, HttpServletRequest request)
   {
	   int req_id = Integer.parseInt(request.getParameter("req_id"));
	   int account_no = Integer.parseInt(request.getParameter("to_account"));
	   String approved_by = request.getParameter("approved_by");
	   Request customerRequest = new Request();
	   customerRequest.setApproved_by(approved_by);
	   customerRequest.setFirst_acc_num(account_no);
	   customerRequest.setReq_id(req_id);
	   employeeDAO.authorizeCustomerTransactions(customerRequest, null);
	   model.addObject("message", "Success");
	   return model;
   }
   
   @RequestMapping(value = "/deletecustomeraccount")
   public ModelAndView delCustomerAccount(ModelAndView model, HttpServletRequest request)
   {
	   String userName = request.getParameter("user_id");
	   InternalUser employee = employeeDAO.getEmployeeProfileDetails(userName);
	   
	   int cust_id = Integer.parseInt(request.getParameter("cust_id"));
	   int account_id = Integer.parseInt(request.getParameter("account_id"));
	   Account account = new Account();
	   account.setAcc_id(account_id);
	   account.setCust_id(cust_id);
	   employeeDAO.deleteCustomerAccount(account,employee.getType());
	   return model;
   }
   
   @RequestMapping(value = "/deleteInternalUseraccount")
   public ModelAndView delInternalUserAccount(ModelAndView model, HttpServletRequest request)
   {
	   InternalUser emp = new InternalUser();
	   //emp.setEmp_id(emp_id);
	   employeeDAO.deleteInternalUser(emp.getEmp_id(), emp.getType());
	   return model;
   }
}