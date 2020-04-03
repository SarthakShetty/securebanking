package com.group12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.group12.controller.AccountController;
import com.group12.models.Account;
import com.group12.models.Support;
import com.group12.utils.Constants;

@Component
public class SupportDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@SuppressWarnings("unchecked")
	public List<Support> getIssues(int customer_id) {
		String statement = "select * from help_support where cust_id = '" + customer_id +"'and status ='" 
							+ Constants.TRANSACTION_PENDING +"';";
		List<Support> listOfsupports = new ArrayList<Support>();

		try {
			listOfsupports = jdbcTemplate.query(statement, new RowMapper() {

				public Support mapRow(ResultSet rs, int rowNum) throws SQLException {
					Support support = new Support();
					support.setRequest_subject((String)rs.getObject("request_subject"));
					support.setMessage((String)rs.getObject("message"));
					support.setCust_id((int) rs.getObject("cust_id"));
					return support;
				}
			});

		} catch (DataAccessException ex) {
			throw new RuntimeException(ex);
		}

		return listOfsupports;
	}

	public void insertIssue(int attribute, String date, String reason) {
		String insert ="insert into customer_help_and_services(cust_id,status,meetingDate,query) values(" + attribute + ",'P','" + date +"'," +"'" + reason +"');"; 
				
		try {
			jdbcTemplate.update(insert);
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
