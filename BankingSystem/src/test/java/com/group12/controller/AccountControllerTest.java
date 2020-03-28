package com.group12.controller;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.group12.dao.AccountDAO;
import com.group12.dao.CustomerDAO;
import com.group12.dao.InternalUserDAO;

public class AccountControllerTest {
	
	@InjectMocks
    private AccountController accountController;
	
	private MockMvc mockMvc;
	
	@Mock
	private AccountDAO accountDAO;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private InternalUserDAO internalUserDAO;
	
	@Mock
    private HttpServletRequest httpRequest;
	
	
	
	@Before
	public void setup() {
		customerDAO = Mockito.mock(CustomerDAO.class);
		internalUserDAO = Mockito.mock(InternalUserDAO.class);
		 MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
	@Test
	public void createAccount() {
		ModelAndView modelAndView = Mockito.mock(ModelAndView.class);
		httpRequest.setAttribute("acc_type", 1);
		httpRequest.setAttribute("balance", 100);
		httpRequest.setAttribute("cust_id", 1);
//		mockMvc.perform(
//				accountController.createNewAccount(modelAndView, httpRequest)
//                        .contentType("Application")
//                        .content()
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("success", is(true)));
	}

}
