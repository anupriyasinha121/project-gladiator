package com.lti.service.policy;

public class InsuranceFactory {

	public static Insurance getInsuranceInstance(String plan) {
		if(plan.equals("COMP")) {
			return new TPLInsurance();
		}else {
			return new ComprehensiveInsurance();
		}
	}
	
}


//comp <-

// Insurance i = InsuranceFactory.getInsuranceInstance("COMP");		//TPL

// long premium = i.calculatePremium(vehicle);