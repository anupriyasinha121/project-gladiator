package com.lti.insurance.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;

public interface CustomerDAO {

	public boolean addCustomer(Customer customer);							// Register	
	public boolean updateCustomer(Customer customer);
	
	String getPassword(String customerId);										// Login 
	boolean updateDrivingLicense(String customerId, String drivingLicense);		//update driving license when customer buy policy
	boolean updatePassword(String customerId, String password);					//reset or forgot
	Customer getCustomer(String customerId);									//Profile
	
//	List<Customer> getAllCustomers();				// Admin

//	void deleteCustomer(Customer customer);			// Admin
		
		
}
