package com.group12.models;


import java.sql.Timestamp;

public class CustomerLogin {

	private Timestamp timstamp;
	private int cust_id;
	private char succes;

	public Timestamp getTimstamp() {
		return timstamp;
	}

	public void setTimstamp(Timestamp timstamp) {
		this.timstamp = timstamp;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public char getSucces() {
		return succes;
	}

	public void setSucces(char succes) {
		this.succes = succes;
	}

}
