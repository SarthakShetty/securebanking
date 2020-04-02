package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.group12.dao.AccountDAO;
import com.group12.dao.CustomerRequestDAO;
import com.group12.dao.InternalUserDAO;
import com.group12.models.InternalUser;
import com.group12.models.Request;
import com.group12.utils.Constants;
import com.group12.models.Account;
import com.group12.models.Customer;

@Controller
public class InternalUserController {

	@Autowired
	private InternalUserDAO employeeDAO;

	@Autowired
	private CustomerRequestDAO customerReqs;

	Logger log = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/internalUser/profile", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetails(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		// TODO logging messages
		String userName = (String) request.getSession().getAttribute("user_id");
		InternalUser employee = employeeDAO.getEmployeeProfileDetails(userName);
		if (employee != null) {
//		// TODO need to do the integration with the UI parameters
			model.addObject("Firstname", employee.getFirst_name());
			model.addObject("Lastname", employee.getLast_name());
			model.addObject("phone", employee.getMobile());
			model.addObject("address", employee.getAddress());
			model.addObject("id", employee.getEmp_id());
			model.addObject("email", employee.getEmail());
			model.addObject("username", employee.getEmp_user_id());
			model.addObject("Age", employee.getAge());
			request.getSession().setAttribute("emp_type", employee.getType());
		}
		model.setViewName("internalUserProfile");
		return model;
	}

	@RequestMapping(value = "/internalUser/transactions", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsTransactions(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
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
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of customer accounts and their information
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
	}

	@RequestMapping(value = "/internalUser/accountManagement/admin", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsAccountsAdmin(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of employee accounts and their information
		 */

		model.setViewName("internalUserAccountManagementAdmin");
		return model;
	}

	@RequestMapping(value = "/internalUser/Requests", method = RequestMethod.GET)
	public ModelAndView getInternalUserDetailsRequests(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to return the list of all requests.
		 */

		List<Request> requestsOfCustomer = employeeDAO
				.retreiveAllCustoumerPendingReqs((int) request.getSession().getAttribute("emp_type"));
		if (requestsOfCustomer != null) {
			model.addObject("list", requestsOfCustomer);
		}
//		List<String> l = new ArrayList<String>();
//		l.add("bloop");
		model.setViewName("internalUserRequests");
		return model;
	}

	@RequestMapping(value = "/internalUser/modifyAccount", method = RequestMethod.POST)
	public RedirectView modifyInternalUserAccount(RedirectView model, HttpServletRequest request,
			@RequestParam("accNumber") String accNum, @RequestParam("firstNameModify") String fName,
			@RequestParam("lastNameModify") String lName, @RequestParam("usernameModify") String uName,
			@RequestParam("passwordModify") String password, @RequestParam("cPasswordModify") String cPassword,
			@RequestParam("addressModify") String address, @RequestParam("emailModify") String email,
			@RequestParam("mobileModify") String phoneNumber, @RequestParam("ageModify") String age,
			@RequestParam("cityModify") String city, @RequestParam("zipModify") String zip,
			@RequestParam("stateModify") String state, RedirectAttributes attr) {
		if (request.getSession().getAttribute("role") == null) {
			model = new RedirectView("/");
			return model;
		}

		boolean empty = checkEmptyFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age,
				city, zip);
		boolean noMatch = checkMatchFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age,
				city, zip);

		if (empty && request.getSession().getAttribute("role") != "admin") {
			model = new RedirectView("/internalUser/accountManagement", true);
			attr.addFlashAttribute("error_msg", "Please fill out all the fields.");

			return model;
		} else if (noMatch && request.getSession().getAttribute("role") != "admin") {
			model = new RedirectView("/internalUser/accountManagement", true);
			attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");

			return model;
		} else {
			if (empty) {
				model = new RedirectView("/internalUser/accountManagement/admin", true);
				attr.addFlashAttribute("error_msg", "Please fill out all the fields.");

				return model;
			} else if (noMatch) {
				model = new RedirectView("/internalUser/accountManagement/admin", true);
				attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");

				return model;
			}
		}

		/*
		 * Need to be able to modify employees account then return list of employee
		 * accounts and a message saying account modified
		 */
		if (empty && request.getSession().getAttribute("role") != "admin") {
			model = new RedirectView("/internalUser/accountManagement");
		} else {
			model = new RedirectView("/internalUser/accountManagement/admin");
		}

		attr.addFlashAttribute("msg", "Account modified.");
		return model;
	}

	@RequestMapping(value = "/internalUser/createEmployee", method = RequestMethod.POST)
	public RedirectView createInternalUserAccount(RedirectView model, HttpServletRequest request,
			@RequestParam("firstNameAdd") String fName, @RequestParam("lastNameAdd") String lName,
			@RequestParam("usernameAdd") String uName, @RequestParam("passwordAdd") String password,
			@RequestParam("cPasswordAdd") String cPassword, @RequestParam("addressAdd") String address,
			@RequestParam("emailAdd") String email, @RequestParam("mobileAdd") String phoneNumber,
			@RequestParam("ageAdd") String age, @RequestParam("cityAdd") String city,
			@RequestParam("zipAdd") String zip, @RequestParam("stateAdd") String state, RedirectAttributes attr) {
		if (request.getSession().getAttribute("role") == null) {
			model = new RedirectView("/");
			return model;
		}

		boolean empty = checkEmptyFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age,
				city, zip);
		boolean noMatch = checkMatchFields(fName, lName, uName, password, cPassword, address, email, phoneNumber, age,
				city, zip);

		if (empty) {
			model = new RedirectView("/internalUser/accountManagement", true);
			attr.addFlashAttribute("error_msg", "Please fill out all the fields.");

			return model;
		} else if (noMatch) {
			model = new RedirectView("/internalUser/accountManagement", true);
			attr.addFlashAttribute("error_msg", "Invalid characters entered, please use valid characters.");

			return model;
		}
		/*
		 * Need to be able to create an employees account
		 * 
		 */

		model = new RedirectView("/internalUser/accountManagement");
		attr.addFlashAttribute("msg", "Account created.");
		return model;
	}

	@RequestMapping(value = "/internalUser/deleteAccount", method = RequestMethod.POST)
	public ModelAndView deleteInternalUserAccount(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
			model = new ModelAndView("redirect:/");
			return model;
		}
		/*
		 * Need to be able to delete an employees account then return the list of
		 * employees and a message saying account deleted
		 */
		model.setViewName("internalUserAccountManagement");
		return model;
	}

	@RequestMapping(value = "/internalUser/authorizeEmployeeRequests", method = RequestMethod.POST)
	public RedirectView authorizeInternalUserRequests(RedirectView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
			model = new RedirectView("/");
			return model;
		}

		String req_status = request.getParameter("status");
		if (req_status != null) {
			String[] reqs = req_status.split(" ");
			if (reqs.length > 1 && reqs[0] != null && reqs[1] != null) {
				if ("accept".equals(reqs[0])) {
					Request customerRequest = customerReqs.retrieveRequestFromId(Integer.parseInt(reqs[1]));
					employeeDAO.authorizeCustomerTransactions(customerRequest,
							(String) request.getSession().getAttribute("user_id"));
				} else if ("decline".equals(reqs[0])) {
					Request customerRequest = new Request();
					customerRequest.setReq_id(Integer.parseInt(reqs[1]));
					employeeDAO.declineCustomerTransactions(customerRequest,
							(String) request.getSession().getAttribute("user_id"));
				}

			}
		}

		model = new RedirectView("/internalUser/Requests");
		return model;
	}

	@RequestMapping(value = "/internalUser/declineEmployeeRequests", method = RequestMethod.POST)
	public ModelAndView declineInternalUserRequests(ModelAndView model, HttpServletRequest request) {
		if (request.getSession().getAttribute("role") == null) {
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

	@RequestMapping(value = "/approvecustomertransactions")
	public ModelAndView approveCustomerTransactions(ModelAndView model, HttpServletRequest request) {
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
	public ModelAndView delCustomerAccount(ModelAndView model, HttpServletRequest request) {
		String userName = request.getParameter("user_id");
		InternalUser employee = employeeDAO.getEmployeeProfileDetails(userName);

		int cust_id = Integer.parseInt(request.getParameter("cust_id"));
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		Account account = new Account();
		account.setAcc_id(account_id);
		account.setCust_id(cust_id);
		employeeDAO.deleteCustomerAccount(account, employee.getType());
		return model;
	}

	@RequestMapping(value = "/deleteInternalUseraccount")
	public ModelAndView delInternalUserAccount(ModelAndView model, HttpServletRequest request) {
		InternalUser emp = new InternalUser();
		// emp.setEmp_id(emp_id);
		employeeDAO.deleteInternalUser(emp.getEmp_id(), emp.getType());
		return model;
	}

	@RequestMapping(value = "/employee/logout")
	public RedirectView logout(RedirectView model, HttpServletRequest request) {

		request.getSession().invalidate();
		model = new RedirectView("/");
		return model;
	}

	private boolean checkEmptyFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip) {
		if (fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() || cPassword.isEmpty()
				|| address.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || age.isEmpty() || city.isEmpty()
				|| zip.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean checkMatchFields(String fName, String lName, String uName, String password, String cPassword,
			String address, String email, String phoneNumber, String age, String city, String zip) {
		if (!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$") || !uName.matches("^[a-zA-Z0-9]+$")
				|| !password.matches("^[a-zA-Z0-9]+$") || !cPassword.matches("^[a-zA-Z0-9]+$")
				|| !address.matches("^[a-zA-Z0-9# ]+$") || !email.matches("^[a-zA-Z0-9@.]+$")
				|| !phoneNumber.matches("^[-0-9]+$") || !age.matches("^[0-9]+$") || !city.matches("^[a-zA-Z]+$")
				|| !zip.matches("^[0-9]+$")) {
			return true;
		}

		return false;
	}

}