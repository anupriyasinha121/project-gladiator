package com.lti.testdao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.ClaimDAO;
import com.lti.dao.CustomerDAO;
import com.lti.dao.PolicyDetailDAO;
import com.lti.dao.RegisteredVehicleDetailDAO;
import com.lti.dao.VehicleDAO;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;
import com.lti.entity.RegisteredVehicleDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:myspring.xml")
public class PolicyTest {
	
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
	
	@PersistenceContext(unitName = "Spring-JPA")
	EntityManager entityManager;

	/*
	 * Add "policy", "RegisteredVehicleDetail" and foreign key 
	 * "REG_VEHICLE_ID_FK" and "CUSTOMER_ID_FK"
	 */
	
	@Test
	public void testbuyPolicy1() {		
		System.out.println("hi");
		
		RegisteredVehicleDetail r = new RegisteredVehicleDetail();
		r.setRegistrationNumber("WGPJHJNB");
		r.setChassisNumber("FGFFGHBB");
		r.setEngineNumber("HJ14FVNBVMN");
		r.setPurchaseDate(new Date());
		r.setEnginePower(1000);
		
		regVeh.addRegisteredVehicle(r);
		
		PolicyDetail p = new PolicyDetail();
		p.setPlan("COMP");
		p.setPolicyStartDate(new Date());
		p.setPolicyEndDate(new Date());
		p.setPremiumAmount((double)2000.0);
		p.setTransactionDate(new Date());	
		
		policyDetailDAO.addPolicy(p);
		
		p.setRegVehicleId(r);
		
		Set<PolicyDetail> policySet = r.getPolicyId();
		
		if(policySet==null) {
			System.out.println("");
			policySet = new HashSet();
		}
		
		policySet.add(p);
		r.setPolicyId(policySet);
		
		regVeh.updateRegisteredVehicle(r);
		policyDetailDAO.updatePolicy(p);
		
		Customer c = customerDAOImpl.getCustomer("anu121");
		Set<PolicyDetail> policy2Set = c.getPolicies();
		if(policy2Set==null) {
			System.out.println("");
			policySet = new HashSet();
		}
		
		policy2Set.add(p);
		
		p.setPolicyCustomerId(c);
		
		customerDAOImpl.updateCustomer(c);
		policyDetailDAO.updatePolicy(p);

	}
	
	/*
	 * Get policy by policyId
	 */
	
	@Test
	public void testGetById() {
		PolicyDetail p = policyDetailDAO.getPolicyByPolicyId(1);
		System.out.println(p);
	}
	
	/*
	 * Fetch All policies of a customer
	 */
	
	@Test
	public void testPolicyForCust() {

		List<PolicyDetail> pd = policyDetailDAO.getPolicyByCustomerId("anu121");
		
		for(PolicyDetail p: pd) {
			System.out.println(p.getPlan());
		}	
	}
	
	
	
	
//	***************** 
	
	
	@Test
	public void testcust() {
//		System.out.println("start");
	
//		Customer cc = customerDAOImpl.getCustomer("anu121");
//		System.out.println("User Name : " + cc.getUserName());
//		System.out.println("Policies are " + cc.getPolicies());
//		
//		PolicyDetail p = new PolicyDetail();
//		p.setPlan("TPL");
//		p.setPolicyStartDate(new Date());
//		p.setPolicyEndDate(new Date());
//		p.setPremiumAmount((double)2000.0);
//		p.setRegistrationNumber("ANU&BH");
//		p.setTransactionDate(new Date());
//		
//		p.setPolicyCustomerId(cc);
//		
//		Set<PolicyDetail> pSet = new HashSet<PolicyDetail>();
//		pSet.add(p);
//	
//		cc.setPolicies(pSet);
//
//		policyDetailDAO.updatePolicy(cc);	
	}
	
}