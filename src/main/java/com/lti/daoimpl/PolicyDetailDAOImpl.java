package com.lti.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.CustomerDAO;
import com.lti.dao.PolicyDetailDAO;
import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;

@Repository
public class PolicyDetailDAOImpl implements PolicyDetailDAO {
	
	@PersistenceContext(unitName = "Spring-JPA")
	EntityManager entityManager;

	@Autowired
	CustomerDAO customerDAOImpl;
	
	@Transactional
	public boolean addPolicy(PolicyDetail policy) {
		
		try {
			entityManager.persist(policy);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean updatePolicy(PolicyDetail policy) {
		
		try {
			entityManager.merge(policy);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Transactional
	public PolicyDetail getPolicyByPolicyId(long policyId) {
		System.out.println("Fetching by policyId");
		PolicyDetail policyData = null;
		try {
			policyData = entityManager.find(PolicyDetail.class, policyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return policyData;
	}
	
	
	@Transactional
	public List<PolicyDetail> getPolicyByCustomerId(String customerId) {
		List<PolicyDetail> policyData;

		try {
			Query policyById = entityManager.createQuery("SELECT p from PolicyDetail p where p.policyCustomerId.userId=:customer");
			policyById.setParameter("customer", customerId);
			policyData = policyById.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return policyData;
	}
	
}