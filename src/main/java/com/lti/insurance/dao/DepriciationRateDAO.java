package com.lti.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.DepriciationRate;

public interface DepriciationRateDAO {

	public void insertdepr(DepriciationRate depr);
	public int selectdepRateByAge(double arg1);
}
