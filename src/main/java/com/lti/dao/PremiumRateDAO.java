package com.lti.dao;

import java.util.ArrayList;

import com.lti.entity.PremiumRate;

public interface PremiumRateDAO {

	public void insertPremRate(PremiumRate perm);
	public PremiumRate selectPremRateByAge(int type,int enginePower);
}
