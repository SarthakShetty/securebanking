package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.group12.models.Customer;


@Component
public class LoginDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("test") && password.equals("test");
	}

	@SuppressWarnings("unchecked")
	public boolean checkIfTheCustomerIsValid(String user_name, String password) {

		String request_customer_information = "Select is_active,currently_logged_in from customer where cust_user_id = '"
				+ user_name + "';";
		List<Customer> customer = new ArrayList<>();

		try {
			customer = jdbcTemplate.query(request_customer_information, new RowMapper() {

				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer cust = new Customer();
					cust.setCurrently_logged_in((int) rs.getObject("currently_logged_in"));
					cust.setIs_active((int) rs.getObject("is_active"));
					return cust;

				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		if (customer.size() == 0) {
			throw new RuntimeException("There is no customer with the user Name");
		}

		if (customer.get(0).getIs_active() == 0) {
			throw new RuntimeException("The Customer is not Activated");
		}

		if (customer.get(0).getCurrently_logged_in() == 1) {
			throw new RuntimeException("The Customer cannot login at multiple locations");
		}
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject("select count(*) from customer where cust_user_id = '" + user_name
					+ "' and " + " cust_pwd = " + "SHA1('" + password + "')" + ";", Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (count == 0) {
			throw new RuntimeException("Password is incorrect.");
		}
		
		//String update_customerTable = "update customer set currently_logged_in = 1 where cust_user_id= " + "'" + user_name + "';";
		//try {
			//jdbcTemplate.update(update_customerTable);
		//}catch(DataAccessException ex) {
			//throw new RuntimeException(ex);
		//}
		

		return true;
	}

}
