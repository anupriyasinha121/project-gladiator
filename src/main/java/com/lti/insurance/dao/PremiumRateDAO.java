package com.lti.insurance.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.PremiumRate;

public interface PremiumRateDAO {

	public void insertPremRate(PremiumRate perm);
	public PremiumRate selectPremRateByAge(int type,int enginePower);
}
