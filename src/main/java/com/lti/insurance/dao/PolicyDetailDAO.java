package com.lti.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;

public interface PolicyDetailDAO {
	
	boolean addPolicy(PolicyDetail policy);							// For buy policy
	
	PolicyDetail getPolicyByPolicyId(long policyId);				// For Renew : To get Registered vehicle detail and vehicle detail
	
	public List<PolicyDetail> getPolicyByCustomerId(String customerId);
	
	public boolean updatePolicy(PolicyDetail policy);
	
}



