package com.lti;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.dao.PolicyDetailDAO;
import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;
import com.lti.insurance.entity.RegisteredVehicleDetail;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
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
