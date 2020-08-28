package com.lti.insurance.service.policy;

import org.springframework.beans.factory.annotation.Autowired;

public class InsuranceFactory {

	@Autowired
	static
	TPLInsurance tpl;
	
	@Autowired
	static
	ComprehensiveInsurance com;
	
	public static Insurance getInsuranceInstance(String plan) {
		if(plan.equals("COMP")) {
			return tpl;
		}else {
			return com;
		}
	}
	
}


//comp <-

// Insurance i = InsuranceFactory.getInsuranceInstance("COMP");		//TPL

// long premium = i.calculatePremium(vehicle);