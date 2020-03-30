package com.group12.models;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	
	private int cust_id;
	private int age;
	private String firstName;
	private String lastName;
	private String email;
	private Timestamp last_successful_transaction_time;
	private String mobile;
	private char type;
	private String state;
	private String city;
	private String address;
	private int failure_count;
	private String password;
	private String username;
	private String zipCode;
	private int is_active;
	private int currently_logged_in;
	


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLast_successful_transaction_time() {
		return last_successful_transaction_time;
	}

	public void setLast_successful_transaction_time(Timestamp last_successful_transaction_time) {
		this.last_successful_transaction_time = last_successful_transaction_time;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFailure_count() {
		return failure_count;
	}

	public void setFailure_count(int failure_count) {
		this.failure_count = failure_count;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public int getCurrently_logged_in() {
		return currently_logged_in;
	}

	public void setCurrently_logged_in(int currently_logged_in) {
		this.currently_logged_in = currently_logged_in;
	}
	
}
