package com.lti.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;

public interface ClaimDAO  {

	List<Claim> adminClaimsApproval();
	
	boolean addClaim(Claim claim);				// If someone claim
	
	public boolean updateClaim(Claim claim);
	
	public Claim getClaim(long claimId);
	
	List<Claim> getAllClaimsByCustomerId(String customerId);		// Required for claim history in user profile
				
}