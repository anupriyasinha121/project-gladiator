package com.lti.insurance.service.policy;

import com.lti.insurance.dto.BuyPolicy;
import com.lti.insurance.dto.ClaimPolicy;
import com.lti.insurance.dto.RenewPolicy;
import com.lti.insurance.exception.InvalidCustomerPolicy;

//@Component @Service @Repository @Contoller
public interface PolicyService {

	/*
	 * Buy Policy
	 */
	public long buyPolicyForCustomer(BuyPolicy policyDetail, String customer);
	
	
	/*
	 * Renew Policy
	 */
	public long claimPolicy(ClaimPolicy newClaim, String customerId) throws InvalidCustomerPolicy;

	/*
	 * Claim Policy
	 */
	public long renewPolicy(RenewPolicy policy, String customerId) throws InvalidCustomerPolicy;

	/*
	 * Calculate Premium Amount
	 */
	
}
