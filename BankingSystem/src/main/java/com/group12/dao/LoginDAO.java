package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.group12.controller.AccountController;
import com.group12.models.Customer;


@Component
public class LoginDAO {
  
	
	Logger log = LoggerFactory.getLogger(LoginDAO.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public Integer checkIfTheCustomerIsValid(String user_name, String password) {
	
		String request_customer_information = "Select is_active,currently_logged_in,cust_id from customer where cust_user_id = '"
				+ user_name + "';";
		List<Customer> customer = new ArrayList<>();

		try {
			customer = jdbcTemplate.query(request_customer_information, new RowMapper() {

				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer cust = new Customer();
					cust.setCust_id((int)rs.getObject("cust_id"));
					cust.setCurrently_logged_in((int) rs.getObject("currently_logged_in"));
					cust.setIs_active((int) rs.getObject("is_active"));
					return cust;

				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException("Error Retriving the customer Details with user name " + user_name);
		}

		if (customer.size() == 0) {
			throw new RuntimeException("There is no customer with the user Name " + user_name);
		}
		int cust_id = customer.get(0).getCust_id();

		if (customer.get(0).getIs_active() == 0) {
			insertIntOCustomerLoginActivity(cust_id,'N');
			throw new RuntimeException("The Customer is not Activated");
		}

		if (customer.get(0).getCurrently_logged_in() == 1) {
			insertIntOCustomerLoginActivity(cust_id,'N');
			throw new RuntimeException("The Customer cannot login at multiple locations");
		}
		
		try {
			cust_id = jdbcTemplate.queryForObject("select cust_id from customer where cust_user_id = '" + user_name
					+ "' and " + " cust_pwd = " + "SHA1('" + password + "')" + ";", Integer.class);
		} catch (DataAccessException ex) {
			insertIntOCustomerLoginActivity(cust_id,'N');
			throw new RuntimeException("Password is incorrect.");
		}
	
		String update_customerTable = "update customer set currently_logged_in = 1 where cust_user_id= " + "'"
				+ user_name + "';";
		try {
			jdbcTemplate.update(update_customerTable);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		insertIntOCustomerLoginActivity(cust_id,'Y');
		return cust_id == -1 ? null : cust_id;
	}

	public Integer checkIfTheEmployeeIsValid(String user_name, String password) {
		
		int employeeId = -1;
		try {
			employeeId = jdbcTemplate.queryForObject("select emp_id from employee where emp_user_id = '" + user_name
					+ "' and " + " emp_password = " + "SHA1('" + password + "')" + ";", Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException("Password is incorrect.");
		}
		return employeeId == -1 ? null : employeeId;
	}
	
	
	public void insertIntOCustomerLoginActivity(int cust_id,char success) {
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String insertIntoCustomer = "Insert into customer_login_activity(cust_id,is_successful,ts) values(" + cust_id + ",'" + success + "','" + date + "');";
		log.info(insertIntoCustomer);
		try {
			 jdbcTemplate.update(insertIntoCustomer);
		} catch (DataAccessException ex) {
			throw new RuntimeException("Error in Acccsing logging table.");
		}
	}
	
	
	

}
