package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.group12.models.Customer;

@Component
public class CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean checkIfMobileNumExists(String parameter) {
		int count =  jdbcTemplate.queryForObject("select count(*) from customer where mobile = "+parameter + ";", Integer.class);
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean checkIfEmailExists(String parameter) {
		int count =  jdbcTemplate.queryForObject("select count(*) from customer where email = "+ "'"+parameter + "'"+";", Integer.class);
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean checkIfUserNameExists(String parameter) {
		int count =  jdbcTemplate.queryForObject("select count(*) from customer where customer_User_id = "+"'"+parameter +"'" +";", Integer.class);
		if (count > 0) {
			return true;
		}
		return false;	}

	public void insertCutomerData(Customer customer) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String insert_customer = "Insert into customer(customer_User_id,cust_password,first_name,last_name,age,address,mobile,email,state,city,zip_code,last_successful_transaction_time,failure_count,type) values("
				+ "'" + customer.getUsername() + "',SHA1('" + customer.getPassword() + "'),'" + customer.getFirstName()
				+ "','" + customer.getLastName() + "'," + customer.getAge() + ",'" + customer.getAddress() + "','"
				+ customer.getMobile() + "','" + customer.getEmail() + "','" + customer.getState() + "','"
				+ customer.getCity() + "','" + customer.getZipCode() + "','" + timestamp + "'," + 0 + ",'"
				+ customer.getType() + "'" + ")";
		jdbcTemplate.update(insert_customer);

	}
	
	public Customer getCustomerProfileDetails(int customer_id) {
		
		List<Customer> customer = (List<Customer>) new Customer();
		String request_customer_information = "Select * from customer where cust_id = "+ customer_id +";";
		customer = jdbcTemplate.query(request_customer_information, new RowMapper(){
			 
		    public Customer mapRow(ResultSet rs, int rowNum)
		            throws SQLException {
		    	Customer cust = new Customer();                    
		        return cust;
		    }});

		return customer.get(0);
	}
	
	
}
