package com.lti.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.insurance.dto.BuyPolicy;
import com.lti.insurance.dto.ClaimPolicy;
import com.lti.insurance.dto.CompTpl;
import com.lti.insurance.dto.RenewPolicy;
import com.lti.insurance.dto.ResultPremium;
import com.lti.insurance.exception.InvalidCustomerPolicy;
import com.lti.insurance.service.policy.Insurance;
import com.lti.insurance.service.policy.PolicyService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PolicyController {

	@Autowired
	PolicyService policyService;
	
	@Autowired
	@Qualifier("tpl")
	Insurance ins;
	
	@Autowired
	@Qualifier("compService")
	Insurance ins2;
	
	@PostMapping("/buy-policy/{userId}")
	public long buyPolicyController(@RequestBody BuyPolicy policyDetail, @PathVariable String userId) {
		
		long policyId = policyService.buyPolicyForCustomer(policyDetail, userId);
		System.out.println(userId);
		
		return policyId;
	}
	
	@PostMapping("/claim-policy/{userId}")
	public long claimPolicyController(@RequestBody ClaimPolicy claimDetail, @PathVariable String userId) {
		
		long claimId=0;
		
		try {
			claimId = policyService.claimPolicy(claimDetail, userId);
		} catch (InvalidCustomerPolicy e) {
//			e.printStackTrace();
			return -1;
		}
		System.out.println(claimDetail);
		System.out.println("HI");
		System.out.println(claimId);
		
		return claimId;
	}
	
	
	@PostMapping("/renew-policy/{userId}")
	public long renewPolicyController(@RequestBody RenewPolicy renewDetail, @PathVariable String userId) {
		
		long policyId=-1;
		try {
			policyId = policyService.renewPolicy(renewDetail, userId);
		} catch (InvalidCustomerPolicy e) {
//			e.printStackTrace();
			return -2;
		}
		
		return policyId;
		
	}
	
//	@PostMapping("/calcIns")
//	public ResultPremium calPremium(@RequestBody CompTpl ct) {
//		ResultPremium resultPrem=new ResultPremium();
//		System.out.println("Calculating Premium");
//		resultPrem.setResultTpl(ins.calculatePremium(ct));
//		System.out.println(resultPrem.getResultTpl());
//		
//		resultPrem.setResultComp(ins2.calculatePremium(ct));
//		System.out.println(resultPrem.getResultComp());
//		return resultPrem;
//	}
	
	


	@PostMapping("/calcIns")
	public ResultPremium calPremium(@RequestBody CompTpl ct) {
		ResultPremium resultPrem=new ResultPremium();
		resultPrem.setResultTpl(ins.calculatePremium(ct).get(0));
//		System.out.println(resultPrem.getResultTpl());
		
		List<Integer>list=ins2.calculatePremium(ct);
		resultPrem.setResultComp(list.get(1));
//		System.out.println(resultPrem.getResultComp());
		resultPrem.setIdv(list.get(0));
		return resultPrem;
	}
	
}
