package com.lti.insurance.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.insurance.exception.UserAlreadyExist;
import com.lti.insurance.exception.InvalidLoginCredentials;
import com.lti.insurance.exception.UserNotFound;
import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.daoimpl.PolicyDetailDAOImpl;
import com.lti.insurance.dto.AdminClaim;
import com.lti.insurance.dto.ProfileClaim;
import com.lti.insurance.dto.ProfilePolicy;
import com.lti.insurance.dto.User;
import com.lti.insurance.dto.UserLoginDTO;
import com.lti.insurance.dto.UserRegisterDTO;
import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;
import com.lti.insurance.entity.RegisteredVehicleDetail;
import com.lti.insurance.entity.Vehicle;


@Service
public class UserServiceProvider implements UserService {

	@Autowired
	CustomerDAO cust;
	
	@Autowired
	PolicyDetailDAOImpl polDetail;
	
	@Autowired
	ClaimDAO claim;

	@Transactional
	public boolean changePassword(String customerId, String password) 
	{
		try {
		Customer customer=new Customer();
		customer=cust.getCustomer(customerId);
		if(customer!=null)
		{
			return cust.updatePassword(customerId, password);
		}
		else
			throw new UserNotFound("User");
		}
		catch(UserNotFound invalidUser) 
		{
			return false;
		}
	}
	
	
//	@Transactional
//	public boolean forgotPassword(String customerId)
//	{
//		Customer c=new Customer();
//		c=cust.getCustomer(customerId);
//		if(c!=null)
//		{return true;}
//		return false;
//	}


	@Transactional
	public User loginUser(UserLoginDTO userLoginDTO) throws UserNotFound, InvalidLoginCredentials {
		
		String userId=userLoginDTO.getEmail();
		String password=userLoginDTO.getPassword();
		
		Customer customerDetail = cust.getCustomer(userId);
		String loginDetails = null;
		
		if(customerDetail==null) {
			throw new UserNotFound();
		}else {
			loginDetails = customerDetail.getPassword();
		}
		
		User user =null ;
		
		if(password.equals(loginDetails)){
			user = new User();
			user.setEmail(userId);
			user.setName(customerDetail.getUserName());
		}else {
			throw new InvalidLoginCredentials();			//If incorrect Password
		}

		return user;
	}
	
	@Transactional
	public User loginAdmin(UserLoginDTO userLoginDTO) throws InvalidLoginCredentials {
		
		String userId=userLoginDTO.getEmail();
		String password=userLoginDTO.getPassword();

		User user = null ;
		
		if(userId.equals("admin@insurance.com") && password.equals("admin")){
			user = new User();
			user.setEmail(userId);
			user.setName(userId);
		}else {
			throw new InvalidLoginCredentials();
		}

		return user;
	}
	
    @Transactional
	public User addCustomer(UserRegisterDTO userRegisterDTO) throws UserAlreadyExist {
    	
    	Customer obj= new Customer();
    	obj.setUserId(userRegisterDTO.getEmail());
    	obj.setUserName(userRegisterDTO.getName());
    	obj.setPassword(userRegisterDTO.getPassword());
    	obj.setAddress(userRegisterDTO.getAddress());
    	obj.setDob(userRegisterDTO.getDob());
    	obj.setContactNumber(userRegisterDTO.getContact());
    	
    	Customer userExist = cust.getCustomer(userRegisterDTO.getEmail());		//Check existing customer
    	User user = null;
    	
    	if(userExist!=null) {
    		throw new UserAlreadyExist();
    	}else {
    		cust.addCustomer(obj);
    		user = new User();
        	user.setEmail(obj.getUserId());
        	user.setName(obj.getUserName());
    	}
    	
		return  user;    	
	}
    
    
    @Transactional
	public List<ProfilePolicy> getProfilePolicyByCustomerId(String customerId){
		
    	
    	List<ProfilePolicy> profile=new ArrayList<ProfilePolicy>();
    	
    	if(polDetail.getPolicyByCustomerId(customerId).size()==0)
			return profile;
    	
		ArrayList<PolicyDetail> policy=(ArrayList<PolicyDetail>) polDetail.getPolicyByCustomerId(customerId);

		
		for(PolicyDetail pol:policy) 
		{
			ProfilePolicy prof= new ProfilePolicy();
			prof.setPolicyId(pol.getPolicyId());
			prof.setPlan(pol.getPlan());

			prof.setPolicyStartDate(pol.getPolicyStartDate());
			prof.setPolicyEndDate(pol.getPolicyEndDate());
			
			RegisteredVehicleDetail reg=pol.getRegVehicleId();
			prof.setRegistrationNumber(reg.getRegistrationNumber());
			
			
			Vehicle vehi=pol.getPolicyVehicleId();
			prof.setModel(vehi.getModel());
			
			profile.add(prof);
		}
		
		return profile;
	} 
	
    
    @Transactional
    public List<ProfileClaim> getProfileClaimByCustomerId(String customerId){
    	
    	ArrayList<Claim> claims=(ArrayList<Claim>) claim.getAllClaimsByCustomerId(customerId);
    	List<ProfileClaim> profclaim= new ArrayList<ProfileClaim>();
    	
    	for(Claim cl:claims)
    	{
    		ProfileClaim procl=new ProfileClaim();
    		procl.setClaimId(cl.getClaimId());
    		procl.setClaimStatus(cl.getClaimStatus());
    		procl.setClaimDate(cl.getClaimDate());
    		procl.setClaimReason(cl.getClaimReason());
    		procl.setClaimAmount(cl.getClaimAmount());
    		
    		Claim claimId=claim.getClaim(cl.getClaimId());
    		System.out.println(claimId.getClaimId());
    		System.out.println();
    		
    		PolicyDetail polDetail=claimId.getClaimPolicyId();
    		
    		procl.setPolicyId(polDetail.getPolicyId());
    		
    		profclaim.add(procl);
    	}
    	return profclaim;
    }
    
    
    
    @Transactional
    public List<AdminClaim> claimsForAdmin()
    {
    	List<AdminClaim> adminClaims= new ArrayList<AdminClaim>();
    	List<Claim>cl= claim.adminClaimsApproval();
    	for(Claim tempClaim:cl)
    	{
    		AdminClaim admin = new AdminClaim();
    		admin.setClaimId(tempClaim.getClaimId());
    		admin.setClaimAmount(tempClaim.getClaimAmount());
    		admin.setClaimDate(tempClaim.getClaimDate());
    		admin.setClaimStatus(tempClaim.getClaimStatus());
    		admin.setClaimReason(tempClaim.getClaimReason());
    		
    		adminClaims.add(admin);
    	}
    	
    	return adminClaims;
    }
    
    
    @Transactional
    public boolean updateClaimByAdmin(AdminClaim claimDto)
    {
    	long num=claimDto.getClaimId();
    	System.out.println(num);
    	Claim cla=claim.getClaim(claimDto.getClaimId());
    	cla.setClaimAmount(claimDto.getClaimAmount());
    	cla.setClaimStatus("Approved");
    	boolean isUpdated = claim.updateClaim(cla);
    	return isUpdated;
    }
  
}
