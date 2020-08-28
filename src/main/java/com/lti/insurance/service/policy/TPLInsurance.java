package com.lti.insurance.service.policy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.insurance.dao.PremiumRateDAO;
import com.lti.insurance.dto.CompTpl;
import com.lti.insurance.entity.PremiumRate;

@Service(value="tpl")
public class TPLInsurance implements Insurance {

	@Autowired
	PremiumRateDAO prm;
	
	public List<Integer> calculatePremium(CompTpl cotpl) {
		List<Integer> list=new ArrayList<Integer>();
		int type=cotpl.getType();
		int enginePower=cotpl.getEnginePower();
		PremiumRate per=prm.selectPremRateByAge(type,enginePower);
		list.add(per.getTplPremium());
		return list;
	}

}

