package com.lti.dao;

import com.lti.entity.RegisteredVehicleDetail;

public interface RegisteredVehicleDetailDAO {
	
	public boolean addRegisteredVehicle(RegisteredVehicleDetail regDetail);			// For Buy Policy
	public RegisteredVehicleDetail getRegisteredVehicle(int regId);
	public boolean updateRegisteredVehicle(RegisteredVehicleDetail regDetail);
	
}



//	insert	<- Buy Policy
//	select by Id
//  