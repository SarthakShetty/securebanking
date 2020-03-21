package com.group12.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;

public class CustomerDAOTest {
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Before
	public void setup() {
		jdbcTemplate = Mockito.mock(JdbcTemplate.class);
		customerDAO = Mockito.mock(CustomerDAO.class);
	}
	//TODO MUST EXECUTE DURING BUILD TIME AND JDBCTEMPLATE MOCK IS NOT INITIALIZING
	@Test
	public void checkIfUsersWithSameMobileAreNotgettingStoredTest() {
		
		Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Integer.class)).thenReturn(1);
		assertEquals(customerDAO.checkIfMobileNumExists(""),true);
	}

}
