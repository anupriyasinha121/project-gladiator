package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lti.service.user.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String userLogin( @RequestParam("name") String userId,   @RequestParam("pass") String pass) {

		
		
		return "hi";
	}
}


//public String getPassword(int customerId) {	
//	Query query=entityManager.createQuery("select password from Customer where userId=:id");
//	query.setParameter("id", customerId);
//	String passwd=(String)query.getSingleResult();
//	return passwd;
//}