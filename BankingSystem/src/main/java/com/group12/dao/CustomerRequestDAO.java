package com.group12.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
		
		
		
		@SuppressWarnings("unchecked")
		public Request retrieveRequestFromId(int req_id) {
			List<Request> requests = new ArrayList<>();
			String paymentRequests = "Select * from Customer_Request where req_id = " + req_id  + ";";

			
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
			
		
		return requests.get(0);
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public void getBankingStatements(Timestamp timestamp, int cust_id, int acc_num) throws IOException {
		String statements = "Select acc_num_1, acc_num_2, status, type, amount, transaction_date from Customer_Request where (status = '" 
		        + Constants.TRANSACTION_TERMINATED  + "'or status='" + Constants.TRANSACTION_COMPLETED 
				+ "' )and (type='" + Constants.TRANSACTION_TYPE_CREDIT + "' or type = '" + Constants.TRANSACTION_TYPE_DEBIT
				+ "'or type = '" + Constants.TRANSACTION_TYPE_TRANSFER + "' or type = '" + Constants.TANSACTION_TYPE_REQUEST
				+ "') and transaction_date >= '"+ timestamp + "'and cust_id ='" +cust_id + "'and acc_num_1 ='" + acc_num +"';";
		
		String excelFilePath = "bankingStatements.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("BankingStatements");
        writeHeaderLine(sheet);
        
		try {
			jdbcTemplate.query(statements, new RowCallbackHandler()  {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					int rowCount = 1;
					while(rs.next()) {
						Integer acc_no1 = (Integer) rs.getObject("acc_num_1");
						Integer acc_no2 = (Integer) rs.getObject("acc_num_2");
						char status = rs.getObject("status").toString().charAt(0);
						String type = (String) rs.getObject("type");
						Double amount = (Double) rs.getObject("amount");
						Timestamp timestamp = rs.getTimestamp("transaction_date");
			            Row row = sheet.createRow(rowCount++);
			            int columnCount = 0;
			            Cell cell = row.createCell(columnCount++);
			            if(acc_no1 != null) {
			            	cell.setCellValue(acc_no1);
			            }
			            
			            cell = row.createCell(columnCount++);
			            if(acc_no2 != null) {
			            	cell.setCellValue(acc_no2);
			            }
			 
			            cell = row.createCell(columnCount++);
			            cell.setCellValue(status);
			 
			            cell = row.createCell(columnCount++);
			            cell.setCellValue(type);
			            
			            cell = row.createCell(columnCount++);
			            cell.setCellValue(amount);
			            
			            cell = row.createCell(columnCount);
			 
			            CellStyle cellStyle = workbook.createCellStyle();
			            CreationHelper creationHelper = workbook.getCreationHelper();
			            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
			            cell.setCellStyle(cellStyle);
			 
			            cell.setCellValue(timestamp);
			 
					}
				}
			});
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	        workbook.write(outputStream);
	        workbook.close();
	
		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}
			
	}
	
	private void writeHeaderLine(XSSFSheet sheet) {
		 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Account Number One");
        
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Account Number Two");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Transaction Status");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Transaction type");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Amount");
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Transaction Date");
    }
	
	
}
