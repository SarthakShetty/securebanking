package com.group12.models;

public class Account {

	private int cust_id;
	private int acc_id;
	private char acc_type;
	private int is_active;
	private double curr_bal;
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(int acc_id) {
		this.acc_id = acc_id;
	}
	public char getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(char acc_type) {
		this.acc_type = acc_type;
	}
	public double getCurr_bal() {
		return curr_bal;
	}
	public void setCurr_bal(double curr_bal) {
		this.curr_bal = curr_bal;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
	
	
	
}
