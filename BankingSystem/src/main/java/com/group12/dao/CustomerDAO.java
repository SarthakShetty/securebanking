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

import com.group12.controller.AccountController;
import com.group12.models.Customer;
import com.group12.models.Request;
import com.group12.utils.Constants;

@Component
public class CustomerDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRequestDAO customerReqDAO;

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

	
	public void updateCustomerData(String uName, String password,
			String address, String email, String phoneNumber, Integer age, String city, String zip, String state, Integer cust_id) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if(!password.isEmpty()) {
			String update_customer = " update customer set cust_user_id = '" +uName +"',cust_pwd =" +"SHA1('" + password + "')," + "address = '" 
					+ address +"',email = '"+ email+"',mobile = '" +phoneNumber+ "',age = '" + age + "',city = '" + city + "',zipcode = '" + zip +
					"',state = '" +state + "'where cust_id = '" + cust_id +"';";

			try {

				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(update_customer, Statement.RETURN_GENERATED_KEYS);
					// ps.setString(1, message);
					return ps;
				}, keyHolder);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			String update_customer_no_password = " update customer set cust_user_id = '" + uName + "',address = '" 
					+ address +"',email = '"+ email + "',mobile = '" + phoneNumber + "',age = '" + age + "',city = '" + city + "',zipcode = '" + zip +
					"',state = '" +state + "'where cust_id = '" + cust_id +"';";

			try {

				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(update_customer_no_password, Statement.RETURN_GENERATED_KEYS);
					// ps.setString(1, message);
					return ps;
				}, keyHolder);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		}
		
//		logger.info("The primary key" + keyHolder.getKey().intValue());
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Customer getCustomerProfileDetails(String userName) {

		List<Customer> customer = new ArrayList<Customer>();
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
					cust.setType(rs.getObject("type").toString().charAt(0));
					cust.setCust_id((int)rs.getObject("cust_id"));
					cust.setCurrently_logged_in((int)rs.getObject("currently_logged_in"));
					cust.setCurrently_logged_in((int)rs.getObject("is_active"));
					cust.setPassword((String)rs.getObject("cust_pwd"));
					return cust;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		return customer.size() > 0 ? customer.get(0) : null;
	}

	public Integer getCustomerId(String userName) {
		Customer customer = getCustomerProfileDetails(userName);
		
		return customer==null?null:customer.getCust_id();
	}

	@SuppressWarnings("unchecked")
	public int getCutomerIdFromEmail(String email) {

		List<Customer> customer = new ArrayList<>();
		String request_customer_information = "Select * from customer where email = '" + email + "';";

		try {
			customer = jdbcTemplate.query(request_customer_information, new RowMapper() {

				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer cust = new Customer();
					cust.setCust_id((int) rs.getObject("cust_id"));
					return cust;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		return customer.size() > 0 ? customer.get(0).getCust_id() : null;

	}

	@SuppressWarnings("unchecked")
	public int getCutomerIdFromMobile(String mobile) {

		List<Customer> customer = new ArrayList<>();
		String request_customer_information = "Select * from customer where mobile = '" + mobile + "';";

		try {
			customer = jdbcTemplate.query(request_customer_information, new RowMapper<Customer>() {

				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer cust = new Customer();
					cust.setCust_id((int) rs.getObject("cust_id"));
					return cust;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		return customer.size() > 0 ? customer.get(0).getCust_id() : null;

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

	public void activateCustomer(String user_Name) {
		
		logger.info(user_Name);
		String update_customer  = "update customer set is_active = 1 where cust_user_id = " + "'" +user_Name+"';";
		//log.info(update_customer);
		try {
			jdbcTemplate.update(update_customer);
		} catch(DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void customerLogout(int cust_id) {
		String update_customerTable = "update customer set currently_logged_in = 0 where cust_id= " + "'"
				+ cust_id + "';";
		try {
			jdbcTemplate.update(update_customerTable);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		
	}

}
