package com.lti;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.dao.PolicyDetailDAO;
import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;
import com.lti.insurance.entity.RegisteredVehicleDetail;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ClaimTest {
	
	@Autowired
	CustomerDAO customerDAOImpl;
	
	@Autowired
	RegisteredVehicleDetailDAO regVeh;
	
	@Autowired
	PolicyDetailDAO policyDetailDAO;
	
	@Autowired
	ClaimDAO claimDAOImpl;
	
	@Autowired
	VehicleDAO vehtestbuyPolicy1icle;

	/*
	 * Add claim and set "CUSTOMER_ID_FK" and "POLICY_ID_FK" for claim
	 */
	
	@Test
	public void testAddClaim() {
		
		Claim claim = new Claim();
		claim.setClaimDate(new Date());
		claim.setClaimReason("Accident");
		
		claimDAOImpl.addClaim(claim);
		
		Customer cust = customerDAOImpl.getCustomer("anu121");
		Set<Claim>  claimSet = cust.getClaimId();
		if(claimSet==null) {
			claimSet = new HashSet<Claim>();
		}
		claimSet.add(claim);		
		
		cust.setClaimId(claimSet);
		claim.setClaimCustomerIdFk(cust);
		
		claimDAOImpl.updateClaim(claim);
		customerDAOImpl.updateCustomer(cust);
		
		PolicyDetail p = policyDetailDAO.getPolicyByPolicyId(1);
		
		Set<Claim>  c = p.getPolicyClaimId();
		if(c==null) {
			c = new HashSet<Claim>();
		}
		c.add(claim);		
		p.setPolicyClaimId(c);
		
		claim.setClaimPolicyId(p);
		
		policyDetailDAO.updatePolicy(p);
		claimDAOImpl.updateClaim(claim);
		
	}
	
	/*
	 * Update claim (Status Here)
	 */
	
	@Test
	public void testUpdateClaim() {
		Claim c = claimDAOImpl.getClaim(5);
		if(c!=null) {
			c.setClaimStatus("Approved");
			claimDAOImpl.updateClaim(c);
			System.out.println(c.getClaimStatus());
		}
	}
	
	/*
	 * Get claim by claimId;
	 */
	
	@Test
	public void testGetClaimById() {
		Claim c = claimDAOImpl.getClaim(5);
		if(c!=null) {
			System.out.println(c.getClaimReason());
		}
	}
	
	/*
	 * Get claim For All Customers
	 */
	
	@Test
	public void testGetClaimForCustomer() {
		List<Claim> claims = claimDAOImpl.getAllClaimsByCustomerId("anu121");
		for(Claim c:claims) {
			System.out.println(c.getClaimId());
			System.out.println(c.getClaimCustomerIdFk().getUserName());
		}
	}
	
	
}