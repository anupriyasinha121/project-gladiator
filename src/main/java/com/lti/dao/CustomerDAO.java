package com.lti.dao;


import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;

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
