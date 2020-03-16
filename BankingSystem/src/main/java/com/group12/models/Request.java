package com.group12.models;

import java.sql.Timestamp;

public class Request {
	
	private int cust_id;
    private int first_acc_num;
    private int second_acc_num;
    private int is_critical;
    private String approved_by;
    private char status;
    private String type;
    private Timestamp transaction_date;
    private Double amount;
    
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getFirst_acc_num() {
		return first_acc_num;
	}
	public void setFirst_acc_num(int first_acc_num) {
		this.first_acc_num = first_acc_num;
	}
	public int getSecond_acc_num() {
		return second_acc_num;
	}
	public void setSecond_acc_num(int second_acc_num) {
		this.second_acc_num = second_acc_num;
	}
	public int getIs_critical() {
		return is_critical;
	}
	public void setIs_critical(int is_critical) {
		this.is_critical = is_critical;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Timestamp transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
