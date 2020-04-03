package com.group12.models;

public class Support {
	private int cust_id;
	private char status;
	private String request_subject;
	private String message;
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getRequest_subject() {
		return request_subject;
	}
	public void setRequest_subject(String request_subject) {
		this.request_subject = request_subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
