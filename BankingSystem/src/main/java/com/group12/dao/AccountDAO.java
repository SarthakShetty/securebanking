package com.group12.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.group12.controller.AccountController;
import com.group12.models.Account;
import com.group12.models.Request;
import com.group12.utils.Constants;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

@Component
public class AccountDAO {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRequestDAO customerRequestDAO;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	public boolean checkIfAccIsActive(int acc_num) {

		String checkIfActive = "select is_active from Account where acc_id = " + acc_num + ";";
		int value = -1;

		try {
			value = jdbcTemplate.queryForObject(checkIfActive, Integer.class);

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		return value == 1;
	}
	

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

	// Activates Account
	public void activateAccount(Request request, String approved_by) {

		String retriveAccountNum = "select acc_num_1 from Customer_Request where req_id =" + request.getReq_id() + ";";
		int account_Num = -1;

		try {
			account_Num = jdbcTemplate.queryForObject(retriveAccountNum, Integer.class);

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (account_Num != -1) {
			String activateAccount = "update account set is_active = 1 where acc_id = " + account_Num + ";";
			try {
				account_Num = jdbcTemplate.update(activateAccount);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		}

		customerRequestDAO.updateRequest(request, Constants.TRANSACTION_COMPLETED, approved_by);

	}

	private Request createRequestObjectCreateAccount(Account account, KeyHolder keyHolder) {
		Request request = new Request();
		request.setIs_critical(1);
		request.setCust_id(account.getCust_id());
		request.setFirst_acc_num(keyHolder.getKey().intValue());
		request.setStatus(Constants.TRANSACTION_PENDING);
		request.setType(Constants.TRANSACTION_TYPE_CREATE_ACCOUNT);
		return request;
	}

	public void createCreditOrDebitReq(Request request) {

		request.setStatus(Constants.TRANSACTION_PENDING);
		customerRequestDAO.insertIntoRequestForCreditOrDebit(request);
	}

	// Debit and credit operation for an account
	public void creditOrDebit(Request request) {
		String get_Amount_present_In_Acc = "select curr_bal from Account where acc_id  = " + request.getFirst_acc_num()
				+ ";";
		boolean canBeAdded = false;
		Double amount_left = 0.0;
		try {
			amount_left = jdbcTemplate.queryForObject(get_Amount_present_In_Acc, Double.class);
			log.info("amount_left=================" + amount_left);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		if (Constants.TRANSACTION_TYPE_CREDIT.equals(request.getType())) {
			amount_left += request.getAmount();
			canBeAdded = true;
			request.setStatus(Constants.TRANSACTION_COMPLETED);
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

	}

	// Creates a transfer and request amount request
	public void transferFunds_create_request(Request request) {

		if (Constants.TANSACTION_TYPE_REQUEST.equals(request.getType())) {
			request.setStatus(Constants.TRANSACTION_CUSTOMER_ACCEPTANCE);
			customerRequestDAO.insertIntoRequestTableForCustReq(request);
		} else if (Constants.TRANSACTION_TYPE_TRANSFER.equals(request.getType())) {
			request.setStatus(Constants.TRANSACTION_PENDING);
			customerRequestDAO.insertIntoRequestTableForTransfer(request);
		}
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

			// Load a file system based wallet for managing identities.
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);

			// load a CCP
			Path networkConfigPath = Paths.get("..", "..", "first-network", "connection-org1.yaml");

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			try (Gateway gateway = builder.connect()) {

				// get the network and contract
				Network network = gateway.getNetwork("mychannel");
				Contract contract = network.getContract("FabRequest");

				byte[] result;

				String key = "REQUEST"+request.getReq_id();

				contract.submitTransaction("createRequest", key, Integer.toString(request.getReq_id()), Integer.toString(request.getCust_id()),
											Integer.toString(request.getFirst_acc_num()), Integer.toString(request.getSecond_acc_num()) ,
											Integer.toString(request.getIs_critical()), request.getApproved_by(), Character.toString(request.getStatus()),
											request.getType(), request.getTransaction_date().toString(), Double.toString(request.getAmount()));

				result = contract.evaluateTransaction("queryRequest", key);
				log.info("hyperledger queryRequest result" + (new String(result));

			}
			
		}
	}

	// deletes is inactivating the account
	public void deleteAccount(int account_no, int customer_id, int is_active) {

		String construct_query = "update account set is_active = " + is_active + "where cust_id = " + customer_id
				+ "and acc_id = " + account_no + ";";

		try {
			jdbcTemplate.update(construct_query);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

	}
}
