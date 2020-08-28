package com.lti.insurance.service.policy;

import java.util.List;

import com.lti.insurance.dto.CompTpl;
import com.lti.insurance.entity.Vehicle;

public interface Insurance {

	List<Integer> calculatePremium(CompTpl cotpl);
	
}
