package com.group12.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.group12.models.Customer;

@Component
public class CustomerDAO {

	@Autowired
	private Customer customer;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void firstQuery() {

	    //jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
	    jdbcTemplate.execute("CREATE TABLE customers(" +
	        "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	}
		
	
	
	
}
