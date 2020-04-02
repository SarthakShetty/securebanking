package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.group12.models.Request;
import com.group12.utils.Constants;

@Component
public class CustomerRequestDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertIntoCustomerReqForAccountCreation(Request request) {
		String insert_sql = "Insert into Customer_Request(cust_id,acc_num_1,is_critical,status,type) values("
				+ request.getCust_id() + "," + request.getFirst_acc_num() + "," + request.getIs_critical() + ",'" + request.getStatus() + "','"
				+ request.getType() + "'" + ");";

		try {
			jdbcTemplate.update(insert_sql);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void updateRequest(Request request, char status, String approved_by) {

		if (status == Constants.TRANSACTION_TERMINATED) {

			String updateCustReq = "update Customer_Request set status = " + "'" + status + "'" + " where req_id = "
					+ request.getReq_id() + ";";
			try {
				jdbcTemplate.update(updateCustReq);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);
			}
		} else if(Constants.TANSACTION_TYPE_REQUEST.equals(request.getType())){
			String updateCustReq = "update Customer_Request set status = " + "'" + status + "'," + "approved_by ="
					+ "'" + approved_by + "',acc_num_1 = " + request.getFirst_acc_num() + " where req_id = "
					+ request.getReq_id() + ";";
			try {
				jdbcTemplate.update(updateCustReq);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);

			}
		} else {
			String updateCustReq = "update Customer_Request set status = " + "'" + status + "'," + "approved_by= "
					+ "'" + approved_by + "' where req_id = "
					+ request.getReq_id() + ";";
			try {
				jdbcTemplate.update(updateCustReq);

			} catch (DataAccessException ex) {
				throw new RuntimeException(ex);

			}
		}

	}

	public void insertIntoRequestForCreditOrDebit(Request request) {
		String insert_sql = "Insert into Customer_Request(cust_id,acc_num_1,is_critical,status,type,Amount) values("
				+ request.getCust_id() + "," + request.getFirst_acc_num() + "," + request.getIs_critical() + ",'" + request.getStatus() + "','"
				+ request.getType() + "',"+request.getAmount() + ");";
		try {
			jdbcTemplate.update(insert_sql);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void insertIntoRequestTableForTransfer(Request request) {
		String insert_sql = "Insert into Customer_Request(cust_id,acc_num_1,acc_num_2,is_critical,status,type,Amount) values("
				+ request.getCust_id() + "," + request.getFirst_acc_num() + "," + request.getSecond_acc_num() + ","
				+ request.getIs_critical() + ",'" + request.getStatus() + "','" + request.getType() + "',"
				+ request.getAmount() + ");";
		try {
			jdbcTemplate.update(insert_sql);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void insertIntoRequestTableForCustReq(Request request) {
		String insert_sql = "Insert into Customer_Request(cust_id,acc_num_2,is_critical,status,type,Amount) values("
				+ request.getCust_id() + "," + request.getSecond_acc_num() + ","
				+ request.getIs_critical() + ",'" + request.getStatus() + "','" + request.getType() + "',"
				+ request.getAmount() + ");";
		try {
			jdbcTemplate.update(insert_sql);
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Request> retrieveAllCustomerspaymentReqs(int custId) {
		List<Request> requests = new ArrayList<>();
		String paymentRequests = "Select * from Customer_Request where cust_id = " + custId + " and status =" + "'"
				+ Constants.TRANSACTION_CUSTOMER_ACCEPTANCE + "'" + " and type =" + "'"
				+ Constants.TANSACTION_TYPE_REQUEST + "';";

		try {
			requests = jdbcTemplate.query(paymentRequests, new RowMapper() {

				public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

					Request req = new Request();
					req.setSecond_acc_num((int) rs.getObject("acc_num_2"));
					req.setAmount((Double) rs.getObject("amount"));
					req.setIs_critical((int) rs.getObject("is_critical"));
					req.setReq_id((int) rs.getObject("req_id"));
					return req;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		return requests;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Request> retrieveAllPendingRequests(int customer_id, int is_critical) {
		List<Request> requests = new ArrayList<>();
		String paymentRequests = "Select * from Customer_Request where cust_id = " + customer_id + "and status =" + "'"
				+ Constants.TRANSACTION_PENDING + "' and is_critical=" +is_critical+ ";";
		
		try {
			requests = jdbcTemplate.query(paymentRequests, new RowMapper() {

				public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

					Request req = new Request();
					if(rs.getObject("acc_num_2") != null) {
					req.setSecond_acc_num((int) rs.getObject("acc_num_2"));
					}
					if(rs.getObject("acc_num_1") != null) {
						req.setFirst_acc_num((int) rs.getObject("acc_num_1"));
					}if(rs.getObject("amount")!=null) {
						req.setAmount((Double) rs.getObject("amount"));
					}
					req.setIs_critical((int) rs.getObject("is_critical"));
					req.setReq_id((int) rs.getObject("req_id"));
					req.setType((String)rs.getObject("type"));
					return req;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
			
		return requests;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Request> retrieveAllPendingRequests(int is_critical) {
		List<Request> requests = new ArrayList<>();
		String paymentRequests = "Select * from Customer_Request where status =" + "'"
				+ Constants.TRANSACTION_PENDING + "' and is_critical=" +is_critical+ ";";

		
		try {
			requests = jdbcTemplate.query(paymentRequests, new RowMapper() {

				public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

					Request req = new Request();
					req.setCust_id((int)rs.getObject("cust_id"));
					if(rs.getObject("acc_num_2") != null) {
					req.setSecond_acc_num((int) rs.getObject("acc_num_2"));
					}
					if(rs.getObject("acc_num_1") != null) {
						req.setFirst_acc_num((int) rs.getObject("acc_num_1"));
					}if(rs.getObject("amount")!=null) {
						req.setAmount((Double) rs.getObject("amount"));
					}
					req.setIs_critical((int) rs.getObject("is_critical"));
					req.setReq_id((int) rs.getObject("req_id"));
					req.setType((String)rs.getObject("type"));
					return req;
				}
			});
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
		
		return requests;
	}

}
