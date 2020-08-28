package com.lti.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.insurance.dto.AdminClaim;
import com.lti.insurance.dto.ForgotPassword;
import com.lti.insurance.dto.ProfileClaim;
import com.lti.insurance.dto.ProfilePolicy;
import com.lti.insurance.dto.User;
import com.lti.insurance.dto.UserLoginDTO;
import com.lti.insurance.dto.UserRegisterDTO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.Vehicle;
import com.lti.insurance.exception.InvalidLoginCredentials;
import com.lti.insurance.exception.UserAlreadyExist;
import com.lti.insurance.exception.UserNotFound;
import com.lti.insurance.service.user.UserService;
import com.lti.insurance.service.vehicle.VehicleServiceProvider;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	VehicleServiceProvider vehicleServiceProvider;
	
	@PostMapping ("/register")
	public User registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
		System.out.println(userRegisterDTO);
		User custObj=null;
		try {
			custObj = userService.addCustomer(userRegisterDTO);
		} catch (UserAlreadyExist e) {
//			e.printStackTrace();
			return custObj;
		}	
		return custObj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody UserLoginDTO userLoginDTO){
		System.out.println(userLoginDTO.getEmail());
		System.out.println(userLoginDTO.getPassword());
		
		User admin = null;
		
		try {
			admin = userService.loginUser(userLoginDTO);
		} catch (UserNotFound e) {
			return admin;
		} catch (InvalidLoginCredentials e) {
			return admin;
		}
		
		return admin;
	//	Customer customerId= customer.;
	//	System.out.println(customerId);
	//	System.out.println(password);
	//	userService.loginUser(customer);
	//	return password;

	}	
	
	@PostMapping("/admin-login")
	public User loginAdmin(@RequestBody UserLoginDTO userLoginDTO){
		System.out.println(userLoginDTO.getEmail());
		System.out.println(userLoginDTO.getPassword());
		
		User admin = null;
		try {
			admin = userService.loginAdmin(userLoginDTO);
		} catch (InvalidLoginCredentials e) {
			return admin;
		}
		
		return admin;
	}
	
	@GetMapping("/user-policy/{userId}")
	public List<ProfilePolicy> getPolicyDetails(@PathVariable String userId)
	{
		return userService.getProfilePolicyByCustomerId(userId);
	}
	
	@GetMapping("/user-claim/{userId}")
	public List<ProfileClaim> getClaimDetails(@PathVariable String userId)
	{
		return userService.getProfileClaimByCustomerId(userId);
	}
	
	@GetMapping("/admin")
	public List<AdminClaim> adminShowingClaims()
	{
		return userService.claimsForAdmin();
	}
	
	@PostMapping("/admin/update")
	public boolean adminUpdatingClaim(@RequestBody AdminClaim update)
	{
		return userService.updateClaimByAdmin(update);
	}
	
	@PostMapping("/forgotPassword")
	public boolean forgotPassword(@RequestBody ForgotPassword forgotPass)
	{
		return userService.changePassword(forgotPass.getEmail(),forgotPass.getPassword());
	}
}
