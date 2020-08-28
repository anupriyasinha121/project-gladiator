package com.lti.insurance.service.policy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.insurance.dao.DepriciationRateDAO;
import com.lti.insurance.dao.PremiumRateDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.dto.CompTpl;
import com.lti.insurance.entity.PremiumRate;
import com.lti.insurance.entity.Vehicle;

@Service(value="compService")
public class ComprehensiveInsurance implements Insurance {

	@Autowired
	PremiumRateDAO prm;
	
	@Autowired
	DepriciationRateDAO dpr;
	
	@Autowired
	VehicleDAO veh;
	
	public List<Integer> calculatePremium(CompTpl cotpl) {
		List<Integer> list=new ArrayList<Integer>();
		int type=cotpl.getType();
		int enginePower=cotpl.getEnginePower();
		int age=cotpl.getAge();
		String mode=cotpl.getModel();
		
		PremiumRate per=prm.selectPremRateByAge(type,enginePower);
		int deprPercent=dpr.selectdepRateByAge(age);
//		System.out.println(deprPercent);
		Vehicle v=veh.getvehicleByModel(mode,type);
//		System.out.println(v.getPrice());
		double price=v.getPrice();
//		System.out.println(price);
		double deprcost=(double)deprPercent*price/100;
//		System.out.println(deprcost);
		int idv=(int) (price-deprcost);
//		System.out.println(idv);
		double premium= per.getOwnDamagePercentage()*idv/100 + per.getTplPremium();
//		System.out.println(premium);
		int premiumWithGST= (int) (premium*1.18);
//		System.out.println(premiumWithGST);
		list.add(idv);
		list.add(premiumWithGST);
		return list;
	}

}
