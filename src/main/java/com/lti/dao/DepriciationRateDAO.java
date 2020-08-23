package com.lti.dao;

import com.lti.entity.DepriciationRate;

public interface DepriciationRateDAO {

	public void insertdepr(DepriciationRate depr);
	public int selectdepRateByAge(double arg1);
}
