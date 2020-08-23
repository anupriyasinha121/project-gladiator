package com.lti.dao;

import java.util.List;

import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;

public interface PolicyDetailDAO {
	
	boolean addPolicy(PolicyDetail policy);							// For buy policy
	
	PolicyDetail getPolicyByPolicyId(long policyId);				// For Renew : To get Registered vehicle detail and vehicle detail
	
	public List<PolicyDetail> getPolicyByCustomerId(String customerId);
	
	public boolean updatePolicy(PolicyDetail policy);
	
}



