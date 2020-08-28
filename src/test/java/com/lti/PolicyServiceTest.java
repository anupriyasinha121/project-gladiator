package com.lti;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.dao.PolicyDetailDAO;
import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.daoimpl.PolicyDetailDAOImpl;
import com.lti.insurance.dto.BuyPolicy;
import com.lti.insurance.dto.ClaimPolicy;
import com.lti.insurance.dto.RenewPolicy;
import com.lti.insurance.exception.InvalidCustomerPolicy;
import com.lti.insurance.service.policy.PolicyServiceProvider;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PolicyServiceTest {

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
	
	@Autowired
	PolicyServiceProvider policyService;
	
	@Test
	public void testBuyPolicyTest() {
		
		BuyPolicy bp = new BuyPolicy();
		
		bp.setChassisNumber("DGHK12B");
		bp.setDrivingLicense("GHPPPP7668VNBN");
		bp.setEngineNumber("VHVKJ67BNB");
		bp.setEnginePower(750);
		bp.setManufacturer("Tata");
		bp.setModel("Indigo");
		bp.setPlan("COMP");
		bp.setPlanDuration(2);
		bp.setPurchaseDate("23-1-1998");
		bp.setRegNumber("PS12BNMBHB");
		bp.setVehicleType(4);
		
		policyService.buyPolicyForCustomer(bp, "anu121");
		
	}
	
	@Test
	public void testClaimPolicy() {
		
		ClaimPolicy cp = new ClaimPolicy();
		cp.setClaimReason("Natural Disaster");
		cp.setMobileNumber(1111111111L);
		cp.setPolicyNumber(19);
		
		long l=0;
		try {
			l = policyService.claimPolicy(cp, "anu121");
		} catch (InvalidCustomerPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Claim Id" + l);
	}
	
	@Test
	public void testRenewPolicy() {
		
		RenewPolicy rp = new RenewPolicy();
		rp.setEmail("anupriyasinha121@gmail.com");
		rp.setMobileNumber(22922222222L);
		rp.setPlan("TPL");
		rp.setPlanDuration(2);
		rp.setPolicyNumber(8);
		
		long l=0;
		try {
			l = policyService.renewPolicy(rp, "anupriyasinha121@gmail.com");
		} catch (InvalidCustomerPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("New PolicyId Id " + l);
		
	}
}






// Coding Standard(Naming Convention, opps concept)
// Technologies
// 
// 
//Presentation, Analytical, coding convention, reasoning ability, Technical knowledge




