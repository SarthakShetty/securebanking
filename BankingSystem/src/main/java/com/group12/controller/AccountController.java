package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.group12.dao.AccountDAO;
import com.group12.models.Account;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/openAccount", method = RequestMethod.POST)
	public ModelAndView createNewAccount(ModelAndView model, HttpServletRequest request) {
		log.info(request.getParameter("acc_type") + "request" + request.toString());
		Account account = new Account();
//		account.setAcc_type(request.getParameter("acc_type").charAt(0));
//		account.setCurr_bal(Double.parseDouble(request.getParameter("balance")));
//		account.setCust_id(Integer.parseInt(request.getParameter("cust_id")));
//		accountDAO.createAccount(account);
		account.setAcc_type('s');
		account.setCurr_bal(100);
		account.setCust_id(1);
		accountDAO.createAccount(account);
		return model;
	}
}
