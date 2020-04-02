
package org.hyperledger.fabric.samples.fabcar;

import java.util.Objects;
import java.sql.Timestamp;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public class Request {
	@Property()
	private String req_id;
	@Property()
	private String cust_id;
	@Property()
	private String first_acc_num;
	@Property()
	private String second_acc_num;
	@Property()
	private String is_critical;
	@Property()
	private String approved_by;
	@Property()
	private String status;
	@Property()
	private String type;
	@Property()
	private String transaction_date;
	@Property()
	private String amount;
    
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getFirst_acc_num() {
		return first_acc_num;
	}
	public void setFirst_acc_num(String first_acc_num) {
		this.first_acc_num = first_acc_num;
	}
	public String getSecond_acc_num() {
		return second_acc_num;
	}
	public void setSecond_acc_num(String second_acc_num) {
		this.second_acc_num = second_acc_num;
	}
	public String getIs_critical() {
		return is_critical;
	}
	public void setIs_critical(String is_critical) {
		this.is_critical = is_critical;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReq_id() {
		return req_id;
	}
	public void setReq_id(String req_id) {
		this.req_id = req_id;
	}

	public Request(@JsonProperty("req_id") final String req_id, @JsonProperty("cust_id") final String cust_id,
            @JsonProperty("first_acc_num") final String first_acc_num, @JsonProperty("second_acc_num") final String second_acc_num,
			@JsonProperty("is_critical") final String is_critical, @JsonProperty("approved_by") final String approved_by,
			@JsonProperty("status") final String status, @JsonProperty("type") final String type,
			@JsonProperty("transaction_date") final String transaction_date, @JsonProperty("amount") final String amount) {
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
