package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.group12.models.Account;
import com.group12.models.Request;
import com.group12.utils.Constants;

@Component
public class AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createAccount(Account account) {
		String insert_sql = "Insert into Account(cust_id,acc_type,is_active,curr_bal) values(" + account.getCust_id()
				+ ",'" + account.getAcc_type() + "'," + 1 + "," + account.getCurr_bal() + ");";
		jdbcTemplate.update(insert_sql);
		Request request = new Request();
		request.setCust_id(account.getCust_id());
		request.setFirst_acc_num(account.getAcc_id());
		request.setStatus(Constants.TRANSACTION_PENDING);
		request.setType(Constants.TRANSACTION_TYPE_CREATE_ACCOUNT);
		insert_sql = "Insert into Customer_Request(cust_id,acc_num_1,is_critical,status,type) values("
				+ request.getCust_id() + "," + request.getFirst_acc_num() + "," + 0 + ",'" + request.getStatus() + "','"
				+ request.getType() + "'" + ");";
		jdbcTemplate.update(insert_sql);
	}

	
	public List<Account> getAccountDetails(int customer_id) {
		String get_accounts_for_a_customer = "select * from account where cust_id = " + customer_id + "and is_active = 1";
		List<Account> accounts = new ArrayList<Account>();
		accounts = jdbcTemplate.query(get_accounts_for_a_customer, new RowMapper(){
			 
		    public Account mapRow(ResultSet rs, int rowNum)
		            throws SQLException {
		    	Account account = new Account();                    
		        return account;
		    }});
		
		return accounts;

	}
	
	public void creditOrDebit(Request request) {
		String get_Ammount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getFirst_acc_num()+ ";";
		Double amount_left = jdbcTemplate.queryForObject(get_Ammount_present_In_Acc,Double.class);


		if (Constants.TRANSACTION_TYPE_CREDIT.equals(request.getType())) {
			
			

		} else if (Constants.TRANSACTION_TYPE_DEBIT.equals(request.getType())) {

		}

	}
	
	public void transferFunds_create_request(Request request) {
		
		
		if (Constants.TANSACTION_TYPE_REQUEST.equals(request.getType())) {
			request.setStatus(Constants.TRANSACTION_CUSTOMER_ACCEPTANCE);
		} else if (Constants.TRANSACTION_TYPE_TRANSFER.equals(request.getType())) {
			if (request.getIs_critical() == 1 ) {
				request.setStatus(Constants.TRANSACTION_PENDING);
			} else {
				transferFundsFromAcc(request);
				request.setStatus(Constants.TRANSACTION_COMPLETED);
			}
		}

		String insert_sql = "Insert into Customer_Request(cust_id,acc_num_1,acc_num_2,is_critical,status,type,Amount) values("
				+ request.getCust_id() + "," + request.getFirst_acc_num() + "," + request.getSecond_acc_num() + ","
				+ request.getIs_critical() + ",'" + request.getStatus() + "','" + request.getType() + "',"
				+ request.getAmount() + ");";
		jdbcTemplate.update(insert_sql);

	}

	private void transferFundsFromAcc(Request request) {

		String get_Ammount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getFirst_acc_num()
				+ ";";
		Double amount_left = jdbcTemplate.queryForObject(get_Ammount_present_In_Acc,Double.class);
		
		if (amount_left < request.getAmount()) {
			request.setStatus(Constants.TRANSACTION_TERMINATED);
		} else {
			amount_left -= request.getAmount();
			get_Ammount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getSecond_acc_num()
					+ ";";
			
			double sec_acc_num = jdbcTemplate.queryForObject(get_Ammount_present_In_Acc,Double.class);
			sec_acc_num += request.getAmount();
			String update_first_Acc = "UPDATE Account SET curr_bal =" + amount_left + " where acc_id ="
					+ request.getFirst_acc_num() + ";";
			String update_sec_Acc = "UPDATE Account SET curr_bal =" + sec_acc_num + " where acc_id ="
					+ request.getSecond_acc_num() + ";";
			jdbcTemplate.update(update_first_Acc);
			jdbcTemplate.update(update_sec_Acc);
			request.setStatus(Constants.TRANSACTION_COMPLETED);
		}
	}
}
