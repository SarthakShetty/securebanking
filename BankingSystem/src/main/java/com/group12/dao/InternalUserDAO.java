package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.group12.models.Account;
import com.group12.models.Request;
import com.group12.utils.Constants;
import com.group12.models.InternalUser;

@Component
public class InternalUserDAO {

	@Autowired
    private CustomerRequestDAO customerReqDAO;
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean checkIfMobileExists(String parameter) {

		int count = 0;
		try {
			count = jdbcTemplate.queryForObject("select count(*) from Employee where mobile = " + parameter + ";",
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
					"select count(*) from Employee where email = " + "'" + parameter + "'" + ";", Integer.class);
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
					"select count(*) from Employee where emp_user_id = " + "'" + parameter + "'" + ";", Integer.class);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public void insertEmployeeData(InternalUser employee) {
		String insert_employee = "Insert into employee(emp_id,emp_User_id,emp_password,first_name,last_name,age,address,mobile,email,type) values("+"'"+employee.getEmp_id()
				+ "'" + employee.getEmp_user_id() + "',SHA1('" + employee.getEmp_password() + "'),'"
				+ employee.getFirst_name() + "','" + employee.getLast_name() + "'," + employee.getAge() + ",'"
				+ employee.getAddress() + "','" + employee.getMobile() + "','" + employee.getEmail() + ",'"
				+ employee.getType() + "'" + ")";
		jdbcTemplate.update(insert_employee);

	}

	public List<Request> viewCustomerTransactions(int customer_id, int tier) {
		List<Request> requests = new ArrayList<>();
		if (tier == 1) {
			requests = customerReqDAO.retrieveAllPendingRequests(customer_id, 0);
		} else {
			requests = customerReqDAO.retrieveAllPendingRequests(customer_id, 1);
		}
		return requests;
	}

	public void authorizeCustomerTransactions(Request request, String user_name) {
		if (Constants.TRANSACTION_TYPE_CREATE_ACCOUNT.equals(request.getType())) {
			accountDAO.activateAccount(request, user_name);
		} else if (Constants.TRANSACTION_TYPE_CREDIT.equals(request.getType()) || Constants.TRANSACTION_TYPE_DEBIT.equals(request.getType())){
			accountDAO.creditOrDebit(request);
			customerReqDAO.updateRequest(request, request.getStatus(), user_name);
		} else {
			accountDAO.transferFundsFromAcc(request);
			customerReqDAO.updateRequest(request, request.getStatus(), user_name);
		}
	}

	public void deleteCustomerAccount(Account account,int tier) {
		if(tier == 2) {
			accountDAO.deleteAccount(account.getAcc_id(),account.getCust_id(),account.getIs_active());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InternalUser getEmployeeProfileDetails(String userName) {

		List<InternalUser> employee = (List<InternalUser>) new InternalUser();
		String request_employee_info = "Select * from employee where emp_user_id = " + userName + ";";

		try {
			employee = jdbcTemplate.query(request_employee_info, new RowMapper() {

				public InternalUser mapRow(ResultSet rs, int rowNum) throws SQLException {
					InternalUser emp = new InternalUser();
					emp.setAddress((String) rs.getObject("address"));
					emp.setAge((int) rs.getObject("age"));
					emp.setEmail((String) rs.getObject("email"));
					emp.setFirst_name((String) rs.getObject("first_name"));
					emp.setLast_name((String) rs.getObject("last_name"));
					emp.setMobile((String) rs.getObject("mobile"));
					emp.setEmp_user_id((String) rs.getObject("emp_user_id"));
					emp.setEmp_password((String) rs.getObject("emp_password"));
					emp.setType((char) rs.getObject("type"));
					emp.setEmp_id((int) rs.getObject("emp_id"));
					return emp;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		return employee.get(0);
	}

}
