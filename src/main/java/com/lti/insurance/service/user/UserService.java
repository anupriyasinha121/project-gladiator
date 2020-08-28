package com.lti.insurance.service.user;

import java.util.List;

import com.lti.insurance.dto.AdminClaim;
import com.lti.insurance.dto.ProfileClaim;
import com.lti.insurance.dto.ProfilePolicy;
import com.lti.insurance.dto.User;
import com.lti.insurance.dto.UserLoginDTO;
import com.lti.insurance.dto.UserRegisterDTO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.exception.InvalidLoginCredentials;
import com.lti.insurance.exception.UserAlreadyExist;
import com.lti.insurance.exception.UserNotFound;

public interface UserService {

	boolean changePassword(String customerId, String password);
	
//	boolean forgotPassword(String customerId);

	User loginUser(UserLoginDTO userLoginDTO) throws UserNotFound, InvalidLoginCredentials;
	
	public User addCustomer(UserRegisterDTO userRegisterDTO) throws UserAlreadyExist;
	
	public User loginAdmin(UserLoginDTO userLoginDTO) throws InvalidLoginCredentials;
	
	public List<ProfilePolicy> getProfilePolicyByCustomerId(String customerId);
	
	public List<ProfileClaim> getProfileClaimByCustomerId(String customerId);
	
	List<AdminClaim> claimsForAdmin();
	 
	boolean updateClaimByAdmin(AdminClaim claimDto);
	
	/*
	 * User Profile Information (Customer detail, its policy and claim history)
	 */
	
}
