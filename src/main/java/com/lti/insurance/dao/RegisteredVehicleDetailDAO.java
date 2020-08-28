package com.lti.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.RegisteredVehicleDetail;

public interface RegisteredVehicleDetailDAO {
	
	public boolean addRegisteredVehicle(RegisteredVehicleDetail regDetail);			// For Buy Policy
	public RegisteredVehicleDetail getRegisteredVehicle(int regId);
	public boolean updateRegisteredVehicle(RegisteredVehicleDetail regDetail);
	
}



//	insert	<- Buy Policy
//	select by Id
//  