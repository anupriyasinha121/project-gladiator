package com.lti.insurance.service.policy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.dao.PolicyDetailDAO;
import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.daoimpl.PolicyDetailDAOImpl;
import com.lti.insurance.dto.BuyPolicy;
import com.lti.insurance.dto.ClaimPolicy;
import com.lti.insurance.dto.RenewPolicy;
import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PolicyDetail;
import com.lti.insurance.entity.RegisteredVehicleDetail;
import com.lti.insurance.entity.Vehicle;
import com.lti.insurance.exception.InvalidCustomerPolicy;

@Service
public class PolicyServiceProvider implements PolicyService{
	
	@Autowired 
	CustomerDAO customerDAOImpl;
	
	@Autowired
	PolicyDetailDAO policyDetailDAOImpl;
	
	@Autowired
	RegisteredVehicleDetailDAO regVehicleDAOImpl;
	
	@Autowired
	VehicleDAO vehicleDAOImpl;
	
	@Autowired
	ClaimDAO claimDAOImpl;
	
	/*
	 * Date, try catch
	 */
	
	@Transactional
	public long buyPolicyForCustomer(BuyPolicy newPolicy, String customerId) {	
		
		PolicyDetail policyDetail = new PolicyDetail();
		
		RegisteredVehicleDetail regVehicle = new RegisteredVehicleDetail();
		
		regVehicle.setRegistrationNumber(newPolicy.getRegNumber());
		regVehicle.setEnginePower(newPolicy.getEnginePower());
		regVehicle.setEngineNumber(newPolicy.getEngineNumber());
		regVehicle.setChassisNumber(newPolicy.getChassisNumber());
		
		boolean r = regVehicleDAOImpl.addRegisteredVehicle(regVehicle);
		System.out.println("Added RegDeta : " + r);
		
		policyDetail.setPlan(newPolicy.getPlan());
		policyDetail.setPolicyStartDate(new Date());
		policyDetail.setPolicyEndDate(new Date());
//		call calculate
		policyDetail.setPremiumAmount(newPolicy.getAmount());
		policyDetail.setTransactionDate(new Date());
		
		boolean b = policyDetailDAOImpl.addPolicy(policyDetail);
		System.out.println("Added Policy : " + b);
		
		/*
		 * Set Foreign key for Vehicle Registration Detail
		 */
		
		Set<PolicyDetail> policySet = regVehicle.getPolicyId();
		if(policySet==null) {
			policySet = new HashSet<PolicyDetail>();
		}
		
		policySet.add(policyDetail);
		regVehicle.setPolicyId(policySet);
		
		policyDetail.setRegVehicleId(regVehicle);
		regVehicleDAOImpl.updateRegisteredVehicle(regVehicle);
		System.out.println("Updated RegDet");
		policyDetailDAOImpl.updatePolicy(policyDetail);
		System.out.println("Updated Policy");

		/*
		 * Set Foreign Key for Customer
		 */

		Customer c = customerDAOImpl.getCustomer(customerId);
		Set<PolicyDetail> custPolicies = c.getPolicies();
		if(custPolicies==null) {
			System.out.println("no policy");
			policySet = new HashSet();
		}
		
		custPolicies.add(policyDetail);
		c.setPolicies(custPolicies);
		
		policyDetail.setPolicyCustomerId(c);
		
		customerDAOImpl.updateCustomer(c);
		policyDetailDAOImpl.updatePolicy(policyDetail);
		
		/*
		 * Set Foreign Key for Vehicle
		 */
		
		Vehicle vehicle = vehicleDAOImpl.getvehicleByModel(newPolicy.getModel() ,newPolicy.getVehicleType());
		System.out.println("Fetched Vehicle" + vehicle);
		Set<PolicyDetail> policiesForVehicle = vehicle.getPolicyId();
		if(policiesForVehicle==null) {
			policiesForVehicle = new HashSet();
		}
		
		policiesForVehicle.add(policyDetail);
		vehicle.setPolicyId(policiesForVehicle);
		
		vehicleDAOImpl.updateVehicle(vehicle);
		
		policyDetail.setPolicyVehicleId(vehicle);
		policyDetailDAOImpl.updatePolicy(policyDetail);
		
		return policyDetail.getPolicyId();
	}

	
	
	
	
	
	
	
	@Transactional
	public long claimPolicy(ClaimPolicy newClaim, String customerId) throws InvalidCustomerPolicy {
		/*
		 * Add Claim
		 */
		
		PolicyDetail policy = policyDetailDAOImpl.getPolicyByPolicyId(newClaim.getPolicyNumber());
		
		if(policy==null) {
			System.out.println("Policy Set Null");
			System.out.println("Policy owner " + policy.getPolicyCustomerId().getUserName());
			
			throw new InvalidCustomerPolicy();
			
		}else if (!policy.getPolicyCustomerId().getUserId().equals(customerId)){
			
			throw new InvalidCustomerPolicy();
			
		}else {
			System.out.println("Policy Set Not Null");
		}
		
		Claim claim = new Claim();
		
		claim.setClaimDate(new Date());
		claim.setClaimReason(newClaim.getClaimReason());
		claim.setClaimStatus("Pending");
		
		claimDAOImpl.addClaim(claim);
		
		/*
		 * Set foreign Key for policy Id
		 */

		System.out.println("Setting policy Id fk");
		claim.setClaimPolicyId(policy);
		
		
		Set<Claim> claimSet = policy.getPolicyClaimId();
		if(claimSet==null) {
			claimSet = new HashSet<Claim>();
		}
		
		claimSet.add(claim);
		policy.setPolicyClaimId(claimSet);
		
		policyDetailDAOImpl.updatePolicy(policy);
		
		claim.setClaimPolicyId(policy);
		claimDAOImpl.updateClaim(claim);
		
		System.out.println("Set policy Id fk");
		
		/*
		 * Set Foreign Key for customer
		 */
		
		System.out.println("Setting customer Id fk");
		Customer customer = customerDAOImpl.getCustomer(customerId);
		Set<Claim> customerClaimList = customer.getClaimId();
		if(customerClaimList==null) {
			customerClaimList = new HashSet<Claim>();
		}
		
		customerClaimList.add(claim);
		customer.setClaimId(customerClaimList);
		customer.setContactNumber(newClaim.getMobileNumber());
		customerDAOImpl.updateCustomer(customer);
		
		
		claim.setClaimCustomerIdFk(customer);
		claimDAOImpl.updateClaim(claim);
		System.out.println("Set policy Id fk");
		
		return claim.getClaimId();
	}
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public long renewPolicy(RenewPolicy policy, String customerId) throws InvalidCustomerPolicy {
		
		/*
		 * Fetch policy Detail		
		 */
		long policyId = policy.getPolicyNumber();
		System.out.println(policyId);
		
		PolicyDetail prevPolicy = policyDetailDAOImpl.getPolicyByPolicyId(policyId);
		
		
		if(prevPolicy==null) {
			System.out.println("Entered Policy doesnot exist");
			return -1;				
		}else if(!prevPolicy.getPolicyCustomerId().getUserId().equals(customerId)) {
			throw new InvalidCustomerPolicy();
		}
		
		System.out.println("Fetched previous policy");
		
		Customer customer = prevPolicy.getPolicyCustomerId();
		System.out.println("Fetched User data");
		
		RegisteredVehicleDetail regVehDetail = prevPolicy.getRegVehicleId();
		System.out.println("Fetched Reg data");
		
		Vehicle vehicle = prevPolicy.getPolicyVehicleId();
		System.out.println("Fetched vehicle data");
		
		/*
		 * Add renewed policy			------	Left To put check if it it has been renewed or not
		 */
		PolicyDetail newPolicy = new PolicyDetail();

		newPolicy.setPlan(policy.getPlan());
		newPolicy.setPolicyCustomerId(prevPolicy.getPolicyCustomerId());
		
		Date newStartDate = prevPolicy.getPolicyEndDate();
		
		newPolicy.setPolicyStartDate(newStartDate);
		newPolicy.setPolicyEndDate(new Date());
		newPolicy.setPolicyVehicleId(vehicle);
		newPolicy.setPremiumAmount(6866);
		newPolicy.setTransactionDate(new Date());
		newPolicy.setRegVehicleId(regVehDetail);
		
		
		policyDetailDAOImpl.addPolicy(newPolicy);		// New Policy added
		System.out.println("New policy added");
		
		/*
		 * Adding Foreign Key for customer
		 */
		
		Set<PolicyDetail> customerPolicyList = customer.getPolicies();
		customerPolicyList.add(newPolicy);
		customer.setPolicies(customerPolicyList);
		
		customerDAOImpl.updateCustomer(customer);
		System.out.println("Updated customer");
		
		Set<PolicyDetail> regPolicyList = regVehDetail.getPolicyId();
		regPolicyList.add(newPolicy);
		regVehDetail.setPolicyId(regPolicyList);;
		
		regVehicleDAOImpl.updateRegisteredVehicle(regVehDetail);
		System.out.println("Updated Reg Veh");
		
		/*
		 * Add Foreign key for vehicle 
		 */
		
		Set<PolicyDetail> policyListForCustomer = vehicle.getPolicyId();
		policyListForCustomer.add(newPolicy);
		vehicle.setPolicyId(policyListForCustomer);
		
		vehicleDAOImpl.updateVehicle(vehicle);
		
		return newPolicy.getPolicyId();
	}
	
}