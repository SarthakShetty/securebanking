package com.group12.dao;

import org.springframework.stereotype.Component;


@Component
public class LoginDAO {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("test") && password.equals("test");
	}

}
