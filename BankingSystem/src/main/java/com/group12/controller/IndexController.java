package com.group12.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
}
