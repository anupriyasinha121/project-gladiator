package com.lti.dao;

import java.util.List;

import com.lti.entity.Vehicle;

public interface VehicleDAO {
	
	public boolean addVehicle(Vehicle vehicle);
	
	public Vehicle getVehicle(long vehicleID);

	// For form : when customer select vehicle type
	List <Vehicle> getAllManufacturerByType(int type);					
	
	// For form : when customer select manufacturer
	List <Vehicle> getAllModelByManu(String manufacturer, int type);				
	
	// when customer select manufacturer and model
	Vehicle getvehicleByModel(String manufacturer, int type);		
}
