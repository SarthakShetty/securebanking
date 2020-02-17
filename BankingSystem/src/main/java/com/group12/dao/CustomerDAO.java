package com.group12.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.group12.models.Customer;

@Component
public class CustomerDAO {

	@Autowired
	private Customer customer;
	
}
