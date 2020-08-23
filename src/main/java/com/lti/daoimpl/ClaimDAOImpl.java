package com.lti.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.ClaimDAO;
import com.lti.entity.Claim;


@Repository
public class ClaimDAOImpl implements ClaimDAO {

	@PersistenceContext(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	@Transactional
	public boolean addClaim(Claim claim) {
		entityManager.persist(claim);
		return true;
	}
	
	@Transactional
	public boolean updateClaim(Claim claim) {
		
		try {
			entityManager.merge(claim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}  
	
	@Transactional
	public Claim getClaim(long claimId) {
		
		Claim claim = null;
		
		try {
			claim = entityManager.find(Claim.class, claimId);
		} catch (Exception e) {
			e.printStackTrace();
			return claim;
		}
		return claim;
	}
	
	
	@Transactional
	public List<Claim> getAllClaimsByCustomerId(String customerId) {
	
		List<Claim> claimList = null;
		
		try {
			Query query = entityManager.createQuery("select ch from Claim as ch where ch.claimCustomerIdFk.userId=:arg");
			query.setParameter("arg", customerId);
			claimList =  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return claimList;
		}
		 return claimList;

	}

}
