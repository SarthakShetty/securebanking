package com.group12.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.group12.models.Account;
import com.group12.models.Request;
import com.group12.utils.Constants;

@Component
public class AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CustomerRequestDAO customerRequestDAO;

	// Retrieving The Account Details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Account> getAccountDetails(int customer_id) {
		String get_accounts_for_a_customer = "select * from account where cust_id = " + customer_id
				+ "and is_active = 1";
		List<Account> accounts = new ArrayList<Account>();

		try {
			accounts = jdbcTemplate.query(get_accounts_for_a_customer, new RowMapper() {

				public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
					Account account = new Account();
					account.setAcc_id((int) rs.getObject("acc_id"));
					account.setCurr_bal(rs.getDouble("curr_bal"));
					return account;
				}
			});

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		return accounts;
	}

	// Creates an Account for the customer
	public void createAccount(Account account) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			String insert_sql = "Insert into Account(cust_id,acc_type,is_active,curr_bal) values("
					+ account.getCust_id() + ",'" + account.getAcc_type() + "'," + 0 + "," + account.getCurr_bal()
					+ ");";
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);

				return ps;
			}, keyHolder);

		} catch (DataAccessException ex) {

			throw new RuntimeException(ex);
		}
		Request request = createRequestObjectCreateAccount(account, keyHolder);
		customerRequestDAO.insertIntoCustomerReqForAccountCreation(request);
	}

	// Debit and credit operation for an account
	public void creditOrDebit(Request request) {
		String get_Amount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getFirst_acc_num()
		+ ";";
		boolean canBeAdded = false;
		Double amount_left = 0.0;
		try {
			amount_left = jdbcTemplate.queryForObject(get_Amount_present_In_Acc, Double.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		
		if (Constants.TRANSACTION_TYPE_CREDIT.equals(request.getType())) {
			amount_left += request.getAmount();
			canBeAdded = true;
			request.setStatus('C');
		} else if (Constants.TRANSACTION_TYPE_DEBIT.equals(request.getType()) && amount_left >= request.getAmount()) {
			amount_left -= request.getAmount();
			canBeAdded = true;
			request.setStatus(Constants.TRANSACTION_COMPLETED);
		}
		if (canBeAdded) {
			String updateAcc = "UPDATE Account SET curr_bal =" + amount_left + " where acc_id ="
					+ request.getFirst_acc_num() + ";";
		
			try {
				jdbcTemplate.update(updateAcc);
			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			request.setStatus(Constants.TRANSACTION_TERMINATED);
		}
		customerRequestDAO.insertIntoRequestForCreditOrDebit(request);
		
	}

	// Creates a transfer and request amount request
	public void transferFunds_create_request(Request request) {

		if (Constants.TANSACTION_TYPE_REQUEST.equals(request.getType())) {
			request.setStatus(Constants.TRANSACTION_CUSTOMER_ACCEPTANCE);
		} else if (Constants.TRANSACTION_TYPE_TRANSFER.equals(request.getType())) {
			if (request.getIs_critical() == 1) {
				request.setStatus(Constants.TRANSACTION_PENDING);
			} else {
				transferFundsFromAcc(request);
				request.setStatus(Constants.TRANSACTION_COMPLETED);
			}
		}

		customerRequestDAO.insertIntoRequestTableForTransfer(request);

	}
	
	// All The critical Transactions
	public void autorizeTransferFundsByEmployee(Request request, String approved_by) {
		transferFundsFromAcc(request);
		customerRequestDAO.updateRequest(request.getReq_id(), request.getStatus(), approved_by);
	}

	// Does the Transfer Process Between The Accounts
	public void transferFundsFromAcc(Request request) {

		String get_Ammount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getFirst_acc_num()
		+ ";";
		Double amount_left = jdbcTemplate.queryForObject(get_Ammount_present_In_Acc, Double.class);
		
		if (amount_left < request.getAmount()) {
			request.setStatus(Constants.TRANSACTION_TERMINATED);
		} else {
			amount_left -= request.getAmount();
			get_Ammount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getSecond_acc_num()
					+ ";";
		
			double sec_acc_num = jdbcTemplate.queryForObject(get_Ammount_present_In_Acc, Double.class);
			sec_acc_num += request.getAmount();
			String update_first_Acc = "UPDATE Account SET curr_bal =" + amount_left + " where acc_id ="
					+ request.getFirst_acc_num() + ";";
			String update_sec_Acc = "UPDATE Account SET curr_bal =" + sec_acc_num + " where acc_id ="
					+ request.getSecond_acc_num() + ";";
			try {
		
				jdbcTemplate.update(update_first_Acc);
				jdbcTemplate.update(update_sec_Acc);
			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
			request.setStatus(Constants.TRANSACTION_COMPLETED);
		}
	}
	
	// deletes is inactivating the account
	public void deleteAccount(int account_no, int customer_id, int is_active) {
		
		String construct_query = "update account set is_active = " + is_active + 
				"where cust_id = " + customer_id + "and acc_id = " + account_no + ";";
		
		try {
			jdbcTemplate.update(construct_query);
		}
		catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	// Activates Account
	public void activateAccount(int requestId, String approved_by) {

		String retriveAccountNum = "select acc_num_1 from Customer_Request where req_id =+ " + requestId + ");";
		int account_Num = -1;

		try {
			account_Num = jdbcTemplate.queryForObject(retriveAccountNum, Integer.class);

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (account_Num != -1) {
			String activateAccount = "update account set is_active =1 where acc_id = " + account_Num + ");";
			try {
				account_Num = jdbcTemplate.update(activateAccount);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		}

		customerRequestDAO.updateRequest(requestId, Constants.TRANSACTION_COMPLETED, approved_by);

	}
	
	private Request createRequestObjectCreateAccount(Account account, KeyHolder keyHolder) {
		Request request = new Request();
		request.setCust_id(account.getCust_id());
		request.setFirst_acc_num(keyHolder.getKey().intValue());
		request.setStatus(Constants.TRANSACTION_PENDING);
		request.setType(Constants.TRANSACTION_TYPE_CREATE_ACCOUNT);
		return request;
	}
	
}