package com.lti.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.CustomerDAO;
import com.lti.entity.Customer;


@Service
public class UserServiceProvider implements UserService {

	@Autowired
	CustomerDAO customerDAO;
	
	
	@Autowired
	CustomerDAO cust;
	
	@Transactional
	public boolean changePassword(String customerId, String password) 
	{
		Customer c=new Customer();
		c=cust.getCustomer(customerId);
		if(c!=null)
		{
			return cust.updatePassword(customerId, password);
		}
		return false;
	}
	
	@Transactional
	public boolean forgotPassword(String customerId)
	{
		Customer c=new Customer();
		c=cust.getCustomer(customerId);
		if(c!=null)
		{return true;}
		return false;
	}
	
	
}
