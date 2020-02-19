//package com.group12.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.group12.dao.CustomerDAO;
//
//@Controller
//public class IndexController {
//	
//	@Autowired
//	private CustomerDAO customerDao;
//
//	@RequestMapping("/")
//	public String home(Map<String, Object> model) {
//		
//		model.put("message", "HowToDoInJava Reader !!");
//		
//		return "index";
//	}
//	
//	@RequestMapping("/next")
//	public String next(Map<String, Object> model) {
//		model.put("message", "You are in new page !!");
//		return "next";
//	}
//
//}