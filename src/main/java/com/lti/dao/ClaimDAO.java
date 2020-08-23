package com.lti.dao;

import java.util.List;

import com.lti.entity.Claim;

public interface ClaimDAO {

	boolean addClaim(Claim claim);				// If someone claim
	
	public boolean updateClaim(Claim claim);
	
	public Claim getClaim(long claimId);
	
	List<Claim> getAllClaimsByCustomerId(String customerId);		// Required for claim history in user profile
				
}