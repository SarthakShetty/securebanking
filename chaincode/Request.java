
import java.sql.Timestamp;
package org.hyperledger.fabric.samples.securebank;

import java.util.Objects;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public class Request {
	@Property()
	private int req_id;
	@Property()
	private int cust_id;
	@Property()
	private int first_acc_num;
	@Property()
	private int second_acc_num;
	@Property()
	private int is_critical;
	@Property()
	private String approved_by;
	@Property()
	private char status;
	@Property()
	private String type;
	@Property()
	private Timestamp transaction_date;
	@Property()
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
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public Request(@JsonProperty("req_id") final int req_id, @JsonProperty("cust_id") final int cust_id,
            @JsonProperty("first_acc_num") final int first_acc_num, @JsonProperty("second_acc_num") final int second_acc_num,
			@JsonProperty("is_critical") final int is_critical, @JsonProperty("approved_by") final String approved_by,
			@JsonProperty("status") final char status, @JsonProperty("type") final String type,
			@JsonProperty("transaction_date") final Timestamp transaction_date, @JsonProperty("amount") final Double amount) {
        this.req_id = req_id;
        this.cust_id = cust_id;
		this.first_acc_num = first_acc_num;
		this.second_acc_num = second_acc_num;
		this.is_critical = is_critical;
		this.approved_by = approved_by;
		this.status = status;
		this.type = type;
		this.transaction_date = transaction_date;
		this.amount = amount;
    }

}
