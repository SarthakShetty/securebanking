package com.group12.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

import com.group12.controller.LoginController;
import com.group12.models.Customer;
import com.group12.models.Request;
import com.group12.utils.Constants;

@Component
public class CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRequestDAO customerReqDAO;

	@Autowired
	private AccountDAO accountDAO;

	Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	public boolean checkIfMobileNumExists(String parameter) {

		int count = 0;
		try {
			count = jdbcTemplate.queryForObject("select count(*) from customer where mobile = " + parameter + ";",
					Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean checkIfEmailExists(String parameter) {
		int count = 0;

		try {
			count = jdbcTemplate.queryForObject(
					"select count(*) from customer where email = " + "'" + parameter + "'" + ";", Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean checkIfUserNameExists(String parameter) {
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(
					"select count(*) from customer where cust_user_id = " + "'" + parameter + "'" + ";", Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public void insertCutomerData(Customer customer) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insert_customer = "Insert into customer(cust_user_id,cust_pwd,first_name,last_name,age,address,mobile,email,state,city,zipcode,last_successful_transaction_time,failure_count,type) values("
				+ "'" + customer.getUsername() + "',SHA1('" + customer.getPassword() + "'),'" + customer.getFirstName()
				+ "','" + customer.getLastName() + "'," + customer.getAge() + ",'" + customer.getAddress() + "','"
				+ customer.getMobile() + "','" + customer.getEmail() + "','" + customer.getState() + "','"
				+ customer.getCity() + "','" + customer.getZipCode() + "','" + timestamp + "'," + 0 + ",'"
				+ customer.getType() + "'" + ")";

		try {

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(insert_customer, Statement.RETURN_GENERATED_KEYS);
				// ps.setString(1, message);
				return ps;
			}, keyHolder);

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		logger.info("The primary key" + keyHolder.getKey().intValue());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Customer getCustomerProfileDetails(String userName) {

		List<Customer> customer = (List<Customer>) new Customer();
		String request_customer_information = "Select * from customer where cust_user_id = '" + userName + "';";

		try {
			customer = jdbcTemplate.query(request_customer_information, new RowMapper() {

				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer cust = new Customer();
					cust.setAddress((String) rs.getObject("address"));
					cust.setAge((int) rs.getObject("age"));
					cust.setCity((String) rs.getObject("city"));
					cust.setEmail((String) rs.getObject("email"));
					cust.setFirstName((String) rs.getObject("first_name"));
					cust.setLastName((String) rs.getObject("last_name"));
					cust.setMobile((String) rs.getObject("mobile"));
					cust.setZipCode((String) rs.getObject("zipcode"));
					cust.setUsername((String) rs.getObject("cust_user_id"));
					cust.setState((String) rs.getObject("state"));
					cust.setType((char) rs.getObject("type"));
					return cust;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		return customer.get(0);
	}
	
	public int getCustomerId(String userName) {
		Customer customer = getCustomerProfileDetails(userName);
		return customer.getCust_id();
	}

	public void authorizeRequestCust(Request request, String customer_userId) {
		customerReqDAO.updateRequest(request, Constants.TRANSACTION_PENDING, customer_userId);
	}

	public void declineCustRequestCust(Request request, String customer_userId) {
		customerReqDAO.updateRequest(request, Constants.TRANSACTION_TERMINATED, customer_userId);
	}

	public List<Request> retrieveAllPaymentRequestForCust(int custId) {

		List<Request> allRequests = new ArrayList<>();
		allRequests = customerReqDAO.retrieveAllCustomerspaymentReqs(custId);
		return allRequests;
	}

	// Added register method for registration purpose
	public void register(Customer customer) {
		String insert = "insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(insert,
					new Object[] { customer.getUsername(), customer.getPassword(), customer.getFirstName(),
							customer.getLastName(), customer.getAge(), customer.getEmail(), customer.getMobile(),
							customer.getAddress(), customer.getState(), customer.getCity(), customer.getZipCode(),
							customer.getType() });
			System.out.println("In register class" + insert);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}

}
