package com.lti.testdao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.dao.ClaimDAO;
import com.lti.dao.CustomerDAO;
import com.lti.dao.PolicyDetailDAO;
import com.lti.dao.RegisteredVehicleDetailDAO;
import com.lti.dao.VehicleDAO;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;
import com.lti.entity.RegisteredVehicleDetail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:myspring.xml")
public class CustomerDAOImplTest {
	
	@Autowired
	CustomerDAO customerDAOImpl;
	
	@Autowired
	RegisteredVehicleDetailDAO regVeh;
	
	@Autowired
	PolicyDetailDAO policyDetailDAO;
	
	@Autowired
	ClaimDAO claimDAOImpl;
	
	@Autowired
	VehicleDAO vehicle;
	
	@Test
	public void test() {
		System.out.println("hi");
	}
	
	/*
	 * Add Customer
	 */
	
	@Test
	public void testaddCust() {
				
		System.out.println(customerDAOImpl);
		
		Customer c = new Customer();
		c.setUserId("anu121");
		c.setUserName("Anupriya");
		c.setPassword("password");
		c.setDob(new Date());
		long no = 9999999999L;
		c.setContactNumber(no);
		c.setAddress("Delhi");
		
		customerDAOImpl.addCustomer(c);
	}
	
	/*
	 * Update Customer
	 */
	
	@Test
	public void testaddUpdateCust() {
				
		System.out.println(customerDAOImpl);
		
		Customer c = customerDAOImpl.getCustomer("anu121");
		System.out.println("Customer Name : "+ c.getUserName());
		
		c.setContactNumber(6867454699L);
		
		System.out.println("Updating contact number");
		customerDAOImpl.updateCustomer(c);
	}
	
	/*
	 * Get Password by userId
	 */
	
	@Test
	public void testGetPass() {
		
		System.out.println("start");
		
//		String pass = customerDAOImpl.getPassword("au121");
		String pass = customerDAOImpl.getPassword("anu121");
		System.out.println("Password Is : " + pass);
		
		System.out.println("end");
	}
	
}
