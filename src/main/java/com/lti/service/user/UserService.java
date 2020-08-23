package com.lti.service.user;

public interface UserService {

	/*
	 * Register
	 */
	
	
	
	
	/*
	 * Login
	 */
	
	
	/*
	 * Reset  
	 */
	
	boolean changePassword(String customerId, String password);
	
	/*
	 * Forgot Password
	 */
	boolean forgotPassword(String customerId);
	
	/*
	 * User Profile Information (Customer detail, its policy and claim history)
	 */
	
}
